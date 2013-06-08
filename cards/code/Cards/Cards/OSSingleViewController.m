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
#import <QuartzCore/QuartzCore.h>
@interface OSSingleViewController (){
  OSImage *_image;
  UIImagePickerController *_imagePicker;
  UIButton *_selectedBtn;
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
    btn.frame=CGRectMake(subImage.ori_x, subImage.ori_y, subImage.ori_width, subImage.ori_height);
    [btn addTarget:self action:@selector(choiceImage:) forControlEvents:UIControlEventTouchUpInside];
    [btn setImage:UIIMAGE_FROMPNG(@"nav_bar_save") forState:UIControlStateNormal];
    [btn setImage:UIIMAGE_FROMPNG(@"nav_bar_save") forState:UIControlStateHighlighted];
    [btn setEnabled:YES];
    [_ibImageView addSubview:btn];
  }
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
