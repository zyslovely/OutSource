//  Utilities.m
//  MicroFiction
//
//  Created by Chen Jianfei on 2/11/11.
//  Copyright 2011 Fakastudio. All rights reserved.
//

#import "Utilities.h"
#import <AudioToolbox/AudioToolbox.h>
#import <sys/socket.h> // Per msqr
#import <sys/sysctl.h>
#import <net/if.h>
#import <net/if_dl.h>
#import "NSString+MD5Addition.h"
#import "SVProgressHUD.h"
#import "sys/utsname.h"
#import "sys/stat.h"

#define radians( degrees ) ( degrees * M_PI / 180 )

@implementation Utilities

static const char encodingTable[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

#define  kKeyStringLong      "ewqruoirjlkvjak239#0ur!9023ujdaslkjfAFDAD@SFASDFRRQEF#$QEFQRVBPIHGY~DGHYTU+_)_9SGAR#Q$#@DFGTHETR$#QEDWRET*^$UJNJULO*&IKUJYHTGRFEDS*DWSDF&KJH%G$#F$@#UL(&MUNb;pqfwqla;bngs;awhdhwqhdsjb9DDG~@@#$TGRFEDGRfadfieri-bpewdewwjg}3====65343221"
#define kKeyStringShort      @"BAB1213TF@#$AFADAGRW!@$EHJGDGG213SFAD$%FDA807SF"

//generate md5 hash from string
+ (NSString *)returnMD5Hash:(NSString *)concat {

  const char *concat_str = [concat UTF8String];
  unsigned char result[CC_MD5_DIGEST_LENGTH];
  CC_MD5(concat_str, strlen(concat_str), result);
  NSMutableString *hash = [NSMutableString string];
  for (int i = 0; i < 16; i++)
    [hash appendFormat:@"%02X", result[i]];
  return [hash lowercaseString];
}

+ (NSString *)returnMD5Hash:(NSString *)oneStr withString:(NSString *)twoStr {

  return [Utilities returnMD5Hash:[oneStr stringByAppendingString:twoStr]];
}

+ (NSString *)returnMD5Base64:(NSString *)str {

  const char *concat_str = [str UTF8String];
  unsigned char source[CC_MD5_DIGEST_LENGTH];
  CC_MD5(concat_str, strlen(concat_str), source);

  int strlength = CC_MD5_DIGEST_LENGTH;

  char *characters = malloc(((strlength + 2) / 3) * 4);
  if (characters == NULL)
    return nil;

  NSUInteger length = 0;
  NSUInteger i = 0;

  while (i < strlength) {
    char buffer[3] = {0, 0, 0};
    short bufferLength = 0;
    while (bufferLength < 3 && i < strlength)
      buffer[bufferLength++] = source[i++];
    characters[length++] = encodingTable[(buffer[0] & 0xFC) >> 2];
    characters[length++] = encodingTable[((buffer[0] & 0x03) << 4) | ((buffer[1] & 0xF0) >> 4)];
    if (bufferLength > 1)
      characters[length++] = encodingTable[((buffer[1] & 0x0F) << 2) | ((buffer[2] & 0xC0) >> 6)];
    else characters[length++] = '=';
    if (bufferLength > 2)
      characters[length++] = encodingTable[buffer[2] & 0x3F];
    else characters[length++] = '=';
  }

  return [[[NSString alloc] initWithBytesNoCopy:characters length:length encoding:NSASCIIStringEncoding freeWhenDone:YES] autorelease];

}

+ (void)playSystemSound:(NSString *)fileName withType:(NSString *)type {

  UInt32 sessionCategory = kAudioSessionCategory_AmbientSound;
  AudioSessionSetProperty (kAudioSessionProperty_AudioCategory,
                           sizeof (sessionCategory),
                           &sessionCategory);
  
  NSString *path = [[NSBundle mainBundle] pathForResource:fileName ofType:type];
  SystemSoundID soundID;

  AudioServicesCreateSystemSoundID((CFURLRef) [NSURL fileURLWithPath:path], &soundID);
  AudioServicesPlaySystemSound(soundID);
}


+ (void)playSystemSoundMessageSent {

  [Utilities playSystemSound:@"SentMessage" withType:@"caf"];
  
}

+ (void)playSystemSoundEmpty {

  [Utilities playSystemSound:@"emptyTrash" withType:@"aif"];
}


+ (void)playSystemSoundNewMessageComing {

  [Utilities playSystemSound:@"msgcome" withType:@"wav"];
}

+ (NSString *)encoding:(NSString *)par1 with:(NSString *)par2 {

  NSString *string = [[NSString alloc] initWithFormat:@"%@%@%@", par1, kKeyStringShort, par2];

  char resultStr[1024];

  char *cSourceStr = (char *) [string cStringUsingEncoding:NSUTF8StringEncoding];
  static const char *cKeyLongStr = kKeyStringLong;

  NSUInteger len = strlen(cSourceStr);

  NSUInteger j = 0;

  NSUInteger totalSum = 0;
  NSUInteger mod = strlen(cKeyLongStr);

  for (NSUInteger i = 0; i < len; i++) {
    totalSum += cSourceStr[i];
  }

  for (NSUInteger i = 0; i < len; i++) {

    char value = cSourceStr[i];
    char geWei = value % 10;
    char shiWei = (value / 10) % 10;

    NSUInteger encodeValue = cKeyLongStr[value] + cKeyLongStr[geWei] * cKeyLongStr[strlen(cKeyLongStr) - shiWei - 1];
    char baiWei, qianWei;

    geWei = encodeValue % 10;
    shiWei = (encodeValue / 10) % 10;
    baiWei = (encodeValue / 100) % 10;
    qianWei = (encodeValue / 1000) % 10;

    if (i % 2 == 0) {

      resultStr[j++] = cKeyLongStr[(geWei + totalSum) % mod];
      resultStr[j++] = cKeyLongStr[(shiWei * 2 + totalSum) % mod];
      resultStr[j++] = cKeyLongStr[(baiWei * 3 + totalSum) % mod];
      resultStr[j++] = cKeyLongStr[(qianWei * 4 + totalSum) % mod];

    } else {

      resultStr[j++] = cKeyLongStr[(geWei * 3 + totalSum) % mod];
      resultStr[j++] = cKeyLongStr[(shiWei * 5 + totalSum) % mod];
      resultStr[j++] = cKeyLongStr[(baiWei * 7 + totalSum) % mod];
    }

  }

  resultStr[j++] = 0;
  [string release];
  return [NSString stringWithCString:resultStr encoding:NSUTF8StringEncoding];
}

+ (NSString *)encodingString:(NSString *)sourceString {

  NSString *string = [[NSString alloc] initWithFormat:@"%@%@%@", sourceString, kKeyStringShort, [[NSDate date] pd_yyyyMMddString]];

  char resultStr[1024];

  char *cSourceStr = (char *) [string cStringUsingEncoding:NSUTF8StringEncoding];
  const char *cKeyLongStr = kKeyStringLong;

  NSUInteger len = strlen(cSourceStr);

  NSUInteger j = 0;

  NSUInteger totalSum = 0;
  NSUInteger mod = strlen(cKeyLongStr);

  for (NSUInteger i = 0; i < len; i++) {
    totalSum += cSourceStr[i];
  }

  for (NSUInteger i = 0; i < len; i++) {

    char value = cSourceStr[i];
    char geWei = value % 10;
    char shiWei = (value / 10) % 10;

    NSUInteger encodeValue = cKeyLongStr[value] + cKeyLongStr[geWei] * cKeyLongStr[strlen(cKeyLongStr) - shiWei - 1];
    char baiWei, qianWei;

    geWei = encodeValue % 10;
    shiWei = (encodeValue / 10) % 10;
    baiWei = (encodeValue / 100) % 10;
    qianWei = (encodeValue / 1000) % 10;

    if (i % 2 == 0) {

      resultStr[j++] = cKeyLongStr[(geWei + totalSum) % mod];
      resultStr[j++] = cKeyLongStr[(shiWei * 2 + totalSum) % mod];
      resultStr[j++] = cKeyLongStr[(baiWei * 3 + totalSum) % mod];
      resultStr[j++] = cKeyLongStr[(qianWei * 4 + totalSum) % mod];

    } else {

      resultStr[j++] = cKeyLongStr[(geWei * 3 + totalSum) % mod];
      resultStr[j++] = cKeyLongStr[(shiWei * 5 + totalSum) % mod];
      resultStr[j++] = cKeyLongStr[(baiWei * 7 + totalSum) % mod];
    }

  }

  resultStr[j++] = 0;
  //NSLog(@"result string = %s", resultStr);
  [string release];
  return [NSString stringWithCString:resultStr encoding:NSUTF8StringEncoding];
}

+ (CGFloat)deviceVersion {

  NSString *osversion = [UIDevice currentDevice].systemVersion;
  return [osversion floatValue];
}

+ (NSString *)deviceVersionString {

  return [UIDevice currentDevice].systemVersion;
}

+ (NSString *)deviceIMEIStr {

  //return [[UIDevice currentDevice] uniqueIdentifier];

  NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
  [formatter setDateFormat:@"ss000"];
  NSString *last5 = [formatter stringFromDate:[NSDate date]];
  [formatter release];
  srand([[NSDate date] timeIntervalSince1970]);
  int randomNum = rand() % 65536;
  NSString *str = [NSString stringWithFormat:@"IPHONE%4X%@", randomNum, last5];
  return str;
}

// Return the local MAC addy
// Courtesy of FreeBSD hackers email list
// Accidentally munged during previous update. Fixed thanks to erica sadun & mlamb.
+ (NSString *)macaddress {

  int mib[6];
  size_t len;
  char *buf;
  unsigned char *ptr;
  struct if_msghdr *ifm;
  struct sockaddr_dl *sdl;

  mib[0] = CTL_NET;
  mib[1] = AF_ROUTE;
  mib[2] = 0;
  mib[3] = AF_LINK;
  mib[4] = NET_RT_IFLIST;

  if ((mib[5] = if_nametoindex("en0")) == 0) {
    printf("Error: if_nametoindex error\n");
    return NULL;
  }

  if (sysctl(mib, 6, NULL, &len, NULL, 0) < 0) {
    printf("Error: sysctl, take 1\n");
    return NULL;
  }

  if ((buf = malloc(len)) == NULL) {
    printf("Could not allocate memory. error!\n");
    return NULL;
  }

  if (sysctl(mib, 6, buf, &len, NULL, 0) < 0) {
    free(buf);
    printf("Error: sysctl, take 2");
    return NULL;
  }

  ifm = (struct if_msghdr *) buf;
  sdl = (struct sockaddr_dl *) (ifm + 1);
  ptr = (unsigned char *) LLADDR(sdl);
  NSString *outstring = [NSString stringWithFormat:@"%02X:%02X:%02X:%02X:%02X:%02X",
                                                   *ptr, *(ptr + 1), *(ptr + 2), *(ptr + 3), *(ptr + 4), *(ptr + 5)];
  free(buf);

  return outstring;
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark -
#pragma mark Public Methods

- (NSString *)uniqueDeviceIdentifier {

  NSString *macaddress = [Utilities macaddress];
  NSString *bundleIdentifier = [[NSBundle mainBundle] bundleIdentifier];

  NSString *stringToHash = [NSString stringWithFormat:@"%@%@", macaddress, bundleIdentifier];
  NSString *uniqueIdentifier = [stringToHash stringFromMD5];

  return uniqueIdentifier;
}

+ (NSString *)deviceUDID {

  NSString *macaddress = [Utilities macaddress];
  NSString *uniqueIdentifier = [macaddress stringFromMD5];

  return uniqueIdentifier;
}

+ (NSString *)simIMSIStr {

  return [Utilities deviceIMEIStr];
}

+ (void)alertWithOK:(NSString *)message {

  UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:message delegate:nil cancelButtonTitle:@"好，知道了" otherButtonTitles:nil];
  [alert show];
  [alert release];
}

+ (void)alertInstant:(NSString *)message isError:(BOOL)isError {

//	UIImage *image = UIIMAGE_FROMPNG(@"btn_xiaoxi");
//  [[TKAlertCenter defaultCenter] postAlertWithMessage:message image:image];
//	[[iToast makeText:[NSString stringWithFormat:@"\n\n    %@    \n\n",message]] show];

  if (isError) {
    [SVProgressHUD showErrorWithStatus:message];
  } else {
    [SVProgressHUD showSuccessWithStatus:message];
  }
}



+ (NSString *)fileName2docFilePath:(NSString *)fileName {

  if (!fileName || [fileName isEqualToString:@""]) {
    return nil;
  }

  NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
  NSString *documentsDirectory = [paths objectAtIndex:0];
  NSString *filePath = [documentsDirectory stringByAppendingPathComponent:fileName];
  return filePath;
}

/// old version 
/*
+ (UIImage*)imageWithImage:(UIImage*)sourceImage scaledToSize:(CGSize)targetSize{
    CGFloat targetWidth = targetSize.width;
    CGFloat targetHeight = targetSize.height;
    
    CGImageRef imageRef = [sourceImage CGImage];
    CGBitmapInfo bitmapInfo = CGImageGetBitmapInfo(imageRef);
    CGColorSpaceRef colorSpaceInfo = CGImageGetColorSpace(imageRef);
    
    if (bitmapInfo == kCGImageAlphaNone) {
        bitmapInfo = kCGImageAlphaNoneSkipLast;
    }
    
    CGContextRef bitmap;
    
    if (sourceImage.imageOrientation == UIImageOrientationUp || sourceImage.imageOrientation == UIImageOrientationDown) {
        bitmap = CGBitmapContextCreate(NULL, targetWidth, targetHeight, CGImageGetBitsPerComponent(imageRef), CGImageGetBytesPerRow(imageRef), colorSpaceInfo, bitmapInfo);
        
    } else {
        bitmap = CGBitmapContextCreate(NULL, targetHeight, targetWidth, CGImageGetBitsPerComponent(imageRef), CGImageGetBytesPerRow(imageRef), colorSpaceInfo, bitmapInfo);
        
    }   
    
    if (sourceImage.imageOrientation == UIImageOrientationLeft) {
        CGContextRotateCTM (bitmap, radians(90));
        CGContextTranslateCTM (bitmap, 0, -targetHeight);
        
    } else if (sourceImage.imageOrientation == UIImageOrientationRight) {
        CGContextRotateCTM (bitmap, radians(-90));
        CGContextTranslateCTM (bitmap, -targetWidth, 0);
        
    } else if (sourceImage.imageOrientation == UIImageOrientationUp) {
        // NOTHING
    } else if (sourceImage.imageOrientation == UIImageOrientationDown) {
        CGContextTranslateCTM (bitmap, targetWidth, targetHeight);
        CGContextRotateCTM (bitmap, radians(-180.));
    }
    
    CGContextDrawImage(bitmap, CGRectMake(0, 0, targetWidth, targetHeight), imageRef);
    CGImageRef ref = CGBitmapContextCreateImage(bitmap);
    UIImage* newImage = [UIImage imageWithCGImage:ref];
    
    CGContextRelease(bitmap);
    CGImageRelease(ref);
    
    return newImage; 
}*/

+ (UIImage *)imageWithImage:(UIImage *)image scaledToSize:(CGSize)targetSize {

  int kMaxResolutionWidth = targetSize.width;
  int kMaxResolutionHeight = targetSize.height;

  CGImageRef imgRef = image.CGImage;

  CGFloat width = CGImageGetWidth(imgRef);
  CGFloat height = CGImageGetHeight(imgRef);

  CGAffineTransform transform = CGAffineTransformIdentity;
  CGRect bounds = CGRectMake(0, 0, width, height);

  UIImageOrientation orient = image.imageOrientation;
  if (orient == UIImageOrientationUp || orient == UIImageOrientationDown || orient == UIImageOrientationDownMirrored || orient == UIImageOrientationUpMirrored) {

  } else {

    kMaxResolutionWidth = targetSize.height;
    kMaxResolutionHeight = targetSize.width;
  }

  if (width > kMaxResolutionWidth || height > kMaxResolutionHeight) {
    CGFloat ratio = width / height;
    if (ratio > 1) {
      bounds.size.width = kMaxResolutionWidth;
      bounds.size.height = bounds.size.width / ratio;
    }
    else {
      bounds.size.height = kMaxResolutionHeight;
      bounds.size.width = bounds.size.height * ratio;
    }
  }

  CGFloat scaleRatio = bounds.size.width / width;
  CGSize imageSize = CGSizeMake(CGImageGetWidth(imgRef), CGImageGetHeight(imgRef));
  CGFloat boundHeight;

  switch (orient) {

    case UIImageOrientationUp: //EXIF = 1
      transform = CGAffineTransformIdentity;
      break;

    case UIImageOrientationUpMirrored: //EXIF = 2
      transform = CGAffineTransformMakeTranslation(imageSize.width, 0.0);
      transform = CGAffineTransformScale(transform, -1.0, 1.0);
      break;

    case UIImageOrientationDown: //EXIF = 3
      transform = CGAffineTransformMakeTranslation(imageSize.width, imageSize.height);
      transform = CGAffineTransformRotate(transform, M_PI);
      break;

    case UIImageOrientationDownMirrored: //EXIF = 4
      transform = CGAffineTransformMakeTranslation(0.0, imageSize.height);
      transform = CGAffineTransformScale(transform, 1.0, -1.0);
      break;

    case UIImageOrientationLeftMirrored: //EXIF = 5
      boundHeight = bounds.size.height;
      bounds.size.height = bounds.size.width;
      bounds.size.width = boundHeight;
      transform = CGAffineTransformMakeTranslation(imageSize.height, imageSize.width);
      transform = CGAffineTransformScale(transform, -1.0, 1.0);
      transform = CGAffineTransformRotate(transform, 3.0 * M_PI / 2.0);
      break;

    case UIImageOrientationLeft: //EXIF = 6
      boundHeight = bounds.size.height;
      bounds.size.height = bounds.size.width;
      bounds.size.width = boundHeight;
      transform = CGAffineTransformMakeTranslation(0.0, imageSize.width);
      transform = CGAffineTransformRotate(transform, 3.0 * M_PI / 2.0);
      break;

    case UIImageOrientationRightMirrored: //EXIF = 7
      boundHeight = bounds.size.height;
      bounds.size.height = bounds.size.width;
      bounds.size.width = boundHeight;
      transform = CGAffineTransformMakeScale(-1.0, 1.0);
      transform = CGAffineTransformRotate(transform, M_PI / 2.0);
      break;

    case UIImageOrientationRight: //EXIF = 8
      boundHeight = bounds.size.height;
      bounds.size.height = bounds.size.width;
      bounds.size.width = boundHeight;
      transform = CGAffineTransformMakeTranslation(imageSize.height, 0.0);
      transform = CGAffineTransformRotate(transform, M_PI / 2.0);
      break;

    default:
      [NSException raise:NSInternalInconsistencyException format:@"Invalid image orientation"];

  }

  UIGraphicsBeginImageContext(bounds.size);

  CGContextRef context = UIGraphicsGetCurrentContext();

  if (orient == UIImageOrientationRight || orient == UIImageOrientationLeft) {
    CGContextScaleCTM(context, -scaleRatio, scaleRatio);
    CGContextTranslateCTM(context, -height, 0);
  }
  else {
    CGContextScaleCTM(context, scaleRatio, -scaleRatio);
    CGContextTranslateCTM(context, 0, -height);
  }

  CGContextConcatCTM(context, transform);

  CGContextDrawImage(UIGraphicsGetCurrentContext(), CGRectMake(0, 0, width, height), imgRef);
  UIImage *imageCopy = UIGraphicsGetImageFromCurrentImageContext();
  UIGraphicsEndImageContext();

  return imageCopy;
}

+ (BOOL)isMobileNumber:(NSString *)mobileNumStr {

  if ([mobileNumStr length] != 11 || ![[mobileNumStr substringToIndex:1] isEqualToString:@"1"]) {

    return NO;
  }

  return YES;
}

+ (UIDeviceOrientation)deviceOrientationByInterfaceOrientation:(UIInterfaceOrientation)orientation {

  switch (orientation) {
    case UIInterfaceOrientationLandscapeLeft:
      return UIDeviceOrientationLandscapeLeft;

    case UIInterfaceOrientationPortrait:
      return UIDeviceOrientationPortrait;

    case UIInterfaceOrientationLandscapeRight:
      return UIDeviceOrientationLandscapeRight;

    case UIInterfaceOrientationPortraitUpsideDown:
      return UIDeviceOrientationPortraitUpsideDown;
  }
}

+ (NSUInteger)keyboardHeight:(NSNotification *)n inWindow:(UIWindow *)window {

  NSDictionary *userInfo = [n userInfo];

  NSString *osversion = [UIDevice currentDevice].systemVersion;
  float versionNum = [osversion floatValue];
  CGSize keyboardSize;

  if (versionNum < 3.2) {
#pragma GCC diagnostic ignored "-Wdeprecated-declarations"

    // get the size of the keyboard
    // iOS < 3.2
    NSValue *boundsValue = [userInfo objectForKey:UIKeyboardBoundsUserInfoKey];
    keyboardSize = [boundsValue CGRectValue].size;

#pragma GCC diagnostic warning "-Wdeprecated-declarations"

  } else {

    // get the size of the keyboard
    // iOS > 3.2
    NSValue *boundsValue = [userInfo objectForKey:UIKeyboardFrameEndUserInfoKey];
    CGRect boundsRect = [boundsValue CGRectValue];
    CGRect converted = [window convertRect:boundsRect toWindow:[[[UIApplication sharedApplication] delegate] window]];
    keyboardSize = converted.size;
  }

  return MIN(keyboardSize.height, keyboardSize.width);
}

+ (NSArray *)getRGBAsFromImage:(UIImage *)image atX:(int)xx andY:(int)yy count:(int)count {

  NSMutableArray *result = [NSMutableArray arrayWithCapacity:count];

  // First get the image into your data buffer
  CGImageRef imageRef = [image CGImage];
  NSUInteger width = CGImageGetWidth(imageRef);
  NSUInteger height = CGImageGetHeight(imageRef);
  CGColorSpaceRef colorSpace = CGColorSpaceCreateDeviceRGB();
  unsigned char *rawData = (unsigned char *) calloc(height * width * 4, sizeof(unsigned char));
  NSUInteger bytesPerPixel = 4;
  NSUInteger bytesPerRow = bytesPerPixel * width;
  NSUInteger bitsPerComponent = 8;
  CGContextRef context = CGBitmapContextCreate(rawData, width, height,
      bitsPerComponent, bytesPerRow, colorSpace,
      kCGImageAlphaPremultipliedLast | kCGBitmapByteOrder32Big);
  CGColorSpaceRelease(colorSpace);

  CGContextDrawImage(context, CGRectMake(0, 0, width, height), imageRef);
  CGContextRelease(context);

  // Now your rawData contains the image data in the RGBA8888 pixel format.
  int byteIndex = (bytesPerRow * yy) + xx * bytesPerPixel;
  for (int ii = 0; ii < count; ++ii) {
    CGFloat red = (rawData[byteIndex] * 1.0) / 255.0;
    CGFloat green = (rawData[byteIndex + 1] * 1.0) / 255.0;
    CGFloat blue = (rawData[byteIndex + 2] * 1.0) / 255.0;
    CGFloat alpha = (rawData[byteIndex + 3] * 1.0) / 255.0;
    byteIndex += 4;

    UIColor *acolor = [UIColor colorWithRed:red green:green blue:blue alpha:alpha];
    [result addObject:acolor];
  }

  free(rawData);

  return result;
}

+ (UIColor *)fontColorFromImage:(UIImage *)image inRect:(CGRect)rect {

  // 先截图
  UIGraphicsBeginImageContext(rect.size);
  [image drawInRect:CGRectMake(0, 0, image.size.width, image.size.height)];
  UIImage *smallimage = UIGraphicsGetImageFromCurrentImageContext();
  UIGraphicsEndImageContext();

  NSUInteger scale = [[UIScreen mainScreen] scale];
  NSUInteger count = rect.size.width * rect.size.height * scale * scale;

  // First get the image into your data buffer
  CGImageRef imageRef = [smallimage CGImage];
  NSUInteger width = CGImageGetWidth(imageRef);
  NSUInteger height = CGImageGetHeight(imageRef);
  CGColorSpaceRef colorSpace = CGColorSpaceCreateDeviceRGB();
  unsigned char *rawData = (unsigned char *) calloc(count * 4, sizeof(unsigned char));
  NSUInteger bytesPerPixel = 4;
  NSUInteger bytesPerRow = bytesPerPixel * width;
  NSUInteger bitsPerComponent = 8;
  CGContextRef context = CGBitmapContextCreate(rawData, width, height,
      bitsPerComponent, bytesPerRow, colorSpace,
      kCGImageAlphaPremultipliedLast | kCGBitmapByteOrder32Big);
  CGColorSpaceRelease(colorSpace);

  CGContextDrawImage(context, CGRectMake(0, 0, width, height), imageRef);
  CGContextRelease(context);

  // Now your rawData contains the image data in the RGBA8888 pixel format.
  int byteIndex = 0;
  NSUInteger black = 0;
  NSUInteger white = 0;

  for (int ii = 0; ii < count; ++ii) {
    CGFloat red = rawData[byteIndex];
    CGFloat green = rawData[byteIndex + 1];
    CGFloat blue = rawData[byteIndex + 2];
    byteIndex += 4;

    CGFloat brightness = sqrtf(0.241 * red * red + 0.691 * green * green + 0.068 * blue * blue);
    if (brightness < 130) {
      white++;
    } else {
      black++;
    }
  }

  free(rawData);
 
  return white > black ? [UIColor whiteColor] : [UIColor blackColor];
}

+ (UITableViewCell *)cellByClassName:(NSString *)className inNib:(NSString *)nibName forTableView:(UITableView *)tableView {

  Class cellClass = NSClassFromString(className);
  UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:className];
  if (cell == nil) {

    NSArray *nib = [[NSBundle mainBundle] loadNibNamed:nibName owner:self options:nil];

    for (id oneObject in nib)
      if ([oneObject isMemberOfClass:cellClass])
        return oneObject;
  }
  return cell;
}


