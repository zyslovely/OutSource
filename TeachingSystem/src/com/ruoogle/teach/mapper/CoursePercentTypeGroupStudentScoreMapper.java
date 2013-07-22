package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CoursePercentTypeGroupStudentScore;

public interface CoursePercentTypeGroupStudentScoreMapper {
	public int addCoursePercentTypeGroupStudentScore(
			CoursePercentTypeGroupStudentScore coursePercentTypeGroupStudentScore);

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
	public CoursePercentTypeGroupStudentScore getCoursePercentTypeGroupStudentScore(
			@Param(value = "courseId") long courseId,
			@Param(value = "groupId") long groupId,
			@Param(value = "fromStudentId") long fromStudentId,
			@Param(value = "toStudentId") long toStudentId);

	/**
	 * 获取
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param groupId
	 * @param toStudentId
	 * @return
	 */
	public List<CoursePercentTypeGroupStudentScore> getCoursePercentTypeGroupStudentScoreByToStudent(
			@Param(value = "courseId") long courseId,
			@Param(value = "groupId") long groupId,
			@Param(value = "toStudentId") long toStudentId);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param groupId
	 * @return
	 */
	public List<CoursePercentTypeGroupStudentScore> getCoursePercentTypeGroupStudentScoreByCourseGroup(
			@Param(value = "courseId") long courseId,
			@Param(value = "groupId") long groupId);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param groupId
	 * @param fromStudentId
	 * @return
	 */
	public List<CoursePercentTypeGroupStudentScore> getCoursePercentTypeGroupStudentScoreByCourseGroupFrom(
			@Param(value = "courseId") long courseId,
			@Param(value = "groupId") long groupId,
			@Param(value = "fromStudentId") long fromStudentId);

	/**
	 * 更新
	 * 
	 * @auther zyslovely@gmail.com
	 * @param coursePercentTypeGroupStudentScore
	 * @return
	 */
	public int updateCoursePercentTypeGroupStudentScore(
			CoursePercentTypeGroupStudentScore coursePercentTypeGroupStudentScore);
}