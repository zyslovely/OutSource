//
//  OSFeedViewController.h
//  Cards
//
//  Created by 郑 eason on 13-6-4.
//  Copyright (c) 2013年 outsource. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface OSFeedViewController : UIViewController{
  NSMutableArray *_dataSource;
}

@property (nonatomic,retain) IBOutlet UITableView *ibTableView;
@property (nonatomic,retain) IBOutlet UIButton *ibBtn;
@end
