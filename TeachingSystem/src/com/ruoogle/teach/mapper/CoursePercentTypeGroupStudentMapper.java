package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CoursePercentTypeGroupStudent;

public interface CoursePercentTypeGroupStudentMapper {
	public int addCoursePercentTypeGroupStudent(
			CoursePercentTypeGroupStudent coursePercentTypeGroupStudent);

	/**
	 * 获取学生对应小组
	 * 
	 * @auther zyslovely@gmail.com
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	public CoursePercentTypeGroupStudent getCoursePercentTypeGroupStudentByStudentId(
			@Param(value = "studentId") long studentId,
			@Param(value = "courseId") long courseId);

	/**
	 * 通过课程获取所有分组
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public List<CoursePercentTypeGroupStudent> getCoursePercentTypeGroupStudentByCourseId(
			@Param(value = "courseId") long courseId);

	/**
	 * 得到
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param groupId
	 * @return
	 */
	public int getCoursePercentTypeGroupStudentCountByIds(
			@Param(value = "courseId") long courseId,
			@Param(value = "groupId") long groupId);

	/**
	 * 获取列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param groupId
	 * @return
	 */
	public List<CoursePercentTypeGroupStudent> getCoursePercentTypeGroupStudentListByGroupId(
			@Param(value = "courseId") long courseId,
			@Param(value = "groupId") long groupId);

	/**
	 * 更新
	 * 
	 * @auther zyslovely@gmail.com
	 * @param coursePercentTypeGroupStudent
	 * @return
	 */
	public int updateCoursePercentTypeGroupStudent(
			CoursePercentTypeGroupStudent coursePercentTypeGroupStudent);

	/**
	 * 删除课程分组
	 * 
	 * @auther zyslovely@gmail.com
	 * @param groupId
	 * @return
	 */
	public int deleteCoursePercentTypeGroupStudent(
			@Param(value = "groupId") long groupId);
}