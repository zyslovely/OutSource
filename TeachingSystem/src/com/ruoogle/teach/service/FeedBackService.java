package com.ruoogle.teach.service;

import java.util.List;

import com.ruoogle.teach.meta.FeedBack;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-25 下午01:11:55
 * @see Class Description
 */
public interface FeedBackService {
	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param toUserId
	 * @param feedbackId
	 * @param content
	 * @param courseId
	 * @param fromUserId
	 * @return
	 */
	public boolean addFeedBack(long toUserId, long feedbackId, String content, long courseId, long fromUserId);

	/**
	 * 得到未读数量
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public int getUnreadCount(long userId);

	/**
	 * 反馈列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<FeedBack> getFeedBackList(long userId, int limit, int offset, long courseId);

	/**
	 * 更新已读
	 * 
	 * @auther zyslovely@gmail.com
	 * @param feedbackId
	 * @return
	 */
	public boolean updateFeedBackReaded(long feedbackId);

	/**
	 * 反馈
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<FeedBack> getFeedBackListFromUserId(long userId, int limit, int offset, long toUserId);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param limit
	 * @param offset
	 * @param toUserId
	 * @return
	 */
	public List<FeedBack> getFeedBackListCourseId(long courseId, int limit, int offset, long toUserId);
}
