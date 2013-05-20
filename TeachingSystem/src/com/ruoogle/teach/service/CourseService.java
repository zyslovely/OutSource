package com.ruoogle.teach.service;

import java.util.List;

import com.ruoogle.teach.meta.CourseScorePercent;
import com.ruoogle.teach.meta.CourseScorePercentProperty;
import com.ruoogle.teach.meta.CourseStudentScore;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午12:37:48
 * @see Class Description
 */
public interface CourseService {
	/**
	 * 添加评分类型
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param desc
	 * @return
	 */
	public boolean addNewCoursePercentType(String name, String desc);

	/**
	 * 添加新的课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseName
	 * @param coursePercentTypes
	 * @param classId
	 * @param year
	 * @param teacherId
	 * @return
	 */
	public boolean addNewCourse(List<CourseScorePercentProperty> courseScorePercentProperties, String courseName,
			List<CourseScorePercent> CourseScorePercents, long classId, int year, long teacherId, List<Long> studentIds);

	/**
	 * 添加学生分类分数
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseStudentScores
	 * @param teacherId
	 * @return
	 */
	public boolean insertCourseScore(long courseId, long studentId, long percentType, double score, long teacherId);
}
