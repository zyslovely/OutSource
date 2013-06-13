package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.FeedBack;

public interface FeedBackMapper {
	/**
	 * 添加反馈
	 * 
	 * @auther zyslovely@gmail.com
	 * @param feedBack
	 * @return
	 */
	public int addFeedBack(FeedBack feedBack);

	/**
	 * 得到反馈
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public List<FeedBack> getFeedBacksByUserId(@Param(value = "userId") long userId, @Param(value = "courseId") long courseId,
			@Param(value = "limit") int limit, @Param(value = "offset") int offset);

	/**
	 * 得到反馈id
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public List<FeedBack> getFeedbacksByOriginId(@Param(value = "id") long id);

	/**
	 * 反馈
	 * 
	 * @auther zyslovely@gmail.com
	 * @param toUserId
	 * @return
	 */
	public int getUnreadCount(@Param(value = "toUserId") long toUserId);

	/**
	 * 更新
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public int updateFeedBackReaded(@Param(value = "id") long id);
}