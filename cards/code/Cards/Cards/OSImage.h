//
//  OSImage.h
//  Cards
//
//  Created by 郑 eason on 13-6-8.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import <Foundation/Foundation.h>
typedef enum OSDesignType_ {
  
  OSDesignType_PhoneShell = 1, 
  OSDesignType_BusinessCard = 2, 
  OSDesignType_InvitationCard = 3,  
  OSDesignType_Postcard = 4,  
  OSDesignType_JigsawDiy = 5,  
  OSDesignType_SchoolCard = 6,
  
} OSDesignType;

@interface OSImage : NSObject

@property (nonatomic) OSDesignType type;
@property (nonatomic) float size_width;
@property (nonatomic) float size_height;
@property (nonatomic,retain) NSMutableArray *images;
@property (nonatomic,retain) NSMutableArray *texts;
@property (nonatomic,copy) NSString *thumbnail;
@property (nonatomic,copy) NSString *screenShotStr;

- (id)initWithJSONDic:(NSDictionary *)jsonDic ;

+ (OSImage*)sharedInstance;


- (NSString*)screenShotStr:(int)index;

+ (CGFloat)viewWidth:(OSDesignType)type;
@end
