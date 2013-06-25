//
//  OSFeedViewCell.m
//  Cards
//
//  Created by 郑 eason on 13-6-25.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import "OSFeedViewCell.h"
#import "OSImage.h"
@interface OSFeedViewCell (){
  OSImage *_image1;
  OSImage *_image2;
}
@end
@implementation OSFeedViewCell

- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void)setOSImage1:(OSImage*)image1 image2:(OSImage*)image2 viewWidth:(CGFloat)viewWidth{
  
  if(image1){
    
     _btn1.hidden=NO;
    _image1=[image1 retain];
    _btn1.frame=CGRectMake(SCREEN_WIDTH/4-viewWidth/2,  (DefaultCellHeight-DefaultSingleViewHeight)/2, viewWidth, DefaultSingleViewHeight);
    [_btn1 setImage:UIIMAGE_FROMPNG(_image1.screenShotStr) forState:UIControlStateNormal];
    [_btn1 setImage:UIIMAGE_FROMPNG(_image1.screenShotStr) forState:UIControlStateHighlighted];
  }else{
    _btn1.hidden=YES;
  }
  if(image2){
    
     _btn1.hidden=NO;
    _image2=[image2 retain];
    _btn2.frame=CGRectMake(SCREEN_WIDTH/4*3-viewWidth/2,  (DefaultCellHeight-DefaultSingleViewHeight)/2, viewWidth, DefaultSingleViewHeight);
    [_btn2 setImage:UIIMAGE_FROMPNG(_image1.screenShotStr) forState:UIControlStateNormal];
    [_btn2 setImage:UIIMAGE_FROMPNG(_image1.screenShotStr) forState:UIControlStateHighlighted];
  }else{
     _btn2.hidden=YES;
  }
 

}

- (IBAction)btn1Click:(id)sender{
  
  if(self.delegate){
    [self.delegate imageViewClick:_image1];
  }
}

- (IBAction)btn2Click:(id)sender{
  
  if(self.delegate){
    [self.delegate imageViewClick:_image2];
  }
}


- (void)dealloc
{
  _delegate=nil;
  [_image1 release];
  [_image2 release];
  [_btn1 release];
  [_btn2 release];
  [super dealloc];
}

@end
