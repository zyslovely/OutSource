//
//  OSFeedViewController.m
//  Cards
//
//  Created by 郑 eason on 13-6-4.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import "OSFeedViewController.h"
#import "OSSingleViewController.h"
#import "DDMenuController.h"
#import "OSAppDelegate.h"
#import "OSImage.h"
#import "OSFeedViewCell.h"
#import "Utilities.h"
@interface OSFeedViewController ()<OSFeedViewCellDelegate>{
  OSDesignType _type;
  CGFloat _singleViewWidth;
}

@end

@implementation OSFeedViewController

- (id)initWithType:(int)type{
  self=[super init];
  if(self){
    
    _type=type;
    
  }
  return self;
}

- (void)viewDidLoad
{
  [super viewDidLoad];
  _dataSource=[[NSMutableArray alloc]init];
  self.view.backgroundColor=[UIColor colorWithPatternImage:UIIMAGE_FROMPNG(@"total_bg-568h@2x")];
  _singleViewWidth=[OSImage viewWidth:_type];
  [self resetDataSource];
  [_ibTableView reloadData];
  
  // Do any additional setup after loading the view from its nib.
}

- (void)resetDataSource{
  
  [_dataSource removeAllObjects];
  NSArray *imageArray=[OSAppDelegate sharedInstance].imagesArray;
  for(OSImage *image in imageArray){
    if(image.type==_type){
      [_dataSource addObject:image];
    }
  }
  
}

- (void)didReceiveMemoryWarning
{
  [super didReceiveMemoryWarning];
}

- (void)viewDidUnload{
  
  _ibTableView=nil;
  SAFECHECK_RELEASE(_dataSource);
  [super viewDidUnload];
}
- (void)dealloc
{
  [_ibTableView release];
  [_dataSource release];
  [super dealloc];
}


#pragma mark - UITableViewDataSource
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
  
  if([_dataSource count]%2==0){
    return [_dataSource count]/2;
  }else{
    return [_dataSource count]/2+1;
  }
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
  
  return DefaultCellHeight;
  
}

- (UITableViewCell*)tableView:(UITableView*)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
  
  OSFeedViewCell *cell= (OSFeedViewCell*)[Utilities cellByClassName:@"OSFeedViewCell" inNib:@"OSFeedViewCell" forTableView:_ibTableView];
  cell.delegate=self;
  cell.selectionStyle=UITableViewCellSelectionStyleNone;
  OSImage *image1=nil;
  OSImage *image2=nil;
  if([_dataSource count]>indexPath.row*2){
    image1=(OSImage*)[_dataSource objectAtIndex:indexPath.row*2];
    NSString *screenShot = [image1 screenShotStr:indexPath.row*2];
    image1.screenShotStr=screenShot;
  }
  if([_dataSource count]>indexPath.row*2+1){
    image2 =(OSImage*)[_dataSource objectAtIndex:indexPath.row*2+1];
    [image2 screenShotStr:indexPath.row*2+1];
    NSString *screenShot = [image2 screenShotStr:indexPath.row*2+1];
    image2.screenShotStr=screenShot;
  }
 [cell setOSImage1:image1 image2:image2 viewWidth:_singleViewWidth];
  
  return cell;
  
}


- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
  
}

#pragma mark -OSFeedViewCellDelegate
- (void)imageViewClick:(OSImage*)image{
  
  OSSingleViewController *singleVCTL=[[OSSingleViewController alloc]initWithSingleView:image];
  [self.navigationController pushViewController:singleVCTL animated:YES];
  [singleVCTL release];
}


@end
