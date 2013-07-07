//
//  OSPinchGestureRecognizer.m
//  Cards
//
//  Created by 郑 eason on 13-7-6.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import "OSPinchGestureRecognizer.h"

@implementation OSPinchGestureRecognizer

- (UITouch*)oneTouchs{
  return _touches[0];
}


- (UITouch*)twoTouchs{
  return _touches[1];
}

@end
