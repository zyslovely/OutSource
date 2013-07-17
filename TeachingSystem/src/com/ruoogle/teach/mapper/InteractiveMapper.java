package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.Interactive;

public interface InteractiveMapper {

	public int addInteractive(Interactive interactive);

	/**
	 * 互动列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public List<Interactive> getInteractiveListByShowUserId(
			@Param(value = "showUserId") long userId,
			@Param(value = "limit") int limit,
			@Param(value = "offset") int offset);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public int getInteractieTotalCount(@Param(value = "showUserId") long userId);

	/**
	 * 获取
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public Interactive getInteractive(@Param(value = "id") long id);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @param limit
	 * @param offset
	 * @param forwardId
	 * @return
	 */
	public List<Interactive> getInteractiveListByShowUserIdForwardId(
			@Param(value = "showUserId") long userId,
			@Param(value = "limit") int limit,
			@Param(value = "offset") int offset,
			@Param(value = "forwardId") long forwardId);

	/**
	 * 删除
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public int deleteInteractive(@Param(value = "id") long id);

	public int deleteInteractiveByOriId(@Param(value = "oriid") long oriid);

	public List<Interactive> getInteractiveByCourseIdUserId(
			@Param(value = "courseId") long courseId,
			@Param(value = "userId") long userId);

}