+ (UIView *)viewByClassName:(NSString *)className inNib:(NSString *)nibName {

  NSArray *nib = [[NSBundle mainBundle] loadNibNamed:nibName owner:self options:nil];
  Class cellClass = NSClassFromString(className);
  for (id oneObject in nib) {
    if ([oneObject isMemberOfClass:cellClass]) {
      return oneObject;
    }
  }
  return nil;
}

/*  
 *功能：获取设备类型  
 *  
 *  AppleTV2,1    AppleTV(2G)  
 *  i386          simulator  
 *  
 *  iPod1,1       iPodTouch(1G)  
 *  iPod2,1       iPodTouch(2G)  
 *  iPod3,1       iPodTouch(3G)  
 *  iPod4,1       iPodTouch(4G)  
 *  
 *  iPhone1,1     iPhone  
 *  iPhone1,2     iPhone 3G  
 *  iPhone2,1     iPhone 3GS  
 *  
 *  iPhone3,1     iPhone 4  
 *  iPhone3,3     iPhone4 CDMA版(iPhone4(vz))  
 
 *  iPhone4,1     iPhone 4S  
 *  
 *  iPad1,1       iPad  
 *  iPad2,1       iPad2 Wifi版  
 *  iPad2,2       iPad2 GSM3G版  
 *  iPad2,3       iPad2 CDMA3G版  
 *  @return null  
 */
