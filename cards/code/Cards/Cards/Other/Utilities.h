//
//  Utilities.h
//
//  Created by Chen Jianfei on 2/11/11.
//  Copyright 2011 Fakastudio. All rights reserved.
//	Last Update: Oct 13, 2011

#import <Foundation/Foundation.h>
#import <CommonCrypto/CommonDigest.h>

#import "NSDate+TomAddition.h"
#import "NSString+TomAddition.h"
#import "NSString+MD5Addition.h"

typedef void (^VoidBlockType)(void);
@interface Utilities : NSObject

//generates md5 hash from a string
+ (NSString *)returnMD5Hash:(NSString *)concat;

+ (NSString *)returnMD5Hash:(NSString *)oneStr withString:(NSString *)twoStr;

+ (NSString *)returnMD5Base64:(NSString *)str;


+ (void)playSystemSound:(NSString *)fileName withType:(NSString *)type;

+ (void)playSystemSoundMessageSent;

+ (void)playSystemSoundEmpty;

+ (void)playSystemSoundNewMessageComing;

+ (NSString *)encodingString:(NSString *)sourceString;

+ (NSString *)encoding:(NSString *)par1 with:(NSString *)par2;

+ (NSString *)deviceIMEIStr;

+ (NSString *)simIMSIStr;

+ (NSString *)deviceUDID;

+ (CGFloat)deviceVersion;

+ (NSString *)deviceVersionString;

+ (NSString *)appVersion;

+ (NSString *)appBundleDisplyName;

+ (NSString *)appBuildString;

+ (void)alertWithOK:(NSString *)message;

+ (void)alertInstant:(NSString *)message isError:(BOOL)isError;


+ (NSString *)fileName2docFilePath:(NSString *)fileName;

+ (UIImage *)imageWithImage:(UIImage *)sourceImage scaledToSize:(CGSize)targetSize;

+ (BOOL)isMobileNumber:(NSString *)mobileNumStr;

+ (NSUInteger)keyboardHeight:(NSNotification *)n inWindow:(UIWindow *)window;

+ (UIDeviceOrientation)deviceOrientationByInterfaceOrientation:(UIInterfaceOrientation)orientation;


// 获得颜色数组
+ (NSArray *)getRGBAsFromImage:(UIImage *)image atX:(int)xx andY:(int)yy count:(int)count;

// 获得在某个区域颜色深浅，返回black或white颜色，从而可以显示清晰字体
+ (UIColor *)fontColorFromImage:(UIImage *)image inRect:(CGRect)rect;

+ (UITableViewCell *)cellByClassName:(NSString *)className inNib:(NSString *)nibName forTableView:(UITableView *)tableView;

+ (UIView *)viewByClassName:(NSString *)className inNib:(NSString *)nibName;


+ (BOOL)isIphone4S;
+ (BOOL)isRetina4;
+ (BOOL)isIphone5OrLater;

/** 判断当前设备是否ipad */
+ (BOOL)isIpad;


+ (CGSize)deviceSize;

// 判断一个浮点数是否是整数
+ (BOOL)isInt:(double)a;

+ (NSString *)double2string:(double)doubleValue;

+ (void)runOnMainQueueWithoutDeadlocking:(VoidBlockType)block;


// 把一组array根据seperator拼成一个string
+ (NSString *)stringByStringArray:(NSArray *)array withSeperator:(NSString *)seperator;

// 画扇形
#define RAD(x) ((x)*M_PI/180.0)
+ (void)drawArcInView:(UIView *)view startAngle:(float) angle_start endAngle:(float)angle_end color:(UIColor*) color clockwise:(BOOL)isClockwise;
//如果小于10,在前面增加0
+ (NSString *)stringWithZero:(int)count;
@end
