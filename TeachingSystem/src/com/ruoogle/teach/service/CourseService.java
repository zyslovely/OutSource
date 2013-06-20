package com.ruoogle.teach.service;

import java.util.List;

import com.ruoogle.teach.meta.Course;
import com.ruoogle.teach.meta.CourseGroupStudentVO;
import com.ruoogle.teach.meta.CoursePercentTypeDemo;
import com.ruoogle.teach.meta.CoursePercentTypeGroup;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudent;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudentVO;
import com.ruoogle.teach.meta.CoursePercentTypeStage;
import com.ruoogle.teach.meta.CourseProperty;
import com.ruoogle.teach.meta.CourseScorePercent;
import com.ruoogle.teach.meta.CourseScorePercentProperty;
import com.ruoogle.teach.meta.CourseStudent;
import com.ruoogle.teach.meta.CourseStudentPropertySemesterScore;
import com.ruoogle.teach.meta.CourseStudentScore;
import com.ruoogle.teach.meta.CourseStudentScoreVO;
import com.ruoogle.teach.meta.CourseStudentVO;
import com.ruoogle.teach.meta.CourseVO;
import com.ruoogle.teach.meta.SearchProfile;
import com.ruoogle.teach.meta.SearchProperty;

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
	public boolean addGroupScore(long toStudentId, long courseId, long groupId, double score, long fromStudentId);

	/**
	 * 完成课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param teacherid
	 * @return
	 */
	public int finishCourse(long courseId, long teacherid);

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
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<Course> getTheCourseListByUserId(long userId, int limit, int offset);

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

	/**
	 * 得到这门课的学生列表，以及分数
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public List<CourseStudentVO> getCourseStudentVOsByCourseId(long courseId, long percentType);

	/**
	 * 根据stage获取课程分数
	 * 
	 * @auther zyslovely@gmail.com
	 * @param stage
	 * @param courseId
	 * @return
	 */
	public List<CourseStudentVO> getCourseStudentVOsFromStage(int stage, long courseId);

	/**
	 * 获得这个学生这个学期的属性
	 * 
	 * @auther zyslovely@gmail.com
	 * @param studentId
	 * @param semesterId
	 * @return
	 */
	public List<CourseStudentPropertySemesterScore> getCourseStudentPropertySemesterScoresByStudentId(long studentId, long semesterId);

	/**
	 * 获取互评学生列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param fromStudentId
	 * @param courseId
	 * @return
	 */
	public List<CoursePercentTypeGroupStudentVO> getCoursePercentTypeGroupStudentScoresFromStudentID(long fromStudentId, long courseId);

	public CourseStudent getCourseStudent(long courseId, long studentId);

	/**
	 * 获取成绩列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public List<CourseStudentScoreVO> getCourseStudentScoreVOsByCourseId(long courseId);

	/**
	 * 获得课程学生分组
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public List<CourseGroupStudentVO> getCourseGroupStudentVOByCourseId(long courseId);

	/**
	 * 获取分组列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public List<CoursePercentTypeGroup> getCoursePercentTypeGroupsByCourseId(long courseId);

	/**
	 * 删除分组
	 */
	public boolean deleteCoursePercentTypeGroup(long groupId);

	/**
	 * 添加新分组
	 * 
	 * @auther zyslovely@gmail.com
	 * @param coursePercentTypeGroupStudents
	 * @param courseId
	 * @return
	 */
	public boolean addNewGroup(List<CoursePercentTypeGroupStudent> coursePercentTypeGroupStudents, long courseId);

	/**
	 * 得到某个学期课程总数
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @param semesterId
	 * @return
	 */
	public int getCourseTotalSemesterCount(long userId, long semesterId);

	/**
	 * 删除课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public boolean deleteCourseById(long courseId);

	/**
	 * 搜索
	 * 
	 * @auther zyslovely@gmail.com
	 * @param semesterId
	 * @param classId
	 * @param searchProperties
	 * @return
	 */
	public List<SearchProfile> getSearchProfile(long semesterId, long classId, List<SearchProperty> searchProperties);

	/**
	 * 结束学业
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public boolean endStudentSemester(long userId);

	/**
	 * 获得最近的专业id
	 * 
	 * @auther zyslovely@gmail.com
	 * @param specialtyId
	 * @param userId
	 * @return
	 */
	public long getLastestSemesterId(long userId);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param userId
	 * @return
	 */
	public List<CourseStudentScore> getCourseStudentScoresByUserIdCourseId(long courseId, long userId);

	public List<CoursePercentTypeStage> getCoursePercentTypeStageListByCourseId(long courseId, long userId);
}
