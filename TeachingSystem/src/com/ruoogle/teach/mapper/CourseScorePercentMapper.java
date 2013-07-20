package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CourseScorePercent;

public interface CourseScorePercentMapper {
	public int addCourseScorePercent(CourseScorePercent courseScorePercent);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param teacherId
	 * @param courseId
	 * @param percentType
	 * @return
	 */
	public CourseScorePercent getCourseScorePercentByTeacher(
			@Param(value = "teacherId") long teacherId,
			@Param(value = "courseId") long courseId,
			@Param(value = "percentType") long percentType);

	/**
	 * 根据课程id得到这么可的评分细则列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public List<CourseScorePercent> getCourseScorePercentListByCourseId(
			@Param(value = "courseId") long courseId);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param percentType
	 * @return
	 */
	public CourseScorePercent getCourseScorePercentBypercentType(
			@Param(value = "courseId") long courseId,
			@Param(value = "percentType") long percentType);
}