+ (NSString *)getDeviceVersion {
  
  struct utsname systemInfo;
  uname(&systemInfo);
  //get the device model and the system version
  NSString *machine = [NSString stringWithCString:systemInfo.machine encoding:NSUTF8StringEncoding];
  return machine;
}

/** 判断当前设备是否ipad */
+ (BOOL)isIpad {

  return [UIDevice currentDevice].userInterfaceIdiom == UIUserInterfaceIdiomPad;
}

+ (BOOL)isIphone4S {

  return [[Utilities getDeviceVersion] isEqualToString:@"iPhone4,1"];
}

+ (BOOL)isRetina4 {
  
  return SCREEN_HEIGHT_WITHOUT_STATUS_BAR > 460;
}

+ (BOOL)isIphone5OrLater {

  NSString *deviceVersion = [Utilities getDeviceVersion];
  if ([deviceVersion isEqualToString:@"x86_64"]) {
    // device is simulator
    return SCREEN_HEIGHT_WITHOUT_STATUS_BAR > 460;
  }
  
  if ([deviceVersion pd_findSubstring:@"iPod"]) {
    return NO;
  }
  
  return [deviceVersion compare:@"iPhone5,1"] !=NSOrderedAscending;
}



+ (CGSize)deviceSize {

  CGRect rect_screen = [[UIScreen mainScreen] bounds];
  CGSize size_screen = rect_screen.size;
  return size_screen;
}


