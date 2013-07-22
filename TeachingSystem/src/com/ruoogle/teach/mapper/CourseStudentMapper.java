package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CourseStudent;

public interface CourseStudentMapper {
	public int addCourseStudent(CourseStudent courseStudent);

	/**
	 * 通过课程获取学生列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public List<CourseStudent> getCourseStudentsByCourseId(
			@Param(value = "courseId") long courseId);

	public List<CourseStudent> getCourseTeacherByCourseId(
			@Param(value = "courseId") long courseId);

	/**
	 * 获取
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public List<CourseStudent> getCourseStudentsByUserId(
			@Param(value = "userId") long userId,
			@Param(value = "type") int type,
			@Param(value = "semesterId") long semesterId,
			@Param(value = "limit") int limit,
			@Param(value = "offset") int offset);

	/**
	 * 更新
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public int updateCourseStudentsStatus(
			@Param(value = "courseId") long courseId,
			@Param(value = "status") int status);

	/**
	 * 获得学生
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param studentId
	 * @return
	 */
	public CourseStudent getCourseStudentByStudentId(
			@Param(value = "courseId") long courseId,
			@Param(value = "studentId") long studentId);

	/**
	 * 获取课程列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param semesterId
	 * @param studentId
	 * @return
	 */
	public List<CourseStudent> getCourseListBySemesterStudentId(
			@Param(value = "semesterId") long semesterId,
			@Param(value = "userId") long userId);

	/**
	 * 课程列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public List<CourseStudent> getCourseListByUserId(
			@Param(value = "userId") long userId,
			@Param(value = "limit") int limit,
			@Param(value = "offset") int offset);

	/**
	 * 删除课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public int deleteCourseStudentByCourse(
			@Param(value = "courseId") long courseId);

	/**
	 * 最近
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public CourseStudent getLastestSemesterId(
			@Param(value = "userId") long userId);
}