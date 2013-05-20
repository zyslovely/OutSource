package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.CourseStudentTotalScore;

public interface CourseStudentTotalScoreMapper {
	public int addCourseStudentTotalScore(CourseStudentTotalScore courseStudentTotalScore);

	/**
	 * 获取课程学生成绩列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public List<CourseStudentTotalScore> getCourseStudentTotalScores(@Param(value = "courseId") long courseId);
}