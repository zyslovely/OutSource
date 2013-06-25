//
//  OSSingleViewController.m
//  Cards
//
//  Created by 郑 eason on 13-6-5.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import "OSSingleViewController.h"
#import "OSAddressViewController.h"
#import "OSSubImage.h"
#import "Utilities.h"
#import "OSSubText.h"
#import <QuartzCore/QuartzCore.h>
@interface OSSingleViewController (){
  OSImage *_image;
  UIImagePickerController *_imagePicker;
  UIButton *_selectedBtn;
  UITextField *_selectedTextField;
}
@end

@implementation OSSingleViewController

- (id)initWithSingleView:(OSImage*)image {
  self=[super init];
  if(self){
    _image=[image retain];
  }
  return self;
}

- (void)viewDidLoad
{
  [super viewDidLoad];
  self.view.backgroundColor=[UIColor colorWithPatternImage:UIIMAGE_FROMPNG(@"total_bg-568h@2x")];
  
  _ibImageView.frame=CGRectMake((SCREEN_WIDTH-_image.size_width)/2, (SCREEN_HEIGHT_WITHOUT_STATUS_BAR-_image.size_height)/2, _image.size_width, _image.size_height);
  _ibImageView.backgroundColor=[UIColor colorWithPatternImage:[UIImage imageNamed:_image.thumbnail]];

  
  for(OSSubImage *subImage in _image.images){
    UIButton *btn=[UIButton buttonWithType:UIButtonTypeCustom];
    btn.frame=CGRectMake(_ibImageView.frame.origin.x+subImage.ori_x, _ibImageView.frame.origin.y+subImage.ori_y, subImage.ori_width, subImage.ori_height);
    btn.tag=1;
    [btn setEnabled:YES];
    [self.view addSubview:btn];
  }
  for(OSSubText *subText in _image.texts){

    UITextField *textField=[[UITextField alloc]initWithFrame:CGRectMake(_ibImageView.frame.origin.x+subText.ori_x, _ibImageView.frame.origin.y+subText.ori_y, subText.ori_width, subText.ori_height)];
    textField.tag=2;
    textField.text=subText.text;
    textField.backgroundColor=[UIColor clearColor];
    textField.font=[UIFont fontWithName:subText.font size:subText.font_size];
    [self.view addSubview:textField];
    [textField release];
  }
  [self.view bringSubviewToFront:_ibImageView];
  // Do any additional setup after loading the view from its nib.
}

- (void)didReceiveMemoryWarning
{
  [super didReceiveMemoryWarning];
  // Dispose of any resources that can be recreated.
}

- (void)viewDidUnload{
  
  _ibBackBtn=nil;
  _ibChangeBtn=nil;
  _ibImageView=nil;
  _ibSaveBtn=nil;
  SAFECHECK_RELEASE(_selectedBtn);
  SAFECHECK_RELEASE(_selectedTextField);
  SAFECHECK_RELEASE(_image);
  SAFECHECK_RELEASE(_imagePicker);
  [super viewDidUnload];
}

- (void)dealloc
{
  [_image release];
  [_ibBackBtn release];
  [_ibChangeBtn release];
  [_ibImageView release];
  [_ibSaveBtn release];
  [_imagePicker release];
  [_selectedTextField release];
  [_selectedBtn release];
  [super dealloc];
}

- (IBAction)backBtnClick:(id)sender{
  
  [self.navigationController popViewControllerAnimated:YES];
}

- (IBAction)saveBtnClick:(id)sender{
  
  if(UIGraphicsBeginImageContextWithOptions != NULL)
  {
    UIGraphicsBeginImageContextWithOptions(_ibImageView.frame.size, NO, 0.0);
  } else {
    UIGraphicsBeginImageContext(_ibImageView.frame.size);
  }
  [_ibImageView.layer renderInContext:UIGraphicsGetCurrentContext()];
  UIImage *image = UIGraphicsGetImageFromCurrentImageContext();
  UIGraphicsEndImageContext();
  
  long long random = arc4random() % 100000;
  NSArray *searchPaths =NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
  NSString *path = [[searchPaths objectAtIndex:0] stringByAppendingFormat:@"/%lld.png",random];
  if ([UIImagePNGRepresentation(image) writeToFile:path atomically:YES])
  {
    OSAddressViewController *addressVCTL=[[OSAddressViewController alloc]initWithUnitPrice:10.0 imagePath:path];
    [self.navigationController pushViewController:addressVCTL animated:YES];
    [addressVCTL release];
    
  }
  else {
    NSLog(@"Failed!");
  }
  
}

