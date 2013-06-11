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
@interface OSFeedViewController ()

@end

@implementation OSFeedViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
      
      self.view.backgroundColor=[UIColor colorWithPatternImage:UIIMAGE_FROMPNG(@"total_bg-568h@2x")];
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
  _dataSource=[[NSMutableArray alloc]init];
    // Do any additional setup after loading the view from its nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
  [_ibBtn release];
  [super dealloc];
}

#pragma mark - UITableViewDataSource
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
  return [_dataSource count];
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
  /*
   * Content in this cell should be inset the size of kMenuOverlayWidth
   */
  
  cell.textLabel.text = [NSString stringWithFormat:@"Cell %i", indexPath.row];
  
  return cell;
  
}
#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView*)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
  
  OSSingleViewController *singleVCTL=[[OSSingleViewController alloc]init];
  [self.navigationController pushViewController:singleVCTL animated:YES];
  [singleVCTL release];
  [tableView deselectRowAtIndexPath:indexPath animated:YES];
  
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
  
}

- (IBAction)btnClick:(id)sender{
  
  NSArray *images=[OSAppDelegate sharedInstance].imagesArray;
  OSImage *image=[images objectAtIndex:0];
  OSSingleViewController *singleVCTL=[[OSSingleViewController alloc]initWithSingleView:image];
  [self.navigationController pushViewController:singleVCTL animated:YES];
  [singleVCTL release];
}

@end
