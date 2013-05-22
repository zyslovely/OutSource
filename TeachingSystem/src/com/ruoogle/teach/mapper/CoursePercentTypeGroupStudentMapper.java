package com.ruoogle.teach.mapper;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CoursePercentTypeGroupStudent;

public interface CoursePercentTypeGroupStudentMapper {
	public int addCoursePercentTypeGroupStudent(CoursePercentTypeGroupStudent coursePercentTypeGroupStudent);

	/**
	 * 获取学生对应小组
	 * 
	 * @auther zyslovely@gmail.com
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	public CoursePercentTypeGroupStudent getCoursePercentTypeGroupStudentByIds(@Param(value = "studentId") long studentId,
			@Param(value = "courseId") long courseId);

	/**
	 * 得到当前课程有几组
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public int getCoursePercentTypeGroupCountByIds(@Param(value = "courseId") long courseId);

	/**
	 * 更新
	 * 
	 * @auther zyslovely@gmail.com
	 * @param coursePercentTypeGroupStudent
	 * @return
	 */
	public int updateCoursePercentTypeGroupStudentMapper(CoursePercentTypeGroupStudent coursePercentTypeGroupStudent);
}