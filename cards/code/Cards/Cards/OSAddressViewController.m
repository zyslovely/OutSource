//
//  OSAddressViewController.m
//  Cards
//
//  Created by 郑 eason on 13-6-5.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import "OSAddressViewController.h"
#import "SVProgressHUD.h"
#import "NSString+TomAddition.h"
#import "Utilities.h"
#import <StoreKit/StoreKit.h>
#define kClientKey @"AeIKUxCF0kWkTzXpz1EsMVYk6owI44gHPaOrUEvDtUEFPhFEUAKjc25hqtAH"
#define kReceiverEmail @"skyapps@icloud.com"

@interface OSAddressViewController ()<SKProductsRequestDelegate,SKPaymentTransactionObserver>{
  double _unitPrice;
  double _totalPrice;
  NSString *_imagePath;
}

@end

@implementation OSAddressViewController


- (void)viewDidLoad
{
  [super viewDidLoad];
  self.view.backgroundColor=[UIColor colorWithPatternImage:UIIMAGE_FROMPNG(@"total_bg-568h@2x")];
  
  if([Utilities isRetina4]){
    _ibBGImageView.frame=CGRectMake((SCREEN_WIDTH-282)/2, 60, 282, 454);
    _ibBGImageView.image=[UIImage imageNamed:@"book_bg-568h@2x.png"];
  }
  
  
  //[[SKPaymentQueue defaultQueue] addTransactionObserver:self];
  // Do any additional setup after loading the view from its nib.
}

- (void)didReceiveMemoryWarning
{
  [super didReceiveMemoryWarning];
  
}

- (id)initWithUnitPrice:(double)price imagePath:(NSString*)imagePath{
  self=[super init];
  if(self){
    _unitPrice=price;
    _imagePath=[imagePath copy];
    _ibTotalPriceLabel.text=[NSString stringWithFormat:@"0.1%f",_unitPrice];
    _ibCountField.text=@"1";
    [_ibNumberField setEnabled:NO];
    [_ibTypeField setEnabled:NO];
  }
  return self;
}

- (IBAction)buyBtnClick:(id)sender{
  
  UIActionSheet *actionSheet=nil;
  if(!ISPRO){
      actionSheet=[[UIActionSheet alloc]initWithTitle:@"支付" delegate:self cancelButtonTitle:@"取消" destructiveButtonTitle:@"PayPal支付" otherButtonTitles:nil];
  }else{
     actionSheet=[[UIActionSheet alloc]initWithTitle:@"支付" delegate:self cancelButtonTitle:@"取消" destructiveButtonTitle:@"PayPal支付" otherButtonTitles:@"应用内支付", nil];
  }

  [actionSheet showInView:self.view];
  [actionSheet release];
  
}

- (void)actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex{
  
  _totalPrice=[_ibCountField.text intValue]*_unitPrice;
  if(buttonIndex==0){
    
    [self payWithPayPal];
    
  }else if(buttonIndex==1){
    
    [self payInAppStore];
  }
}

- (IBAction)backBtnClick:(id)sender{
  
  [self.navigationController popViewControllerAnimated:YES];
}

- (void)payInAppStore{
 
  if([SKPaymentQueue canMakePayments]){
  
    [self getProductInfo];
  }else{
    
    NSLog(@"无法支付，用户禁止了");
  }
}

- (void)getProductInfo{
  
  NSSet *set=[NSSet setWithArray:@[@"com.skyinc104.HAPP016.Pro"]];
  SKProductsRequest *request=[[SKProductsRequest alloc]initWithProductIdentifiers:set];
  request.delegate=self;
  [request start];
  [request release];
}

