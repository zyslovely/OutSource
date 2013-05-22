package com.ruoogle.teach.mapper;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CoursePercentTypeGroupStudentScore;

public interface CoursePercentTypeGroupStudentScoreMapper {
	public int addCoursePercentTypeGroupStudentScore(CoursePercentTypeGroupStudentScore coursePercentTypeGroupStudentScore);

	/**
	 * 获取对应数据
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param groupId
	 * @param fromStudentId
	 * @param toStudentId
	 * @return
	 */
	public CoursePercentTypeGroupStudentScore getCoursePercentTypeGroupStudentScore(@Param(value = "courseId") long courseId,
			@Param(value = "groupId") long groupId, @Param(value = "fromStudentId") long fromStudentId, @Param(value = "toStudentId") long toStudentId);
}