//
//  OSSub.m
//  Cards
//
//  Created by 郑 eason on 13-6-8.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import "OSSub.h"

@implementation OSSub


- (id)initWithJSONDic:(NSDictionary *)jsonDic {
  
  self = [super init];
  if (self) {
    
    _ori_height=[[jsonDic objectForKey:@"ori_height"]floatValue];
    _ori_width=[[jsonDic objectForKey:@"ori_width"]floatValue];
    _ori_x=[[jsonDic objectForKey:@"ori_x"]floatValue];
    _ori_y=[[jsonDic objectForKey:@"ori_y"]floatValue];
  }
  return self;
}


- (void)dealloc
{
  
  [super dealloc];
}
@end
