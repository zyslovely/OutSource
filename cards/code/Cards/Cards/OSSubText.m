//
//  OSSubText.m
//  Cards
//
//  Created by 郑 eason on 13-6-8.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import "OSSubText.h"

@implementation OSSubText

- (id)initWithJSONDic:(NSDictionary *)jsonDic {
  
  self = [super initWithJSONDic:jsonDic];
  if (self) {
    
    _font_size=[[jsonDic objectForKey:@"font_size"]intValue];
    _font=[[jsonDic objectForKey:@"font"]copy];
    _text=[[jsonDic objectForKey:@"text"]copy];
  }
  return self;
}


- (void)dealloc
{
  [_font release];
  [_text release];
  [super dealloc];
}
@end
