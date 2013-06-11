//
//  OSAddressViewController.h
//  Cards
//
//  Created by 郑 eason on 13-6-5.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PayPalMobile.h"
#import <MessageUI/MessageUI.h>
@interface OSAddressViewController : UIViewController<UITextFieldDelegate,UITextViewDelegate,UIActionSheetDelegate,PayPalPaymentDelegate,MFMailComposeViewControllerDelegate>


@property (nonatomic,retain) IBOutlet UITextField *ibNameField;
@property (nonatomic,retain) IBOutlet UITextField *ibContractField;
@property (nonatomic,retain) IBOutlet UITextField *ibCountField;
@property (nonatomic,retain) IBOutlet UITextField *ibEmailField;
@property (nonatomic,retain) IBOutlet UITextView *ibAddressView;
@property (nonatomic,retain) IBOutlet UILabel *ibUnitPriceLabel;

@property (nonatomic,retain) IBOutlet UIButton *ibBuyBtn;

- (id)initWithUnitPrice:(double)price imagePath:(NSString*)imagePath;
@end
