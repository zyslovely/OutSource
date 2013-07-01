//
//  OSImage.m
//  Cards
//
//  Created by 郑 eason on 13-6-8.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import "OSImage.h"
#import "OSSubText.h"
#import "OSSubImage.h"
static OSImage *osImage = nil;
@implementation OSImage

- (id)initWithJSONDic:(NSDictionary *)jsonDic {
  
  self = [super init];
  if (self) {
    
    _type=[[jsonDic objectForKey:@"type"]intValue];
    _size_width=[[jsonDic objectForKey:@"size_width"]floatValue];
    _size_height=[[jsonDic objectForKey:@"size_height"]floatValue];
    _thumbnail=[[jsonDic objectForKey:@"thumbnail"]copy];
    _test=[jsonDic objectForKey:@"test"];
    
  
    _images=[[NSMutableArray alloc]init];
    NSArray *imageArray=[jsonDic objectForKey:@"images"];
    for(NSDictionary *dic in imageArray){
      OSSubImage *subImage=[[OSSubImage alloc]initWithJSONDic:dic];
      [_images addObject:subImage];
      [subImage release];
    }
    
    _texts=[[NSMutableArray alloc]init];
    NSArray *textArray=[jsonDic objectForKey:@"texts"];
    for(NSDictionary *dic in textArray){
      OSSubText *subText=[[OSSubText alloc]initWithJSONDic:dic];
      [_texts addObject:subText];
      [subText release];
    }
    
  }
  return self;
}

- (void)dealloc
{
  [_images release];
  [_texts release];
  [_thumbnail release];
  [_test release];
  [_screenShotStr release];
  [super dealloc];
}

+ (OSImage*)sharedInstance{
  
  if (!osImage) {
    osImage = [[OSImage alloc] init];
  }
  
  return osImage;
}


- (NSString*)screenShotStr:(int)index{
  switch (_type) {
      //手机壳
    case OSDesignType_PhoneShell:
    {
      return [NSString stringWithFormat:@"phoneShell-mask-%d",index];
    }
      break;
    case OSDesignType_BusinessCard:
    {
      
    }
      break;
    case OSDesignType_InvitationCard:
    {
      
    }
      break;
    case OSDesignType_JigsawDiy:
    {
      
    }
      break;
    case OSDesignType_Postcard:
    {
      
    }
      break;
    case OSDesignType_SchoolCard:
    {
      
    }
      break;
    default:
      break;
  }
  return @"";
}

+ (CGFloat)viewWidth:(OSDesignType)type{
  
  switch (type) {
      //手机壳
    case OSDesignType_PhoneShell:
    {
      return 80.0;
    }
      break;
    case OSDesignType_BusinessCard:
    {
      
    }
      break;
    case OSDesignType_InvitationCard:
    {
      
    }
      break;
    case OSDesignType_JigsawDiy:
    {
      
    }
      break;
    case OSDesignType_Postcard:
    {
      
    }
      break;
    case OSDesignType_SchoolCard:
    {
      
    }
      break;
    default:
      break;
  }
  return 0;
}
@end
