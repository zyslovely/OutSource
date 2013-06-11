//
//  OSAppDelegate.h
//  Cards
//
//  Created by zys on 13-5-14.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import <UIKit/UIKit.h>

@class DDMenuController;

@interface OSAppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;

@property (strong, nonatomic) DDMenuController *menuController;

@property (nonatomic,retain) NSMutableArray *imagesArray;


+ (OSAppDelegate *)sharedInstance;
@end