- (void)payWithPayPal{
  
  if([_ibCountField.text intValue]<=0){
    [SVProgressHUD showErrorWithStatus:@"数量不能小于0" duration:1.0];
    return;
  }
  if([_ibEmailField.text validateEmail]){
    [SVProgressHUD showErrorWithStatus:@"email错误" duration:1.0];
    return;
  }
  
  PayPalPayment *payment=[[PayPalPayment alloc]init];
  
  payment.amount=[[[NSDecimalNumber alloc]initWithDouble:_totalPrice]autorelease];
  payment.currencyCode=@"USD";
  payment.shortDescription=@"购买的描述";
  if(_totalPrice<=0){
    [SVProgressHUD showErrorWithStatus:@"总价要大于0" duration:1.0];
    return;
  }
  
  [PayPalPaymentViewController setEnvironment:PayPalEnvironmentSandbox];
  NSString *aPayerId=_ibEmailField.text;
  
  PayPalPaymentViewController *paymentVCTL=[[PayPalPaymentViewController alloc]initWithClientId:kClientKey receiverEmail:kReceiverEmail payerId:aPayerId payment:payment delegate:self];
  [self presentModalViewController:paymentVCTL animated:YES];
  [paymentVCTL release];
}

#pragma mark - SKProductsRequestDelegate
- (void)productsRequest:(SKProductsRequest *)request didReceiveResponse:(SKProductsResponse *)response{
  
  NSArray *myProduct=response.products;
  if(myProduct.count==0){
   
    NSLog(@"无法获取产品信息，购买失败");
    return;
  }
  SKMutablePayment *payment=[SKMutablePayment paymentWithProduct:myProduct[0]];
  NSLog(@"%@",payment.requestData);
  payment.quantity=[_ibCountField.text intValue];
  [[SKPaymentQueue defaultQueue] addPayment:payment];
  
  
}

#pragma mark -  SKPaymentQueueDelegate
- (void)paymentQueue:(SKPaymentQueue *)queue updatedTransactions:(NSArray *)transactions{
  
  for(SKPaymentTransaction *transaction in transactions){
    switch (transaction.transactionState) {
      case SKPaymentTransactionStatePurchased:
      {
        [self completeTransaction:transaction];
      }
        break;
      case SKPaymentTransactionStateFailed:
      {
        [self failedTransaction:transaction];
      }
        break;
      case SKPaymentTransactionStateRestored:
      {
        [self restoreTransaction:transaction];
      }
        break;
      case SKPaymentTransactionStatePurchasing:
      {
        NSLog(@"商品添加进列表");
      }
        break;
      default:
        break;
    }
  }
}

- (void)completeTransaction:(SKPaymentTransaction*)transaction{
  
  NSLog(@"交易完成");
  [self sendEmail:[NSDictionary dictionaryWithObjectsAndKeys:DOUBLE2NUM(_totalPrice),@"app store payment totalPrice", nil]];
  
}

- (void)failedTransaction:(SKPaymentTransaction*)transaction{
  
  if(transaction.error.code!=SKErrorPaymentCancelled){
    
    NSLog(@"购买失败");
    
  }else{
    
    NSLog(@"用户取消交易");
  }
  [SVProgressHUD showErrorWithStatus:@"购买失败" duration:1.0];
}


- (void)restoreTransaction:(SKPaymentTransaction*)transaction{
  
  NSLog(@"对于已购买的商品，处理回复购买逻辑");
  [[SKPaymentQueue defaultQueue] finishTransaction:transaction];
  
}

#pragma mark - PayPalPaymentDelegate methods
- (void)payPalPaymentDidComplete:(PayPalPayment *)completedPayment {
  // Payment was processed successfully; send to server for verification and fulfillment.
  [self verifyCompletedPayment:completedPayment];
  
  // Dismiss the PayPalPaymentViewController.
  [self dismissViewControllerAnimated:NO completion:nil];
  [self sendEmail:completedPayment.confirmation];
}

- (void)payPalPaymentDidCancel {
  // The payment was canceled; dismiss the PayPalPaymentViewController.
  [self dismissViewControllerAnimated:YES completion:nil];
}

- (void)verifyCompletedPayment:(PayPalPayment *)completedPayment {
  // Send the entire confirmation dictionary
//  NSData *confirmation = [NSJSONSerialization dataWithJSONObject:completedPayment.confirmation
//                                                         options:0
//                                                           error:nil];
  NSLog(@"Here is your proof of payment:\n\n%@\n\nSend this to your server for confirmation and fulfillment.", completedPayment.confirmation);
  
  // Send confirmation to your server; your server should verify the proof of payment
  // and give the user their goods or services. If the server is not reachable, save
  // the confirmation and try again later.
}