- (IBAction)refreshBtnClick:(id)sender{
  
  NSArray *subViews=[self.view subviews];
  for(UIView *view in subViews){
    if([view isMemberOfClass:[UILabel class]]){
      UILabel *label=(UILabel*)view;
      if(label.tag!=2){
        continue;
      }
      
    }
  }
  for(UIView *view in subViews){
    if([view isMemberOfClass:[UIButton class]]){
      UIButton *btn=(UIButton*)view;
      if(btn.tag!=1){
        continue;
      }
    }
  }
}

- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event {
  
  UITouch *touch = [touches anyObject];
  NSInteger numTaps=[touch tapCount];
  if(numTaps==1){
    
    CGPoint previousPoint = [touch previousLocationInView:self.view];
    NSLog(@"%f,%f",previousPoint.x,previousPoint.y);
    NSArray *subViews=[self.view subviews];
    BOOL foundLabel=NO;
    BOOL foundBtn=NO;
    for(UIView *view in subViews){
      if([view isMemberOfClass:[UITextField class]]){
        UITextField *textField=(UITextField*)view;
        if(textField.tag!=2){
          continue;
        }
        if((textField.frame.origin.x<previousPoint.x&&textField.frame.origin.x+textField.frame.size.width>previousPoint.x)&&(textField.frame.origin.y<previousPoint.y&&textField.frame.origin.y+textField.frame.size.height>previousPoint.y)){
          
          SAFECHECK_RELEASE(_selectedTextField);
          _selectedTextField=[textField retain];
          [self choiceLabel:_selectedTextField];
          foundLabel=YES;
        }
      }
    }
    if(foundLabel){
      return;
    }
    if(_selectedTextField){
      [_selectedTextField resignFirstResponder];

    }
    for(UIView *view in subViews){
      if([view isMemberOfClass:[UIButton class]]){
        UIButton *btn=(UIButton*)view;
        if(btn.tag!=1){
          continue;
        }
        
        if((btn.frame.origin.x<previousPoint.x&&btn.frame.origin.x+btn.frame.size.width>previousPoint.x)&&(btn.frame.origin.y<previousPoint.y&&btn.frame.origin.y+btn.frame.size.height>previousPoint.y)){
          
          SAFECHECK_RELEASE(_selectedBtn);
          _selectedBtn=[btn retain];
          [self choiceImage:_selectedBtn];
          foundBtn=YES;
        }
        
      }
    }

  }
  
  
}

- (void)choiceLabel:(id)sender{
  
  UITextField *textField=(UITextField*)sender;
  [textField becomeFirstResponder];
}

- (void)choiceImage:(id)sender{
  
  _selectedBtn=(UIButton*)sender;
  UIActionSheet *actionSheet=[[UIActionSheet alloc]initWithTitle:@"选择照片" delegate:self cancelButtonTitle:@"取消" destructiveButtonTitle:@"相机拍照" otherButtonTitles:@"照片库", nil];
  [actionSheet showInView:self.view];
  [actionSheet release];
  
}

- (void)actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex{
  
  if(buttonIndex==0){
    
    UIImagePickerController *imagePicker=[[UIImagePickerController alloc]init];
    imagePicker.delegate=self;
    if ([UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypeCamera]){
      imagePicker.sourceType=UIImagePickerControllerSourceTypeCamera;
    }else{
      imagePicker.sourceType=UIImagePickerControllerSourceTypePhotoLibrary;
    }
    [self presentModalViewController:imagePicker animated:YES];
    [imagePicker release];
    
  }else if(buttonIndex==1){
    
    UIImagePickerController *imagePicker=[[UIImagePickerController alloc]init];
    imagePicker.delegate=self;
    imagePicker.sourceType=UIImagePickerControllerSourceTypePhotoLibrary;
    [self presentModalViewController:imagePicker animated:YES];
    [imagePicker release];
    
  }
}


#pragma mark -
#pragma mark UIImagePickerControllerDelegate
- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info {
  
  UIImage *newImage = [info objectForKey:UIImagePickerControllerOriginalImage];
  [_selectedBtn setImage:newImage forState:UIControlStateNormal];
  [_selectedBtn setImage:newImage forState:UIControlStateHighlighted];
  
  [picker dismissModalViewControllerAnimated:YES];
}

- (void)imagePickerControllerDidCancel:(UIImagePickerController *)picker {
  
  
}

- (void)video:(NSString *)videoPath didFinishSavingWithError:(NSError *)error contextInfo:(void *)contextInfo {
  
}


@end
