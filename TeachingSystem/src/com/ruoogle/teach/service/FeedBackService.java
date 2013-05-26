package com.ruoogle.teach.service;

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
}