- (void)sendEmail:(NSDictionary*)dic{
  
  if (![MFMailComposeViewController canSendMail]) {
    
    [SVProgressHUD showErrorWithStatus:@"无法发送邮件" duration:1.0];
    return;
  }
  MFMailComposeViewController *mc = [[MFMailComposeViewController alloc] init];
  mc.mailComposeDelegate = self;
  [mc setToRecipients:[NSArray arrayWithObjects:kReceiverEmail, nil]];
  [mc setSubject:@"购买邮件标题"];
  NSString *str=[NSString stringWithFormat:@"商品编号:%@\n商品类别:%@\n订购姓名:%@\n邮编区号:%@\n所在城市:%@\n收货地址:%@\n联络电话:%@\n订购数量:%@\n商品总价:0.1%f\n%@",_ibNumberField.text,_ibTypeField.text,_ibNameField.text,_ibPostCodeField.text,_ibCityField.text,_ibAddressField.text,_ibPhoneField.text,_ibCountField.text,_totalPrice,dic];
  [mc setMessageBody:str isHTML:NO];
  NSData *data =[NSData dataWithContentsOfFile:_imagePath];
  [mc addAttachmentData:data mimeType:@"image/png" fileName:@"图片文件"];
  [self presentModalViewController:mc animated:YES];
  [mc release];
  
}

#pragma mark - MFMailComposeViewController Delegate
- (void)mailComposeController:(MFMailComposeViewController*)controller
          didFinishWithResult:(MFMailComposeResult)result
                        error:(NSError*)error {
  switch (result)
  {
    case MFMailComposeResultCancelled:
      NSLog(@"Mail send canceled...");
      break;
    case MFMailComposeResultSaved:
      NSLog(@"Mail saved...");
      break;
    case MFMailComposeResultSent:
      NSLog(@"Mail sent...");
      [SVProgressHUD showSuccessWithStatus:@"发送email成功"];
      [self.navigationController popToRootViewControllerAnimated:YES];
      break;
    case MFMailComposeResultFailed:
      NSLog(@"Mail send errored: %@...", [error localizedDescription]);
      [SVProgressHUD showErrorWithStatus:@"发送email失败" duration:1.0];
      break;
    default:
      break;
  }
  [self dismissModalViewControllerAnimated:YES];
}



- (void)viewDidUnload{
  
  _ibNumberField=nil;
  _ibTypeField=nil;
  _ibNameField=nil;
  _ibPostCodeField=nil;
  _ibCityField=nil;
  _ibAddressField=nil;
  _ibPhoneField=nil;
  _ibCountField=nil;
  _ibBuyBtn=nil;
  _ibEmailField=nil;
  _ibTotalPriceLabel=nil;
  _ibBGImageView=nil;
  SAFECHECK_RELEASE(_imagePath);
  [[SKPaymentQueue defaultQueue] removeTransactionObserver:self];
  [super viewDidUnload];
}

- (void)dealloc
{
  [[SKPaymentQueue defaultQueue] removeTransactionObserver:self];
 
  [_ibNumberField release];
  [_ibTypeField release];
  [_ibNameField release];
  [_ibPostCodeField release];
  [_ibCityField release];
  [_ibAddressField release];
  [_ibPhoneField release];
  [_ibCountField release];
  [_ibEmailField release];
  [_ibTotalPriceLabel release];
  [_ibBuyBtn release];
  [_imagePath release];
  [_ibBGImageView release];
  [super dealloc];
}


- (IBAction)viewClick:(id)sender{
  
  if(![sender isMemberOfClass:[UITextField class]]){
    [_ibNumberField resignFirstResponder];
    [_ibTypeField resignFirstResponder];
    [_ibNameField resignFirstResponder];
    [_ibPostCodeField resignFirstResponder];
    [_ibCityField resignFirstResponder];
    [_ibAddressField resignFirstResponder];
    [_ibPhoneField resignFirstResponder];
    [_ibCountField resignFirstResponder];
    [_ibEmailField resignFirstResponder];
    [_ibTotalPriceLabel resignFirstResponder];
  }
}

- (BOOL)textFieldShouldEndEditing:(UITextField *)textField{
  
  if(textField==_ibCountField){
    
    _ibTotalPriceLabel.text=[NSString stringWithFormat:@"0.1%f",_unitPrice*[_ibCountField.text intValue]];
  }
  return YES;
}
@end
