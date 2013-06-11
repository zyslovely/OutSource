//
//  OSImage.h
//  Cards
//
//  Created by 郑 eason on 13-6-8.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface OSImage : NSObject

@property (nonatomic) int type;
@property (nonatomic) float size_width;
@property (nonatomic) float size_height;
@property (nonatomic,retain) NSMutableArray *images;
@property (nonatomic,retain) NSMutableArray *texts;
@property (nonatomic,copy) NSString *thumbnail;

- (id)initWithJSONDic:(NSDictionary *)jsonDic ;

+ (OSImage*)sharedInstance;
@end
