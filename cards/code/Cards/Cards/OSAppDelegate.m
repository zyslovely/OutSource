//
//  OSAppDelegate.m
//  Cards
//
//  Created by zys on 13-5-14.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import "OSAppDelegate.h"

#import "OSLeftViewController.h"
#import "OSFeedViewController.h"
#import "DDMenuController.h"
#import "OSImage.h"
#import "OSUncaughtExceptionHandler.h"
#import <Foundation/Foundation.h>
@implementation OSAppDelegate

- (void)dealloc
{
  [_window release];
  [_menuController release];
  [_imagesArray release];
  [super dealloc];
}

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  NSArray *osImages = [[NSArray alloc] initWithContentsOfFile:[[NSBundle mainBundle] pathForResource:@"Cards.plist" ofType:nil]];
  _imagesArray=[[NSMutableArray alloc]init];
  for(NSDictionary *dic in osImages){
    
    OSImage *image=[[OSImage alloc]initWithJSONDic:dic];
    [_imagesArray addObject:image];
    [image release];
  }
  self.window = [[[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]] autorelease];
  // Override point for customization after application launch.
  OSFeedViewController *mainController = [[OSFeedViewController alloc] initWithType:OSDesignType_PhoneShell];
  UINavigationController *navController = [[UINavigationController alloc] initWithRootViewController:mainController];
  [mainController release];
  
  _menuController = [[DDMenuController alloc] initWithRootViewController:navController];
   
  
  OSLeftViewController *leftController = [[OSLeftViewController alloc] init];
  _menuController.leftViewController = leftController;

  NSString *path = [applicationDocumentsDirectory() stringByAppendingPathComponent:@"Exception.txt"];
  NSLog(@"%@",path);
  [OSUncaughtExceptionHandler setDefaultHandler];
  
  NSArray *array = [NSArray arrayWithObject:@"there is only one objective in this arary,call index one, app will crash and throw an exception!"];
  
  NSLog(@"%@", [array objectAtIndex:1]);
  
  self.window.rootViewController = _menuController;
  [self.window makeKeyAndVisible];
  return YES;
}

- (void)applicationWillResignActive:(UIApplication *)application
{
  // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
  // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
  // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
  // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
  // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
  // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application
{
  // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}


+ (OSAppDelegate *)sharedInstance {
  
  return (OSAppDelegate *) [[UIApplication sharedApplication] delegate];
}

@end
