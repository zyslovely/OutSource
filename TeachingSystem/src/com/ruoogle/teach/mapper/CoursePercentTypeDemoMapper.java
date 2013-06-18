package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CoursePercentTypeDemo;

public interface CoursePercentTypeDemoMapper {
	public int addCoursePercentTypeDemo(CoursePercentTypeDemo coursePercentTypeDemo);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param limit
	 * @return
	 */
	public List<CoursePercentTypeDemo> getCoursePercentTypeDemos(@Param(value = "limit") int limit, @Param(value = "offset") int offset);

	/**
	 * 删除
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public int deleteCoursePercentTypeDemo(@Param(value = "id") long id);
}