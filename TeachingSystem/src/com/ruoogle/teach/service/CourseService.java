package com.ruoogle.teach.service;

import java.util.List;

import com.ruoogle.teach.meta.Course;
import com.ruoogle.teach.meta.CoursePercentTypeDemo;
import com.ruoogle.teach.meta.CourseProperty;
import com.ruoogle.teach.meta.CourseScorePercent;
import com.ruoogle.teach.meta.CourseScorePercentProperty;
import com.ruoogle.teach.meta.CourseVO;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudent.GroupLevel;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午12:37:48
 * @see Class Description
 */
public interface CourseService {

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
			List<CourseScorePercent> CourseScorePercents, long classId, long teacherId, long semesterId, String desc);

	/**
	 * 添加学生分类分数
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseStudentScores
	 * @param teacherId
	 * @return
	 */
	public boolean insertCourseScore(long courseId, long studentId, long percentType, double score, long teacherId);

	/**
	 * 判断这个学生的分数是不是都出来了
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param studentId
	 * @return
	 */
	public boolean checkIsAllScoreOut(long courseId, long studentId);

	/**
	 * 这门课程成绩是否都录入完成
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public boolean checkIsAllScoreInsertFinished(long courseId);

	/**
	 * 插入阶段性课程分数
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param stage
	 * @param score
	 * @param studentId
	 * @param teacherId
	 * @return
	 */
	public boolean insertCourseStageScore(long courseId, int stage, double score, long studentId, long teacherId);

	/**
	 * 添加分组
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param studentId
	 * @param groupId
	 */
	public boolean addCourseGroup(long courseId, long studentId, long groupId, long teacherId, GroupLevel level);

	/**
	 * 添加分组分数
	 * 
	 * @auther zyslovely@gmail.com
	 * @param toStudentId
	 * @param courseId
	 * @param groupId
	 * @param score
	 * @param fromStudentId
	 * @return
	 */
	public boolean addGroupScore(long toStudentId, long courseId, long groupId, double score, long fromStudentId, long percentType);

	/**
	 * 完成课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param teacherid
	 * @return
	 */
	public boolean finishCourse(long courseId, long teacherid);

	/**
	 * 获取课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public List<Course> getCourseListByUserId(long userId, int type, long semesterId, int limit, int offset);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @param type
	 * @param semesterId
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<CourseVO> getCourseVOListByUserId(long userId, int type, long semesterId, int limit, int offset);

	/**
	 * 获取课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public Course getCourseById(long courseId);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<CoursePercentTypeDemo> getCoursePercentTypeDemos(int limit, int offset);

	public List<CourseProperty> getAllCourseProperties();

	/**
	 * 得到类型
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public List<CourseScorePercent> getCourseScorePercentListByCourseId(long courseId);
}
