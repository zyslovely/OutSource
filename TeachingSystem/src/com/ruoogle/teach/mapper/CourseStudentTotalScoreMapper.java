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

	/**
	 * 得到某个学生某门课的总成绩
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param studentId
	 * @return
	 */
	public CourseStudentTotalScore getCourseStudentTotalScoreByStudentId(@Param(value = "courseId") long courseId,
			@Param(value = "studentId") long studentId);

	/**
	 * 更新学生成绩
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param studentId
	 * @return
	 */
	public int updateCourseStudentTotalScore(@Param(value = "id") long id, @Param(value = "score") double score);

}