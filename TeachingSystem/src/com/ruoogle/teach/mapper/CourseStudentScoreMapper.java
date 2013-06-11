package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CourseStudentScore;

public interface CourseStudentScoreMapper {
	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseStudentScore
	 * @return
	 */
	public int addCourseStudentScore(CourseStudentScore courseStudentScore);

	/**
	 * 根据学生获得课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param studentId
	 * @param percentType
	 * @param courseId
	 * @return
	 */
	public CourseStudentScore getCourseStudentScoreByStudentId(@Param(value = "studentId") long studentId,
			@Param(value = "percentType") long percentType, @Param(value = "courseId") long courseId);

	/**
	 * 得到这个学生的课程得分列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param studentId
	 * @return
	 */
	public List<CourseStudentScore> getCourseStudentScoresByCourseIdStudentId(@Param(value = "courseId") long courseId,
			@Param(value = "studentId") long studentId);

	/**
	 * 更新分数
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseStudentScore
	 * @return
	 */
	public int updateCourseStudentScore(CourseStudentScore courseStudentScore);

	/**
	 * 通过评分标准和课程获取对应的列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public List<CourseStudentScore> getCourseStudentScoreListByCourseIdPercentType(@Param(value = "courseId") long courseId,
			@Param(value = "percentType") long percentType);
}