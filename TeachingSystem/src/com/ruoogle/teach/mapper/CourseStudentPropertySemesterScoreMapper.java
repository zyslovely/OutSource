package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CourseStudentPropertySemesterScore;

public interface CourseStudentPropertySemesterScoreMapper {
	public int addCourseStudentPropertySemesterScore(CourseStudentPropertySemesterScore courseStudentPropertySemesterScore);

	/**
	 * 获取列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param semesterId
	 * @param studentId
	 * @return
	 */
	public List<CourseStudentPropertySemesterScore> getCourseStudentPropertySemesterScoreByStudentIdSemester(
			@Param(value = "semesterId") long semesterId, @Param(value = "studentId") long studentId);

	/**
	 * 获取学生学期数
	 * 
	 * @auther zyslovely@gmail.com
	 * @param studentId
	 * @return
	 */
	public int getCourseStudentPropertySemesterCount(@Param(value = "studentId") long studentId);

	/**
	 * 获取学生的所有
	 * 
	 * @auther zyslovely@gmail.com
	 * @param studentId
	 * @return
	 */
	public List<CourseStudentPropertySemesterScore> getCourseStudentPropertySemesterScoreByStudentId(@Param(value = "studentId") long studentId);

	/**
	 * 更新
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseStudentPropertySemesterScore
	 * @return
	 */
	public int updateCourseStudentPropertySemesterScore(CourseStudentPropertySemesterScore courseStudentPropertySemesterScore);

	/**
	 * 删除
	 * 
	 * @auther zyslovely@gmail.com
	 * @param studentId
	 * @param semesterId
	 * @return
	 */
	public int deleteCourseStudentPropertySemesterScore(@Param("studentId") long studentId, @Param("semesterId") long semesterId);
}