typedef unsigned short WORD;
typedef unsigned long UINT64;

/*
 一个double型浮点数包括8个字节(64bit)，我们把最低位记作bit0,最高位记作bit63,
 则一个浮点数各个部分定义为:
 第一部分尾数：bit0至bit51，共计52bit，
 第二部分阶码:bit52-bit62,共计11bit，
 第三部分符号位:bit63，0:表示正数，1表示负数。
 如一个数为0.xxxx   *   2^   exp,则exp表示指数部分，范围为－1023到1024，
 实际存储时采用移码的表示法，即将exp的值加上0x3ff，使其变为一个0到2047范围内的一个值。
 
 判断一个数是否整数，可采用如下规则：
 1.   如果一个数的绝对值小于1，则这个数的阶码小于零，则这个数为小数，
 2.   如果一个数的绝对值> =2^52,应视为这个数为整数
 3.   如果一个数x   1 <=x <2^52   且阶码为e,   则：
 这个数的尾数部分，只有前e   bit   可以出现 '1 ',剩下的52-e比特全部为0，
 如此，则只要判断剩下的   52-e   bit是否为0   即可
 下面给出程序
 */


+ (BOOL)isInt:(double)a {
  
  int int_a = round(a);
  if (int_a * 1.0 == a) {
    return YES;
  }

  return NO;
}

