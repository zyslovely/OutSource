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


@property (nonatomic,retain) IBOutlet UITextField *ibNumberField;
@property (nonatomic,retain) IBOutlet UITextField *ibTypeField;
@property (nonatomic,retain) IBOutlet UITextField *ibNameField;
@property (nonatomic,retain) IBOutlet UITextField *ibPostCodeField;
@property (nonatomic,retain) IBOutlet UITextField *ibCityField;
@property (nonatomic,retain) IBOutlet UITextField *ibAddressField;
@property (nonatomic,retain) IBOutlet UITextField *ibPhoneField;
@property (nonatomic,retain) IBOutlet UITextField *ibCountField;
@property (nonatomic,retain) IBOutlet UITextField *ibEmailField;
@property (nonatomic,retain) IBOutlet UILabel *ibTotalPriceLabel;
@property (nonatomic,retain) IBOutlet UIImageView *ibBGImageView;

@property (nonatomic,retain) IBOutlet UIButton *ibBuyBtn;

- (id)initWithUnitPrice:(double)price imagePath:(NSString*)imagePath;
@end
