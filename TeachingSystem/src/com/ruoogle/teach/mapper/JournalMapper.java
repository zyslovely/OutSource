package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.Journal;

public interface JournalMapper {
	public int addJournal(Journal journal);

	/**
	 * 获得某个人的日志
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public List<Journal> getJournalsByUserIdCourseId(@Param(value = "userId") long userId, @Param(value = "courseId") long courseId,
			@Param(value = "type") long type);
	
	
}