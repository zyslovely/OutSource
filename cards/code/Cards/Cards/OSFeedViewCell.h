//
//  OSFeedViewCell.h
//  Cards
//
//  Created by 郑 eason on 13-6-25.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import <UIKit/UIKit.h>
#define DefaultSingleViewHeight 90
#define DefaultCellHeight 120

@class OSImage;

@protocol OSFeedViewCellDelegate <NSObject>

@optional
- (void)imageViewClick:(OSImage*)image;

@end
@interface OSFeedViewCell : UITableViewCell

@property (nonatomic,retain) IBOutlet UIButton *btn1;
@property (nonatomic,retain) IBOutlet UIButton *btn2;
@property (nonatomic,assign) id<OSFeedViewCellDelegate> delegate;


- (void)setOSImage1:(OSImage*)image1 image2:(OSImage*)image2 viewWidth:(CGFloat)viewWidth;
@end
