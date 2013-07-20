package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CoursePercentTypeStage;

public interface CoursePercentTypeStageMapper {
	public int addCoursePercentTypeStage(
			CoursePercentTypeStage coursePercentTypeStage);

	/**
	 * 更新课程状态
	 * 
	 * @auther zyslovely@gmail.com
	 * @param coursePercentTypeStage
	 * @return
	 */
	public int updateCoursePercentTypeStage(
			CoursePercentTypeStage coursePercentTypeStage);

	/**
	 * 获取
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public CoursePercentTypeStage getCoursePercentTypeStage(
			@Param(value = "courseId") long courseId,
			@Param(value = "studentId") long studentId,
			@Param(value = "stageIndex") long stageIndex,
			@Param(value = "percentType") long percentType);

	/**
	 * 得到该学生的分数
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param studentId
	 * @return
	 */
	public List<CoursePercentTypeStage> getCoursePercentTypeStageListByStudentId(
			@Param(value = "courseId") long courseId,
			@Param(value = "studentId") long studentId);

	/**
	 * 获得stage的学生分数列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param stage
	 * @return
	 */
	public List<CoursePercentTypeStage> getCoursePercentTypeStageListByCourseIdStage(
			@Param(value = "courseId") long courseId,
			@Param(value = "stage") int stage);
}