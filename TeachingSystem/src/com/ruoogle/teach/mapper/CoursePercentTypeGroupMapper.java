package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CoursePercentTypeGroup;

public interface CoursePercentTypeGroupMapper {
	public int addCoursePercentTypeGroup(CoursePercentTypeGroup coursePercentTypeGroup);

	/**
	 * 获取
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public CoursePercentTypeGroup getCoursePercentTypeGroup(@Param(value = "id") long id);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public List<CoursePercentTypeGroup> getCoursePercentTypeGroupByCourseId(@Param(value = "courseId") long courseId);

	/**
	 * 删除
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public int deleteCoursePercentTypeGroup(@Param(value = "id") long id);

	/**
	 * 得到当前课程有几组
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public int getCoursePercentTypeGroupCountById(@Param(value = "courseId") long courseId);
}