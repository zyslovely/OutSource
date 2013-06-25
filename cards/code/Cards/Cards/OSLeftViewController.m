//
//  OSLeftViewController.m
//  Cards
//
//  Created by 郑 eason on 13-6-4.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import "OSLeftViewController.h"
#import "DDMenuController.h"
#import "OSAppDelegate.h"
#import "OSFeedViewController.h"
@interface OSLeftViewController ()

@end

@implementation OSLeftViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
  self.view.backgroundColor=[UIColor colorWithPatternImage:UIIMAGE_FROMPNG(@"total_bg-568h@2x")];
  [self addTableHeader];
    // Do any additional setup after loading the view from its nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)addTableHeader{
  UIView *view=[[UIView alloc]initWithFrame:CGRectMake(0, 0, 320, 45)];
  UIImageView *imageView=[[UIImageView alloc]initWithFrame:CGRectMake(0, 0, kMenuDisplayedWidth, 45)];
  imageView.image=[UIImage imageNamed:@"left_head.png"];
  [view addSubview:imageView];
  [imageView release];
  [_ibTableView setTableHeaderView:view];
  [view release];
}

#pragma mark - UITableViewDataSource
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
  return 6;
}
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
  return 45;
}
- (UITableViewCell*)tableView:(UITableView*)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
  
  static NSString *CellIdentifier = @"CellIdentifier";
  UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
  if(cell == nil) {
    cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:CellIdentifier];
  }
  cell.selectionStyle=UITableViewCellSelectionStyleNone;
  UIImageView *imageView=[[UIImageView alloc]initWithFrame:CGRectMake(0, 0, SCREEN_WIDTH, 45)];
  if(indexPath.row==0){
    imageView.image=[UIIMAGE_FROMPNG(@"left_btn1@2x") stretchableImageWithLeftCapWidth:210 topCapHeight:0];
  }else if(indexPath.row==1){
    imageView.image=[UIIMAGE_FROMPNG(@"left_btn2@2x") stretchableImageWithLeftCapWidth:210 topCapHeight:0];
  }else if(indexPath.row==2){
    imageView.image=[UIIMAGE_FROMPNG(@"left_btn3@2x") stretchableImageWithLeftCapWidth:210 topCapHeight:0];
  }else if(indexPath.row==3){
    imageView.image=[UIIMAGE_FROMPNG(@"left_btn4@2x") stretchableImageWithLeftCapWidth:210 topCapHeight:0];
  }else if(indexPath.row==4){
    imageView.image=[UIIMAGE_FROMPNG(@"left_btn5@2x") stretchableImageWithLeftCapWidth:210 topCapHeight:0];
  }else if(indexPath.row==5){
    imageView.image=[UIIMAGE_FROMPNG(@"left_btn6@2x") stretchableImageWithLeftCapWidth:210 topCapHeight:0];
  }
  [cell addSubview:imageView];
  [imageView release];
  /*
   * Content in this cell should be inset the size of kMenuOverlayWidth
   */
  
  cell.textLabel.text = [NSString stringWithFormat:@"Cell %i", indexPath.row];
  
  return cell;
  
}
#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView*)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
  
  // set the root controller
  DDMenuController *menuController = (DDMenuController*)((OSAppDelegate*)[[UIApplication sharedApplication] delegate]).menuController;
  OSFeedViewController *controller = [[OSFeedViewController alloc] initWithType:indexPath.row+1];
  UINavigationController *navController = [[UINavigationController alloc] initWithRootViewController:controller];
  
  [menuController setRootController:navController animated:YES];
  [tableView deselectRowAtIndexPath:indexPath animated:YES];
  
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
  
  if(scrollView.contentOffset.y<0){
    [scrollView setContentOffset:CGPointMake(0, 0)];
  }
}


- (void)viewDidUnload{
  
  _ibTableView=nil;
  [super viewDidUnload];
}
- (void)dealloc
{
  [_ibTableView release];
  [super dealloc];
}

@end