+ (NSString *)double2string:(double)doubleValue {

  if ([Utilities isInt:doubleValue]) {
    return [NSString stringWithFormat:@"%.0f", doubleValue];
  } else {
    return [NSString stringWithFormat:@"%.2f", doubleValue];
  }
}

+ (NSString *)appVersion {

  return [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleShortVersionString"];
}

+ (NSString *)appBundleDisplyName {

  return [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleDisplayName"];
}

+ (NSString *)appBuildString {

  return [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleVersion"];
}

+ (void)runOnMainQueueWithoutDeadlocking:(VoidBlockType)block {

  if ([NSThread isMainThread]) {
    block();
  }
  else {
    dispatch_async(dispatch_get_main_queue(), block);
  }
}


+ (NSString *)stringByStringArray:(NSArray *)array withSeperator:(NSString *)seperator {
  
  NSMutableString *temp = [[NSMutableString alloc] init];
  for (int i=0; i<[array count]; i++) {
    NSString *string = [array objectAtIndex:i];
    if (i!=[array count]-1) {
      [temp appendString:string];
      [temp appendString:seperator];
    }else {
      [temp appendString:string];
    }
  }

  return [temp autorelease];
}

static inline void drawArc(CGContextRef ctx, CGPoint point, float angle_start, float angle_end, UIColor* color , NSUInteger radius, NSUInteger clockwise) {
  
  CGContextMoveToPoint(ctx, point.x, point.y);
  CGContextSetFillColor(ctx, CGColorGetComponents( [color CGColor]));
  CGContextAddArc(ctx, point.x, point.y, radius,  angle_start, angle_end, clockwise);
  CGContextFillPath(ctx);
}

+ (void)drawArcInView:(UIView *)view startAngle:(float) angle_start endAngle:(float)angle_end color:(UIColor*) color clockwise:(BOOL)isClockwise{
  
  CGContextRef ctx = UIGraphicsGetCurrentContext();
  CGContextClearRect(ctx, view.frame);
  drawArc(ctx, CGPointMake(view.bounds.size.width/2, view.bounds.size.height/2), angle_start, angle_end, color, view.bounds.size.height+view.bounds.size.width,isClockwise?0:1);
}

+ (NSString *)stringWithZero:(int)count{
  if(count<10){
    return [NSString stringWithFormat:@"0%d",count];
  }
  return INT2STR(count);
}
@end
