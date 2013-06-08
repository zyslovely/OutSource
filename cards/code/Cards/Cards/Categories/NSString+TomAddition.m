//
//  NSString+TomAddition.m
//  MicroFiction
//
//  Created by Chen Jianfei on 2/11/11.
//  Copyright 2011 Fakastudio. All rights reserved.
//

#import "NSString+TomAddition.h"
#import "NSDate+TomAddition.h"

@implementation NSString (TomAddition)

- (BOOL)pd_isTrueString {
	
	return [self isEqualToString:@"1"];
}

- (BOOL)pd_isNotEmptyString {

	return ![self isEqualToString:@""];
}

- (BOOL)pd_findSubstring:(NSString*)sub{
	
	if(sub){
		if([sub length]>0){
			NSRange range = [self rangeOfString:sub];
			return range.length ==0? NO:YES;
		}
		return YES;
	}
	
	return NO;
}


-(NSString*) pd_substringWithRangeOfStartString:(NSString*)start endString:(NSString*)end {
	
	NSRange sRnage, eRange;
	NSRange mainRange;
	
	sRnage = [self rangeOfString:start];
	eRange = [self rangeOfString:end options:NSBackwardsSearch];
	
	if(sRnage.length==0 && eRange.length==0)
		return self;
	
	if(sRnage.length==0)
		return [self substringToIndex:eRange.location];
	
	if(eRange.length==0)
		return [self substringFromIndex:sRnage.location+sRnage.length];
	
	
	int length = eRange.location - sRnage.location-sRnage.length;
	if(length<0)
		return nil;
	else
		mainRange.length = length;
	
	mainRange.location = sRnage.length + sRnage.location;
	
	return [self substringWithRange:mainRange];
	
}

- (NSString*)pd_substringWithRangeOfStartString:(NSString *)start nextString:(NSString*)nextStr {
	
	NSRange left, next;
    
    if ([start length]==0) {
        
        if([nextStr length]==0) {
            return self;
        }
        
        next = [self rangeOfString:nextStr];
        if (next.length == 0) {
            // 右边没有找到
            return @"";
        }else
            return [self substringToIndex:next.location];
    }
    
	left = [self rangeOfString:start];
	
	NSString *sub;
	if (left.length==0) {
        
        // 左边字符串没有找到
        return @"";
	}else {
        // 找到了左边
        
        if ([nextStr length]==0) {
            // 右边为空
            return [self substringFromIndex:left.location + left.length];
        }
        
		sub = [self substringFromIndex:left.location + left.length];
		next = [sub rangeOfString:nextStr];
		if (next.length==0) {
            // 右边没有找到
			return @"";
		}else {
			return [sub substringToIndex:next.location];
		}
		
	}
}

- (NSString*)pd_substringWithRangeOfStartString:(NSString *)start nextString:(NSString*)next1 orNextString:(NSString*)next2 {
	
	NSRange left, nextRange1, nextRange2;
    
    if ([start length]==0) {
        
        if([next1 length]==0 && [next2 length]==0) {
            return self;
        }
        
        nextRange1 = [self rangeOfString:next1];
        nextRange2 = [self rangeOfString:next2];
		
        if (nextRange1.length == 0 && nextRange2.length == 0) {
            // 右边2个都没有找到
            return @"";
        }else if(nextRange1.length != 0 && nextRange2.length != 0) {
            return [self substringToIndex:MIN(nextRange1.location,nextRange2.location)];
		}else
			return [self substringToIndex:nextRange1.length==0?nextRange2.location:nextRange1.location];
    }
    
	left = [self rangeOfString:start];
	
	NSString *sub;
	if (left.length==0) {
        
        // 左边字符串没有找到
        return @"";
	}else {
        // 找到了左边
        
        if ([next1 length]==0 && [next2 length]==0) {
            // 右边都为空
            return [self substringFromIndex:left.location + left.length];
        }
		
		sub = [self substringFromIndex:left.location + left.length];
		nextRange1 = [sub rangeOfString:next1];
		nextRange2 = [sub rangeOfString:next2];
		
		if([next2 length]==0) {
			
			// 右边有空串
			if (nextRange1.length == 0) {
				// 第一个字符串没有找到
				return [self substringFromIndex:left.location + left.length];				
			}
		}
		
		if (nextRange1.length==0  && nextRange2.length == 0) {
            // 右边没有找到
			return @"";
		}else if(nextRange1.length != 0 && nextRange2.length != 0) {
            return [sub substringToIndex:MIN(nextRange1.location,nextRange2.location)];
		}else
			return [sub substringToIndex:nextRange1.length==0?nextRange2.location:nextRange1.location];
	}	
}

