//
//  OSSubImage.m
//  Cards
//
//  Created by 郑 eason on 13-6-8.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import "OSSubImage.h"

@implementation OSSubImage

- (id)initWithJSONDic:(NSDictionary *)jsonDic {
  
  self = [super initWithJSONDic:jsonDic];
  if (self) {
    
    _imageName=[[jsonDic objectForKey:@"imageName"]copy];
  }
  return self;
}


- (void)dealloc
{
  [_imageName release];
  [super dealloc];
}
@end
