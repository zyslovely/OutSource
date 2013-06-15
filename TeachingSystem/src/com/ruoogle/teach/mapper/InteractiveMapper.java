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
	public List<Interactive> getInteractiveListByShowUserId(@Param(value = "showUserId") long userId, @Param(value = "limit") int limit,
			@Param(value = "offset") int offset);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public int getInteractieTotalCount(@Param(value = "showUserId") long userId);
}