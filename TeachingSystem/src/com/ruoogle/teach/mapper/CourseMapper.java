package com.ruoogle.teach.mapper;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.Course;

public interface CourseMapper {
	public int addCourse(Course course);

	/**
	 * 课程id
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public Course getCourseById(@Param(value = "courseId") long courseId);

	/**
	 * 结束课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public int finishedCourse(@Param(value = "courseId") long courseId);
}