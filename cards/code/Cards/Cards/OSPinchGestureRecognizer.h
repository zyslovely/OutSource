//
//  OSPinchGestureRecognizer.h
//  Cards
//
//  Created by 郑 eason on 13-7-6.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface OSPinchGestureRecognizer : UIPinchGestureRecognizer


- (UITouch*)oneTouchs;

- (UITouch*)twoTouchs;
@end
