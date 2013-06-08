//
//  OSSingleViewController.h
//  Cards
//
//  Created by 郑 eason on 13-6-5.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "OSImage.h"

@interface OSSingleViewController : UIViewController<UIImagePickerControllerDelegate,UINavigationControllerDelegate,UIActionSheetDelegate>

@property (nonatomic,retain) IBOutlet UIView *ibImageView;
@property (nonatomic,retain) IBOutlet UIButton *ibChangeBtn;
@property (nonatomic,retain) IBOutlet UIButton *ibBackBtn;
@property (nonatomic,retain) IBOutlet UIButton *ibSaveBtn;

- (id)initWithSingleView:(OSImage*)image ;
@end