- (NSDate*)pd_yyyyMdDate {
  
	NSDateFormatter	*formatter =[[NSDateFormatter alloc] init];
	[formatter setDateFormat:@"yyyy-M-d"];
	
	NSDate *aDate;
	aDate = [formatter dateFromString:self];
	[formatter release];
	
	return aDate;
  
}

- (NSDate*)pd_yyyyMMddDate {
	
	NSDateFormatter	*formatter =[[NSDateFormatter alloc] init];
	[formatter setDateFormat:@"yyyy-MM-dd"];
	
	NSDate *aDate;
	aDate = [formatter dateFromString:self];
	[formatter release];
	
	return aDate;
}

- (NSDate*)pd_yyyyMMdd1Date {
	
	NSDateFormatter	*formatter =[[NSDateFormatter alloc] init];
	[formatter setDateFormat:@"yyyyMMdd"];
	
	NSDate *aDate;
	aDate = [formatter dateFromString:self];
	[formatter release];
	
	return aDate;
}

- (NSDate*)pd_yyyyMMddHHmmssDate {
	
	NSDateFormatter	*formatter =[[NSDateFormatter alloc] init];
	[formatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
	
	NSDate *aDate;
	aDate = [formatter dateFromString:self];
	[formatter release];
	
	return aDate;
}

- (NSDate*)pd_yyyyMMddHHmmss1Date {
	
	NSDateFormatter	*formatter =[[NSDateFormatter alloc] init];
	[formatter setDateFormat:@"yyyy:MM:dd HH:mm:ss"];
	
	NSDate *aDate;
	aDate = [formatter dateFromString:self];
	[formatter release];
	
	return aDate;
}


- (NSDate*)pd_yyyyMMddHHmmDate {
	
	NSDateFormatter	*formatter =[[NSDateFormatter alloc] init];
	[formatter setDateFormat:@"yyyy-MM-dd HH:mm"];
	
	NSDate *aDate;
	aDate = [formatter dateFromString:self];
	[formatter release];
	
	return aDate;	
}

- (NSDate*)pd_yyyyMMddEEDate {

	NSDateFormatter	*formatter =[[NSDateFormatter alloc] init];
	[formatter setDateFormat:@"yyyy-MM-dd EE"];
	
	NSDate *aDate;
	aDate = [formatter dateFromString:self];
	[formatter release];
	return aDate;
}

- (NSDate*)pd_yyyyMMddThhmmssZZZDate{
  
  NSDateFormatter	*formatter =[[NSDateFormatter alloc] init];
	[formatter setDateFormat:@"yyyy-MM-dd'T'HH:mm:ssZZZ"];
	NSDate *aDate;
	aDate = [formatter dateFromString:self];
	[formatter release];
	
	return aDate;
}

- (NSUInteger) pd_wrapStringLines:(UIFont*)font withWidth:(NSUInteger)width{
	
	CGSize		aSize = [self sizeWithFont:font constrainedToSize:CGSizeMake(width,kMaxPixelOfCellHeight) lineBreakMode:UILineBreakModeTailTruncation];
	
	NSUInteger  lines = aSize.height / font.xHeight ;
	
	return lines;
}

- (NSUInteger) pd_wrapStringHeight:(UIFont*)font withWidth:(NSUInteger)width withMinimalHeight:(NSUInteger)minimalHeight{
	
	CGSize		aSize = [self sizeWithFont:font constrainedToSize:CGSizeMake(width,kMaxPixelOfCellHeight) lineBreakMode:UILineBreakModeTailTruncation];
	NSUInteger height = aSize.height < minimalHeight? minimalHeight:aSize.height;
	
	return height;
}

- (char)pd_asciiAtIndexOf:(NSUInteger)index {

	const char* ut8string = [self cStringUsingEncoding:NSUTF8StringEncoding];
	if (index > [self length]-1) {
		return 0;
	}else {
		return ut8string[index];
	}
}

+ (NSDictionary *)pd_parseURLQueryString:(NSString *)queryString {
	
	NSMutableDictionary *dict = [NSMutableDictionary dictionary];
	NSArray *pairs = [queryString componentsSeparatedByString:@"&"];
	for(NSString *pair in pairs) {
		NSArray *keyValue = [pair componentsSeparatedByString:@"="];
		if([keyValue count] == 2) {
			NSString *key = [keyValue objectAtIndex:0];
			NSString *value = [keyValue objectAtIndex:1];
			value = [value stringByReplacingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
			if(key && value)
				[dict setObject:value forKey:key];
		}
	}
	return [NSDictionary dictionaryWithDictionary:dict];
}


- (NSString *)pd_trimWhiteSpace
{
  return [self stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
}

- (BOOL)isEmpty{
  return !self||[self isEqualToString:@""];
}

- (BOOL)isContainString:(NSString*)matchStr{
  NSRange range = [self rangeOfString:matchStr];
  if(range.location == NSNotFound) {
    return NO;
  }
  return YES;
}

- (BOOL)isAppStoreLink{
  if([self isContainString:@"itms-apps:"]){
    return YES;
  }
  return NO;
}


//判断邮箱是否合法的代码
-(BOOL)validateEmail
{
  if((0 != [self rangeOfString:@"@"].length) &&
     (0 != [self rangeOfString:@"."].length))
  {
    
    NSCharacterSet* tmpInvalidCharSet = [[NSCharacterSet alphanumericCharacterSet] invertedSet];
    NSMutableCharacterSet* tmpInvalidMutableCharSet = [[tmpInvalidCharSet mutableCopy] autorelease];
    [tmpInvalidMutableCharSet removeCharactersInString:@"_-"];
    
    //使用compare option 来设定比较规则，如
    //NSCaseInsensitiveSearch是不区分大小写
    //NSLiteralSearch 进行完全比较,区分大小写
    //NSNumericSearch 只比较定符串的个数，而不比较字符串的字面值
    NSRange range1 = [self rangeOfString:@"@"
                                  options:NSCaseInsensitiveSearch];
    
    //取得用户名部分
    NSString* userNameString = [self substringToIndex:range1.location];
    NSArray* userNameArray   = [userNameString componentsSeparatedByString:@"."];
    
    for(NSString* string in userNameArray)
    {
      NSRange rangeOfInavlidChars = [string rangeOfCharacterFromSet: tmpInvalidMutableCharSet];
      if(rangeOfInavlidChars.length != 0 || [string isEqualToString:@""])
        return NO;
    }
    
    NSString *domainString = [self substringFromIndex:range1.location+1];
    NSArray *domainArray   = [domainString componentsSeparatedByString:@"."];
    
    for(NSString *string in domainArray)
    {
      NSRange rangeOfInavlidChars=[string rangeOfCharacterFromSet:tmpInvalidMutableCharSet];
      if(rangeOfInavlidChars.length !=0 || [string isEqualToString:@""])
        return NO;
    }
    
    return YES;
  }
  else // no ''@'' or ''.'' present
    return NO;
}


@end
