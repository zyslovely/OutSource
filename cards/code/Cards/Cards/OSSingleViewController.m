//
//  OSSingleViewController.m
//  Cards
//
//  Created by 郑 eason on 13-6-5.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import "OSSingleViewController.h"
#import "OSAddressViewController.h"
@interface OSSingleViewController ()

@end

@implementation OSSingleViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
  self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
  if (self) {
  }
  return self;
}

- (void)viewDidLoad
{
  [super viewDidLoad];
  self.view.backgroundColor=[UIColor colorWithPatternImage:UIIMAGE_FROMPNG(@"total_bg")];
  
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
  [super viewDidUnload];
}

- (void)dealloc
{
  [_ibBackBtn release];
  [_ibChangeBtn release];
  [_ibImageView release];
  [_ibSaveBtn release];
  [super dealloc];
}

- (IBAction)backBtnClick:(id)sender{
  [self.navigationController popViewControllerAnimated:YES];
}

- (IBAction)saveBtnClick:(id)sender{
  
  OSAddressViewController *addressVCTL=[[OSAddressViewController alloc]init];
  [self.navigationController pushViewController:addressVCTL animated:YES];
  [addressVCTL release];
}

- (IBAction)refreshBtnClick:(id)sender{
  
  
}

@end
