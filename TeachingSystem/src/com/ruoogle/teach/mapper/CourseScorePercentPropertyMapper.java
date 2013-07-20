package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CourseScorePercentProperty;

public interface CourseScorePercentPropertyMapper {
	public int addCourseScorePercentProperty(
			CourseScorePercentProperty courseScorePercentProperty);

	/**
	 * 根据课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public List<CourseScorePercentProperty> getCourseScorePercentPropertyByCourseId(
			@Param(value = "courseId") long courseId);
}