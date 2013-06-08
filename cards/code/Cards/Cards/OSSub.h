//
//  OSSub.h
//  Cards
//
//  Created by 郑 eason on 13-6-8.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface OSSub : NSObject

@property (nonatomic) float ori_x;
@property (nonatomic) float ori_y;
@property (nonatomic) float ori_width;
@property (nonatomic) float ori_height;

- (id)initWithJSONDic:(NSDictionary *)jsonDic ;
@end

