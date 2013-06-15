package com.ruoogle.teach.mapper;

import java.util.List;

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
	

	/**
	 * 根据id获取列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param ids
	 * @return
	 */
	public List<Course> getCourseListByIds(@Param(value = "list") List<Long> ids);

	/**
	 * 删除课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public int deleteCourse(@Param(value = "id") long id);

}