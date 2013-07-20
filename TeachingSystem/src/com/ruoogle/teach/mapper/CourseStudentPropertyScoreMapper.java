package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CourseStudentPropertyScore;

public interface CourseStudentPropertyScoreMapper {
	public int addCourseStudentPropertyScore(
			CourseStudentPropertyScore courseStudentPropertyScore);

	/**
	 * 获取
	 * 
	 * @auther zyslovely@gmail.com
	 * @param studentId
	 * @param courseId
	 * @param propertyId
	 * @return
	 */
	public CourseStudentPropertyScore getCourseStudentPropertyScoreByStudentIdPropertyIdCourseId(
			@Param("studentId") long studentId,
			@Param("courseId") long courseId,
			@Param("propertyId") long propertyId);

	/**
	 * 更新
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @param score
	 * @return
	 */
	public int updateCourseStudentPropertyScoreById(@Param("id") long id,
			@Param("score") double score);

	/**
	 * 获取该学生该学期分数列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param studentId
	 * @param semester
	 * @return
	 */
	public List<CourseStudentPropertyScore> getCourseStudentPropertyScoreBySemesterId(
			@Param("studentId") long studentId,
			@Param("semesterId") long semesterId);

}