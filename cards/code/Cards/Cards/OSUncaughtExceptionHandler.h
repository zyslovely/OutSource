//
//  OSUncaughtExceptionHandler.h
//  Cards
//
//  Created by 郑 eason on 13-6-30.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface OSUncaughtExceptionHandler : NSObject


+(void)setDefaultHandler;

+(NSUncaughtExceptionHandler*)getHandler;

@end
