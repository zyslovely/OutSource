package com.ruoogle.teach.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.eason.web.util.HashMapMaker;
import com.eason.web.util.ListUtils;
import com.ruoogle.teach.mapper.ClassMapper;
import com.ruoogle.teach.mapper.CourseMapper;
import com.ruoogle.teach.mapper.CoursePercentTypeDemoMapper;
import com.ruoogle.teach.mapper.CoursePercentTypeGroupMapper;
import com.ruoogle.teach.mapper.CoursePercentTypeGroupStudentMapper;
import com.ruoogle.teach.mapper.CoursePercentTypeGroupStudentScoreMapper;
import com.ruoogle.teach.mapper.CoursePercentTypeStageMapper;
import com.ruoogle.teach.mapper.CoursePropertyMapper;
import com.ruoogle.teach.mapper.CourseScorePercentMapper;
import com.ruoogle.teach.mapper.CourseScorePercentPropertyMapper;
import com.ruoogle.teach.mapper.CourseStudentMapper;
import com.ruoogle.teach.mapper.CourseStudentPropertyScoreMapper;
import com.ruoogle.teach.mapper.CourseStudentPropertySemesterScoreMapper;
import com.ruoogle.teach.mapper.CourseStudentScoreMapper;
import com.ruoogle.teach.mapper.CourseStudentTotalScoreMapper;
import com.ruoogle.teach.mapper.ProfileMapper;
import com.ruoogle.teach.mapper.ProfilePropertyMapper;
import com.ruoogle.teach.meta.Course;
import com.ruoogle.teach.meta.CourseGroupStudentVO;
import com.ruoogle.teach.meta.CoursePercentTypeDemo;
import com.ruoogle.teach.meta.CoursePercentTypeGroup;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudent;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudentScore;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudentVO;
import com.ruoogle.teach.meta.CoursePercentTypeStage;
import com.ruoogle.teach.meta.CourseProperty;
import com.ruoogle.teach.meta.CourseScorePercent;
import com.ruoogle.teach.meta.CourseScorePercentProperty;
import com.ruoogle.teach.meta.CourseStudent;
import com.ruoogle.teach.meta.CourseStudentPropertyScore;
import com.ruoogle.teach.meta.CourseStudentPropertySemesterScore;
import com.ruoogle.teach.meta.CourseStudentScore;
import com.ruoogle.teach.meta.CourseStudentScoreVO;
import com.ruoogle.teach.meta.CourseStudentTotalScore;
import com.ruoogle.teach.meta.CourseStudentVO;
import com.ruoogle.teach.meta.CourseVO;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.ProfileProperty;
import com.ruoogle.teach.meta.SearchProfile;
import com.ruoogle.teach.meta.SearchProperty;
import com.ruoogle.teach.meta.CoursePercentTypeDemo.CoursePercentType;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudent.GroupLevel;
import com.ruoogle.teach.meta.Profile.ProfileLevel;
import com.ruoogle.teach.service.CourseService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午12:38:00
 * @see Class Description
 */
@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService {
	private static final Logger logger = Logger.getLogger(CourseServiceImpl.class);

	@Resource
	private CourseScorePercentMapper courseScorePercentMapper;
	@Resource
	private ClassMapper classMapper;
	@Resource
	private CourseMapper courseMapper;
	@Resource
	private CourseScorePercentPropertyMapper courseScorePercentPropertyMapper;
	@Resource
	private CourseStudentScoreMapper courseStudentScoreMapper;
	@Resource
	private CourseStudentTotalScoreMapper courseStudentTotalScoreMapper;
	@Resource
	private CourseStudentMapper courseStudentMapper;
	@Resource
	private CoursePropertyMapper coursePropertyMapper;
	@Resource
	private CoursePercentTypeStageMapper coursePercentTypeStageMapper;
	@Resource
	private CoursePercentTypeGroupStudentScoreMapper coursePercentTypeGroupStudentScoreMapper;
	@Resource
	private CoursePercentTypeGroupStudentMapper coursePercentTypeGroupStudentMapper;
	@Resource
	private CoursePercentTypeGroupMapper coursePercentTypeGroupMapper;
	@Resource
	private ProfileMapper profileMapper;
	@Resource
	private CoursePercentTypeDemoMapper coursePercentTypeDemoMapper;
	@Resource
	private CourseStudentPropertyScoreMapper courseStudentPropertyScoreMapper;
	@Resource
	private CourseStudentPropertySemesterScoreMapper courseStudentPropertySemesterScoreMapper;
	@Resource
	private ProfilePropertyMapper profilePropertyMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#addNewCourse(java.lang.String,
	 * java.util.List, long, int, long)
	 */
	@Override
	public boolean addNewCourse(List<CourseScorePercentProperty> courseScorePercentProperties, String courseName,
			List<CourseScorePercent> CourseScorePercents, long classId, long teacherId, long semesterId, String desc) {
		Course course = new Course();
		course.setName(courseName);
		course.setClassId(classId);
		course.setSemester(semesterId);
		course.setTeacherId(teacherId);
		course.setStatus(Course.VALID);
		course.setDescription(desc);
		if (courseMapper.addCourse(course) <= 0) {
			return false;
		}
		Set<Long> teacherIds = new HashSet<Long>();
		teacherIds.add(teacherId);
		for (CourseScorePercent courseScorePercent : CourseScorePercents) {
			courseScorePercent.setCourseId(course.getId());
			if (courseScorePercent.getTeacherId() != 0) {
				// 添加教师
				teacherIds.add(courseScorePercent.getTeacherId());
			}
			courseScorePercentMapper.addCourseScorePercent(courseScorePercent);
		}

		for (CourseScorePercentProperty courseScorePercentProperty : courseScorePercentProperties) {
			courseScorePercentProperty.setCourseId(course.getId());
			courseScorePercentPropertyMapper.addCourseScorePercentProperty(courseScorePercentProperty);
		}
		List<Profile> profileList = profileMapper.getProfileByClassId(classId, ProfileLevel.Student.getValue(), 0, -1);
		for (Profile profile : profileList) {
			CourseStudent courseStudent = new CourseStudent();
			courseStudent.setClassId(classId);
			courseStudent.setUserId(profile.getUserId());
			courseStudent.setType(ProfileLevel.Student.getValue());
			courseStudent.setCourseId(course.getId());
			courseStudent.setSemesterId(semesterId);
			courseStudentMapper.addCourseStudent(courseStudent);
		}
		// 添加教师和企业教师用户
		for (Long userId : teacherIds) {
			Profile profile = profileMapper.getProfile(userId);
			CourseStudent courseStudent = new CourseStudent();
			courseStudent.setClassId(classId);
			courseStudent.setUserId(profile.getUserId());
			courseStudent.setType(profile.getLevel());
			courseStudent.setCourseId(course.getId());
			courseStudent.setSemesterId(semesterId);
			courseStudentMapper.addCourseStudent(courseStudent);
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#insertCourseScore(long,
	 * long, long, double)
	 */
	@Override
	public boolean insertCourseScore(long courseId, long studentId, long percentType, double score, long teacherId) {

		CourseScorePercent courseScorePercent = null;
		// 判断当前教师有没有权限
		if (percentType == CoursePercentType.EachStudent.getValue()) {
			courseScorePercent = courseScorePercentMapper.getCourseScorePercentBypercentType(courseId, percentType);
		} else {
			courseScorePercent = courseScorePercentMapper.getCourseScorePercentByTeacher(teacherId, courseId, percentType);
		}
		if (courseScorePercent == null) {
			logger.error("当前教师有没有权限输入分数");
			return false;
		}

		CourseStudentScore courseStudentScore = courseStudentScoreMapper.getCourseStudentScoreByStudentId(studentId, percentType, courseId);
		if (courseStudentScore == null) {
			courseStudentScore = new CourseStudentScore();
			courseStudentScore.setCourseId(courseId);
			courseStudentScore.setStudentId(studentId);
			courseStudentScore.setPercentType(percentType);
			courseStudentScore.setPercent(courseScorePercent.getPercent());
			courseStudentScore.setScore(score);
			if (courseStudentScoreMapper.addCourseStudentScore(courseStudentScore) <= 0) {
				return false;
			}
		} else {
			courseStudentScore.setScore(score);
			courseStudentScoreMapper.updateCourseStudentScore(courseStudentScore);
		}

		if (this.checkIsAllScoreOut(courseId, studentId)) {
			this.insertCourseStudentProperty(courseId, studentId);
			this.insertCourseStudentTotalScore(courseId, studentId);
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#checkIsAllScoreOut(long,
	 * long)
	 */
	public boolean checkIsAllScoreOut(long courseId, long studentId) {
		List<CourseScorePercent> list = courseScorePercentMapper.getCourseScorePercentListByCourseId(courseId);
		List<CourseStudentScore> courseStudentScores = courseStudentScoreMapper.getCourseStudentScoresByCourseIdStudentId(courseId, studentId);
		if (ListUtils.isEmptyList(list) || ListUtils.isEmptyList(courseStudentScores) || list.size() != courseStudentScores.size()) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#checkIsAllScoreInsertFinished
	 * (long)
	 */
	public boolean checkIsAllScoreInsertFinished(long courseId) {
		List<CourseStudent> courseStudents = courseStudentMapper.getCourseStudentsByCourseId(courseId);
		List<CourseStudentTotalScore> courseStudentTotalScores = courseStudentTotalScoreMapper.getCourseStudentTotalScores(courseId);
		if (ListUtils.isEmptyList(courseStudents) || ListUtils.isEmptyList(courseStudentTotalScores)
				|| courseStudents.size() != courseStudentTotalScores.size()) {
			return false;
		}
		return true;
	}

	/**
	 * 添加这门课程的学生属性值
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param studentId
	 * @param percentType
	 * @return
	 */
	private boolean insertCourseStudentProperty(long courseId, long studentId) {
		Course course = courseMapper.getCourseById(courseId);
		if (course == null) {
			return false;
		}

		List<CourseScorePercentProperty> courseScorePercentProperties = courseScorePercentPropertyMapper
				.getCourseScorePercentPropertyByCourseId(courseId);
		List<CourseStudentScore> courseStudentScores = courseStudentScoreMapper.getCourseStudentScoresByCourseIdStudentId(courseId, studentId);
		if (ListUtils.isEmptyList(courseScorePercentProperties) || ListUtils.isEmptyList(courseStudentScores)) {
			return false;

		}
		Map<Long, CourseStudentScore> courseStudentScoreMap = HashMapMaker.listToMap(courseStudentScores, "getPercentType", CourseStudentScore.class);

		List<CourseProperty> courseProperties = coursePropertyMapper.getAllCourseProperties();
		for (CourseProperty courseProperty : courseProperties) {
			double score = 0;
			for (CourseScorePercentProperty courseScorePercentProperty : courseScorePercentProperties) {
				if (courseScorePercentProperty.getPropertyId() != courseProperty.getId()) {
					continue;
				}
				CourseStudentScore courseStudentScore = courseStudentScoreMap.get(courseScorePercentProperty.getPercentType());
				if (courseStudentScore == null) {
					continue;
				}

				score += courseStudentScore.getScore() / 100 * courseStudentScore.getPercent() / 100;
			}

			CourseStudentPropertyScore courseStudentPropertyScore = courseStudentPropertyScoreMapper
					.getCourseStudentPropertyScoreByStudentIdPropertyIdCourseId(studentId, courseId, courseProperty.getId());
			if (courseStudentPropertyScore == null) {
				courseStudentPropertyScore = new CourseStudentPropertyScore();
				courseStudentPropertyScore.setCourseId(courseId);
				courseStudentPropertyScore.setPropertyId(courseProperty.getId());
				courseStudentPropertyScore.setScore(score);
				courseStudentPropertyScore.setSemesterId(course.getSemester());
				courseStudentPropertyScore.setStudentId(studentId);
				courseStudentPropertyScoreMapper.addCourseStudentPropertyScore(courseStudentPropertyScore);
			} else {
				courseStudentPropertyScore.setScore(score);
				courseStudentPropertyScoreMapper.updateCourseStudentPropertyScoreById(courseStudentPropertyScore.getId(), score);
			}

		}
		return true;
	}

	/**
	 * 计算并插入这个学生在这么课程的总成绩
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param studentId
	 * @return
	 */
	private boolean insertCourseStudentTotalScore(long courseId, long studentId) {
		List<CourseStudentScore> courseStudentScores = courseStudentScoreMapper.getCourseStudentScoresByCourseIdStudentId(courseId, studentId);
		Course course = courseMapper.getCourseById(courseId);
		double score = 0;
		for (CourseStudentScore courseStudentScore : courseStudentScores) {
			score += courseStudentScore.getPercent() / 100 * courseStudentScore.getScore();
		}
		CourseStudentTotalScore courseStudentTotalScore = courseStudentTotalScoreMapper.getCourseStudentTotalScoreByStudentId(courseId, studentId);
		if (courseStudentTotalScore != null) {
			courseStudentTotalScore.setScore(score);
			return courseStudentTotalScoreMapper.updateCourseStudentTotalScore(courseStudentTotalScore.getId(), score) > 0;
		} else {
			courseStudentTotalScore = new CourseStudentTotalScore();
			courseStudentTotalScore.setCourseId(courseId);
			courseStudentTotalScore.setSemester(course.getSemester());
			courseStudentTotalScore.setScore(score);
			courseStudentTotalScore.setStudentId(studentId);
			return courseStudentTotalScoreMapper.addCourseStudentTotalScore(courseStudentTotalScore) > 0;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#insertCourseStageScore(long,
	 * int, double, long, long)
	 */
	@Override
	public boolean insertCourseStageScore(long courseId, int stage, double score, long studentId, long teacherId) {
		List<CourseScorePercent> list = courseScorePercentMapper.getCourseScorePercentListByCourseId(courseId);
		if (ListUtils.isEmptyList(list)) {
			return false;
		}
		boolean succ = false;
		long percentType = -1;
		for (CourseScorePercent courseScorePercent : list) {
			if (courseScorePercent.getPercentType() == CoursePercentType.AvgGrading.getValue() && teacherId == courseScorePercent.getTeacherId()) {
				succ = true;
				percentType = courseScorePercent.getPercentType();
			}
		}
		if (!succ) {
			return false;
		}
		CoursePercentTypeStage coursePercentTypeStage = coursePercentTypeStageMapper.getCoursePercentTypeStage(courseId, studentId, stage,
				percentType);
		if (coursePercentTypeStage == null) {
			coursePercentTypeStage = new CoursePercentTypeStage();
			coursePercentTypeStage.setCourseId(courseId);
			coursePercentTypeStage.setStageIndex(stage);
			coursePercentTypeStage.setScore(score);
			coursePercentTypeStage.setPercentType(percentType);
			coursePercentTypeStage.setStudentId(studentId);
			coursePercentTypeStageMapper.addCoursePercentTypeStage(coursePercentTypeStage);
		} else {
			coursePercentTypeStage.setScore(score);
			coursePercentTypeStageMapper.updateCoursePercentTypeStage(coursePercentTypeStage);
		}
		if (this.checkIsAllStageFinished(courseId, studentId)) {
			double stageTotalScore = 0;
			List<CoursePercentTypeStage> coucoursePercentTypeStages = coursePercentTypeStageMapper.getCoursePercentTypeStageListByStudentId(courseId,
					studentId);
			for (CoursePercentTypeStage stage1 : coucoursePercentTypeStages) {
				stageTotalScore += stage1.getScore();
			}
			this.insertCourseScore(courseId, studentId, percentType, stageTotalScore / coucoursePercentTypeStages.size(), teacherId);
		}
		return true;
	}

	/**
	 * 是否该课程的所有stage已经结束
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param studentId
	 * @return
	 */
	private boolean checkIsAllStageFinished(long courseId, long studentId) {
		List<CoursePercentTypeStage> list = coursePercentTypeStageMapper.getCoursePercentTypeStageListByStudentId(courseId, studentId);
		if (ListUtils.isEmptyList(list)) {
			return false;
		}
		CourseScorePercent courseScorePercent = courseScorePercentMapper.getCourseScorePercentBypercentType(courseId, list.get(0).getPercentType());
		if (courseScorePercent == null || courseScorePercent.getObjectCount() != list.size()) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#addGroupScore(long, long,
	 * long, double, long)
	 */
	@Override
	public boolean addGroupScore(long toStudentId, long courseId, long groupId, double score, long fromStudentId) {

		CoursePercentTypeGroupStudentScore coursePercentTypeGroupStudentScore = coursePercentTypeGroupStudentScoreMapper
				.getCoursePercentTypeGroupStudentScore(courseId, groupId, fromStudentId, toStudentId);
		if (coursePercentTypeGroupStudentScore == null) {
			coursePercentTypeGroupStudentScore = new CoursePercentTypeGroupStudentScore();
			coursePercentTypeGroupStudentScore.setFromStudentId(fromStudentId);
			coursePercentTypeGroupStudentScore.setToStudentId(toStudentId);
			coursePercentTypeGroupStudentScore.setGroupId(groupId);
			coursePercentTypeGroupStudentScore.setScore(score);
			coursePercentTypeGroupStudentScore.setCourseId(courseId);
			coursePercentTypeGroupStudentScoreMapper.addCoursePercentTypeGroupStudentScore(coursePercentTypeGroupStudentScore);
		} else {
			coursePercentTypeGroupStudentScore.setScore(score);
			coursePercentTypeGroupStudentScoreMapper.updateCoursePercentTypeGroupStudentScore(coursePercentTypeGroupStudentScore);
		}
		if (this.checkIfGroupScoreFinished(toStudentId, courseId, groupId)) {
			// 计算分数，然后添加到学生成绩中
			List<CoursePercentTypeGroupStudentScore> list = coursePercentTypeGroupStudentScoreMapper
					.getCoursePercentTypeGroupStudentScoreByToStudent(courseId, groupId, toStudentId);

			CoursePercentTypeGroupStudent toGroupStudent = coursePercentTypeGroupStudentMapper.getCoursePercentTypeGroupStudentByStudentId(
					toStudentId, courseId);
			CoursePercentTypeGroup coursePercentTypeGroup = coursePercentTypeGroupMapper.getCoursePercentTypeGroup(groupId);
			// 获得总分
			double totalScore = 0;
			for (CoursePercentTypeGroupStudentScore studentScore : list) {
				int needDoubleCount = 1;// 如果是队长，需要双倍加分
				if (studentScore.getFromStudentId() == studentScore.getToStudentId()) {
					if (toGroupStudent.getLevel() == GroupLevel.Leader.getValue()) {
						needDoubleCount = 2;
					}
				}
				totalScore += studentScore.getScore() * needDoubleCount;
			}
			double avgScore = 0;
			if (toGroupStudent.getLevel() == GroupLevel.Leader.getValue()) {
				avgScore = totalScore / (coursePercentTypeGroup.getCount() + 1);
			} else {
				avgScore = totalScore / (coursePercentTypeGroup.getCount());
			}
			this.insertCourseScore(courseId, toStudentId, CoursePercentType.EachStudent.getValue(), avgScore, 0);

		}
		return false;
	}

	/**
	 * 判断分组积分是否结束
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	private boolean checkIfGroupScoreFinished(long toStudentId, long courseId, long groupId) {

		List<CoursePercentTypeGroupStudentScore> list = coursePercentTypeGroupStudentScoreMapper.getCoursePercentTypeGroupStudentScoreByToStudent(
				courseId, groupId, toStudentId);
		int count = coursePercentTypeGroupStudentMapper.getCoursePercentTypeGroupStudentCountByIds(courseId, groupId);
		if (ListUtils.isEmptyList(list) || list.size() < count) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#finishCourse(long, long)
	 */
	@Override
	public int finishCourse(long courseId, long teacherid) {
		Course course = courseMapper.getCourseById(courseId);
		if (course == null) {
			return -1;
		}
		List<CourseStudent> courseStudentList = courseStudentMapper.getCourseStudentsByCourseId(courseId);
		List<CourseStudentTotalScore> totalScoreList = courseStudentTotalScoreMapper.getCourseStudentTotalScores(courseId);
		if (totalScoreList.size() < courseStudentList.size()) {
			return 2; // 还有学生的成绩没有录入
		}
		if (courseMapper.finishedCourse(courseId) > 0) {
			courseStudentMapper.updateCourseStudentsStatus(courseId, Course.FINISHED);
			return 1;
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#getCourseListByUserId(long)
	 */
	@Override
	public List<Course> getCourseListByUserId(long userId, int type, long semesterId, int limit, int offset) {

		List<CourseStudent> courseStudents = courseStudentMapper.getCourseStudentsByUserId(userId, type, semesterId, limit, offset);
		if (ListUtils.isEmptyList(courseStudents)) {
			return null;
		}
		List<Long> courseIds = new ArrayList<Long>(courseStudents.size());
		for (CourseStudent courseStudent : courseStudents) {
			courseIds.add(courseStudent.getCourseId());
		}
		return courseMapper.getCourseListByIds(courseIds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#getCourseById(long)
	 */
	@Override
	public Course getCourseById(long courseId) {
		return courseMapper.getCourseById(courseId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#getCoursePercentTypeDemos(int,
	 * int)
	 */
	@Override
	public List<CoursePercentTypeDemo> getCoursePercentTypeDemos(int limit, int offset) {
		return coursePercentTypeDemoMapper.getCoursePercentTypeDemos(limit, offset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#getAllCourseProperties()
	 */
	@Override
	public List<CourseProperty> getAllCourseProperties() {
		return coursePropertyMapper.getAllCourseProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#getCourseVOListByUserId(long,
	 * int, long, int, int)
	 */
	@Override
	public List<CourseVO> getCourseVOListByUserId(long userId, int type, long semesterId, int limit, int offset) {
		List<Course> list = this.getCourseListByUserId(userId, type, semesterId, limit, offset);
		if (ListUtils.isEmptyList(list)) {
			return null;
		}

		Profile userProfile = profileMapper.getProfile(userId);
		List<CourseVO> courseVOs = new ArrayList<CourseVO>();
		for (Course course : list) {
			CourseVO courseVO = new CourseVO();
			courseVO.setCourse(course);
			courseVO.setUser(userProfile);
			com.ruoogle.teach.meta.Class class1 = classMapper.getClassById(course.getClassId());
			courseVO.setClass1(class1);
			if (course.getStatus() == Course.FINISHED) {
				CourseStudentTotalScore courseStudentTotalScore = courseStudentTotalScoreMapper.getCourseStudentTotalScoreByStudentId(course.getId(),
						userId);
				if (courseStudentTotalScore != null) {
					courseVO.setScore(courseStudentTotalScore.getScore());
				}
			} else {
				// 判断是否有分组
				List<CoursePercentTypeGroup> coursePercentTypeGroupList = coursePercentTypeGroupMapper.getCoursePercentTypeGroupByCourseId(course
						.getId());
				if (!ListUtils.isEmptyList(coursePercentTypeGroupList)) {
					if (!this.checkStudentFinishedScoreGroup(userId, course.getId())) {
						courseVO.setHaveGroupToScore(1);
					}
				}
			}
			courseVOs.add(courseVO);
		}

		return courseVOs;
	}

	public boolean checkStudentFinishedScoreGroup(long studentId, long courseId) {

		CoursePercentTypeGroupStudent coursePercentTypeGroupStudent = coursePercentTypeGroupStudentMapper
				.getCoursePercentTypeGroupStudentByStudentId(studentId, courseId);
		if (coursePercentTypeGroupStudent == null) {
			return true;
		}
		CoursePercentTypeGroup coursePercentTypeGroup = coursePercentTypeGroupMapper.getCoursePercentTypeGroup(coursePercentTypeGroupStudent
				.getGroupId());
		if (coursePercentTypeGroup == null) {
			return true;
		}
		List<CoursePercentTypeGroupStudentScore> coursePercentTypeGroupStudentScores = coursePercentTypeGroupStudentScoreMapper
				.getCoursePercentTypeGroupStudentScoreByCourseGroupFrom(courseId, coursePercentTypeGroupStudent.getGroupId(), studentId);
		if (ListUtils.isEmptyList(coursePercentTypeGroupStudentScores)) {
			return false;
		}
		// 如果数量相同，说明评分完成
		if (coursePercentTypeGroupStudentScores.size() == coursePercentTypeGroup.getCount()) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#getCourseScorePercentListByCourseId
	 * (long)
	 */
	@Override
	public List<CourseScorePercent> getCourseScorePercentListByCourseId(long courseId) {
		List<CourseScorePercent> courseScorePercents = courseScorePercentMapper.getCourseScorePercentListByCourseId(courseId);
		if (ListUtils.isEmptyList(courseScorePercents)) {
			return null;
		}
		for (CourseScorePercent courseScorePercent : courseScorePercents) {
			Profile profile = profileMapper.getProfile(courseScorePercent.getTeacherId());
			if (profile != null) {
				courseScorePercent.setTeacherName(profile.getName());
			}
		}
		return courseScorePercents;
	}

	/**
	 * 获取课程学生
	 */
	@Override
	public List<CourseStudentVO> getCourseStudentVOsByCourseId(long courseId, long percentType) {
		List<CourseStudent> courseStudents = courseStudentMapper.getCourseStudentsByCourseId(courseId);
		if (ListUtils.isEmptyList(courseStudents)) {
			return null;
		}
		List<Long> userIds = new ArrayList<Long>(courseStudents.size());
		for (CourseStudent courseStudent : courseStudents) {
			if (courseStudent.getType() != ProfileLevel.Student.getValue()) {
				continue;
			}
			userIds.add(courseStudent.getUserId());
		}
		List<Profile> profileList = profileMapper.getProfileListByIds(userIds);
		Map<Long, Profile> profileMap = HashMapMaker.listToMap(profileList, "getUserId", Profile.class);
		List<CourseStudentScore> courseStudentScoreList = courseStudentScoreMapper.getCourseStudentScoreListByCourseIdPercentType(courseId,
				percentType);
		Map<Long, CourseStudentScore> courseStudentScoreMap = HashMapMaker
				.listToMap(courseStudentScoreList, "getStudentId", CourseStudentScore.class);
		List<CourseStudentVO> courseStudentVOs = new ArrayList<CourseStudentVO>();
		for (CourseStudent courseStudent : courseStudents) {

			CourseStudentVO courseStudentVO = new CourseStudentVO();

			Profile profile = profileMap.get(courseStudent.getUserId());
			if (profile == null) {
				continue;
			}
			CourseStudentScore courseStudentScore = courseStudentScoreMap.get(profile.getUserId());
			if (courseStudentScore != null) {
				courseStudentVO.setScore(courseStudentScore.getScore());
			} else {
				courseStudentVO.setScore(-1);
			}
			courseStudentVO.setUserId(profile.getUserId());
			courseStudentVO.setName(profile.getName());
			courseStudentVOs.add(courseStudentVO);
		}
		return courseStudentVOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#getCourseStudentVOsFromStage(int,
	 * long)
	 */
	@Override
	public List<CourseStudentVO> getCourseStudentVOsFromStage(int stage, long courseId) {
		List<CourseStudent> courseStudents = courseStudentMapper.getCourseStudentsByCourseId(courseId);
		if (ListUtils.isEmptyList(courseStudents)) {
			return null;
		}
		List<Long> userIds = new ArrayList<Long>(courseStudents.size());
		for (CourseStudent courseStudent : courseStudents) {
			if (courseStudent.getType() != ProfileLevel.Student.getValue()) {
				continue;
			}
			userIds.add(courseStudent.getUserId());
		}
		List<Profile> profileList = profileMapper.getProfileListByIds(userIds);
		Map<Long, Profile> profileMap = HashMapMaker.listToMap(profileList, "getUserId", Profile.class);
		List<CoursePercentTypeStage> coursePercentTypeStageList = coursePercentTypeStageMapper.getCoursePercentTypeStageListByCourseIdStage(courseId,
				stage);
		Map<Long, CoursePercentTypeStage> courseStudentScoreMap = HashMapMaker.listToMap(coursePercentTypeStageList, "getStudentId",
				CoursePercentTypeStage.class);
		List<CourseStudentVO> courseStudentVOs = new ArrayList<CourseStudentVO>();
		for (CourseStudent courseStudent : courseStudents) {

			CourseStudentVO courseStudentVO = new CourseStudentVO();

			Profile profile = profileMap.get(courseStudent.getUserId());
			if (profile == null) {
				continue;
			}
			CoursePercentTypeStage coursePercentTypeStage = courseStudentScoreMap.get(profile.getUserId());
			if (coursePercentTypeStage != null) {
				courseStudentVO.setScore(coursePercentTypeStage.getScore());
			} else {
				courseStudentVO.setScore(-1);
			}
			courseStudentVO.setUserId(profile.getUserId());
			courseStudentVO.setName(profile.getName());
			courseStudentVOs.add(courseStudentVO);
		}
		return courseStudentVOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.ruoogle.teach.service.CourseService#
	 * getCourseStudentPropertySemesterScoresByStudentId(long, long)
	 */
	@Override
	public List<CourseStudentPropertySemesterScore> getCourseStudentPropertySemesterScoresByStudentId(long studentId, long semesterId) {

		List<CourseStudent> courseStudentList = courseStudentMapper.getCourseListBySemesterStudentId(semesterId, studentId);
		if (ListUtils.isEmptyList(courseStudentList)) {
			return null;
		}
		List<Long> ids = new ArrayList<Long>();
		for (CourseStudent courseStudent : courseStudentList) {
			ids.add(courseStudent.getCourseId());
		}
		List<Course> courseList = courseMapper.getCourseListByIds(ids);
		if (ListUtils.isEmptyList(courseList)) {
			return null;
		}
		boolean isAllFinished = true;
		for (CourseStudent courseStudent : courseStudentList) {
			if (courseStudent.getStatus() == Course.VALID) {
				isAllFinished = false;
			}
		}
		if (isAllFinished) {
			List<CourseStudentPropertySemesterScore> courseStudentPropertySemesterScores = courseStudentPropertySemesterScoreMapper
					.getCourseStudentPropertySemesterScoreByStudentIdSemester(semesterId, studentId);
			if (ListUtils.isEmptyList(courseStudentPropertySemesterScores)) {
				// 计算一次
				List<CourseStudentPropertyScore> courseStudentPropertyScores = courseStudentPropertyScoreMapper
						.getCourseStudentPropertyScoreBySemesterId(studentId, semesterId);
				Map<Long, Double> map = new HashMap<Long, Double>();
				for (CourseStudentPropertyScore courseStudentPropertyScore : courseStudentPropertyScores) {
					Double scoreDouble = map.get(courseStudentPropertyScore.getPropertyId());
					if (scoreDouble == null) {
						scoreDouble = courseStudentPropertyScore.getScore();
					} else {
						scoreDouble += courseStudentPropertyScore.getScore();
					}
					map.put(courseStudentPropertyScore.getPropertyId(), scoreDouble);
				}
				List<CourseProperty> list = coursePropertyMapper.getAllCourseProperties();
				for (CourseProperty courseProperty : list) {
					CourseStudentPropertySemesterScore courseStudentPropertySemesterScore = new CourseStudentPropertySemesterScore();
					courseStudentPropertySemesterScore.setPropertyId(courseProperty.getId());
					Double scoreDouble = map.get(courseProperty.getId());
					if (scoreDouble != null) {
						courseStudentPropertySemesterScore.setScore(scoreDouble);
					}
					courseStudentPropertySemesterScore.setSemesterId(semesterId);
					courseStudentPropertySemesterScore.setStudentId(studentId);
					courseStudentPropertySemesterScoreMapper.addCourseStudentPropertySemesterScore(courseStudentPropertySemesterScore);

				}
				return courseStudentPropertySemesterScoreMapper.getCourseStudentPropertySemesterScoreByStudentIdSemester(semesterId, studentId);
			} else {
				return courseStudentPropertySemesterScores;
			}
		} else {
			courseStudentPropertySemesterScoreMapper.deleteCourseStudentPropertySemesterScore(studentId, semesterId);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.ruoogle.teach.service.CourseService#
	 * getCoursePercentTypeGroupStudentScoresFromStudentID(long, long)
	 */
	@Override
	public List<CoursePercentTypeGroupStudentVO> getCoursePercentTypeGroupStudentScoresFromStudentID(long fromStudentId, long courseId) {

		CoursePercentTypeGroupStudent coursePercentTypeGroupStudent = coursePercentTypeGroupStudentMapper
				.getCoursePercentTypeGroupStudentByStudentId(fromStudentId, courseId);
		if (coursePercentTypeGroupStudent == null) {
			return null;
		}
		List<CoursePercentTypeGroupStudent> coursePercentTypeGroupStudentList = coursePercentTypeGroupStudentMapper
				.getCoursePercentTypeGroupStudentListByGroupId(courseId, coursePercentTypeGroupStudent.getGroupId());
		if (ListUtils.isEmptyList(coursePercentTypeGroupStudentList)) {
			return null;
		}
		List<Long> ids = new ArrayList<Long>();
		for (CoursePercentTypeGroupStudent coursePercentTypeGroupStudent2 : coursePercentTypeGroupStudentList) {
			ids.add(coursePercentTypeGroupStudent2.getStudentId());
		}
		List<Profile> profileList = profileMapper.getProfileListByIds(ids);
		Map<Long, Profile> profileMap = HashMapMaker.listToMap(profileList, "getUserId", Profile.class);
		List<CoursePercentTypeGroupStudentVO> coursePercentTypeGroupStudentVOs = new ArrayList<CoursePercentTypeGroupStudentVO>();
		for (CoursePercentTypeGroupStudent coursePercentTypeGroupStudent2 : coursePercentTypeGroupStudentList) {
			CoursePercentTypeGroupStudentVO coursePercentTypeGroupStudentVO = new CoursePercentTypeGroupStudentVO();
			coursePercentTypeGroupStudentVO.setGroupId(coursePercentTypeGroupStudent2.getGroupId());
			coursePercentTypeGroupStudentVO.setUserId(coursePercentTypeGroupStudent2.getStudentId());
			Profile profile = profileMap.get(coursePercentTypeGroupStudent2.getStudentId());
			if (profile != null) {
				coursePercentTypeGroupStudentVO.setName(profile.getName());
			}
			CoursePercentTypeGroupStudentScore coursePercentTypeGroupStudentScore = coursePercentTypeGroupStudentScoreMapper
					.getCoursePercentTypeGroupStudentScore(courseId, coursePercentTypeGroupStudent2.getGroupId(), fromStudentId,
							coursePercentTypeGroupStudent2.getStudentId());
			if (coursePercentTypeGroupStudentScore != null) {
				coursePercentTypeGroupStudentVO.setScore(coursePercentTypeGroupStudentScore.getScore());
			}
			coursePercentTypeGroupStudentVOs.add(coursePercentTypeGroupStudentVO);
		}
		return coursePercentTypeGroupStudentVOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#getCourseStudent(long, long)
	 */
	@Override
	public CourseStudent getCourseStudent(long courseId, long studentId) {
		return courseStudentMapper.getCourseStudentByStudentId(courseId, studentId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#getCourseStudentScoreVOsByCourseId
	 * (long)
	 */
	@Override
	public List<CourseStudentScoreVO> getCourseStudentScoreVOsByCourseId(long courseId) {

		List<CourseStudent> courseStudents = courseStudentMapper.getCourseStudentsByCourseId(courseId);
		if (ListUtils.isEmptyList(courseStudents)) {
			return null;
		}
		List<CourseStudentScoreVO> list = new ArrayList<CourseStudentScoreVO>();
		for (CourseStudent courseStudent : courseStudents) {
			CourseStudentScoreVO courseStudentScoreVO = new CourseStudentScoreVO();
			List<CourseStudentScore> scoreList = courseStudentScoreMapper.getCourseStudentScoresByCourseIdStudentId(courseId, courseStudent
					.getUserId());
			courseStudentScoreVO.setScoreList(scoreList);
			CourseStudentTotalScore courseStudentTotalScore = courseStudentTotalScoreMapper.getCourseStudentTotalScoreByStudentId(courseId,
					courseStudent.getUserId());
			courseStudentScoreVO.setTotalScore(courseStudentTotalScore);
			Profile profile = profileMapper.getProfile(courseStudent.getUserId());
			if (profile != null) {
				courseStudentScoreVO.setName(profile.getName());
				courseStudentScoreVO.setUserId(profile.getUserId());
			}
			list.add(courseStudentScoreVO);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#getCourseGroupStudentVOByCourseId
	 * (long)
	 */
	@Override
	public List<CourseGroupStudentVO> getCourseGroupStudentVOByCourseId(long courseId) {

		List<CourseStudent> courseStudents = courseStudentMapper.getCourseStudentsByCourseId(courseId);
		if (ListUtils.isEmptyList(courseStudents)) {
			return null;
		}
		List<Long> ids = new ArrayList<Long>();
		for (CourseStudent courseStudent : courseStudents) {
			ids.add(courseStudent.getUserId());
		}

		List<Profile> profileList = profileMapper.getProfileListByIds(ids);
		Map<Long, Profile> profileMap = HashMapMaker.listToMap(profileList, "getUserId", Profile.class);
		List<CoursePercentTypeGroupStudent> coursePercentTypeGroupStudents = coursePercentTypeGroupStudentMapper
				.getCoursePercentTypeGroupStudentByCourseId(courseId);
		Map<Long, CoursePercentTypeGroupStudent> coursePercentTypeGroupStudentMap = HashMapMaker.listToMap(coursePercentTypeGroupStudents,
				"getStudentId", CoursePercentTypeGroupStudent.class);
		List<CourseGroupStudentVO> courseGroupStudentVOs = new ArrayList<CourseGroupStudentVO>();
		for (CourseStudent courseStudent : courseStudents) {
			CourseGroupStudentVO courseGroupStudentVO = new CourseGroupStudentVO();
			Profile profile = profileMap.get(courseStudent.getUserId());
			if (profile != null) {
				courseGroupStudentVO.setProfile(profile);
			}
			CoursePercentTypeGroupStudent coursePercentTypeGroupStudent = coursePercentTypeGroupStudentMap.get(courseStudent.getUserId());
			if (coursePercentTypeGroupStudent != null) {
				courseGroupStudentVO.setCoursePercentTypeGroupStudent(coursePercentTypeGroupStudent);
			}
			courseGroupStudentVOs.add(courseGroupStudentVO);
		}
		return courseGroupStudentVOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#getCoursePercentTypeGroupsByCourseId
	 * (long)
	 */
	@Override
	public List<CoursePercentTypeGroup> getCoursePercentTypeGroupsByCourseId(long courseId) {
		return coursePercentTypeGroupMapper.getCoursePercentTypeGroupByCourseId(courseId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#deleteCoursePercentTypeGroup(
	 * long)
	 */
	@Override
	public boolean deleteCoursePercentTypeGroup(long groupId) {

		if (coursePercentTypeGroupMapper.deleteCoursePercentTypeGroup(groupId) > 0) {
			return coursePercentTypeGroupStudentMapper.deleteCoursePercentTypeGroupStudent(groupId) > 0;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#addNewGroup(java.util.List,
	 * long)
	 */
	@Override
	public boolean addNewGroup(List<CoursePercentTypeGroupStudent> coursePercentTypeGroupStudents, long courseId) {

		Course course = courseMapper.getCourseById(courseId);
		if (course == null) {
			return false;
		}
		List<CourseStudent> courseStudents = courseStudentMapper.getCourseStudentsByCourseId(courseId);
		if (ListUtils.isEmptyList(courseStudents)) {
			return false;
		}
		CourseScorePercent courseScorePercent = courseScorePercentMapper.getCourseScorePercentBypercentType(courseId, CoursePercentType.EachStudent
				.getValue());
		int nowCount = coursePercentTypeGroupMapper.getCoursePercentTypeGroupCountById(courseId);
		if (nowCount == courseScorePercent.getObjectCount()) {
			return false;
		}

		CoursePercentTypeGroup coursePercentTypeGroup = new CoursePercentTypeGroup();
		coursePercentTypeGroup.setCount(coursePercentTypeGroupStudents.size());
		coursePercentTypeGroup.setCourseId(courseId);
		if (coursePercentTypeGroupMapper.addCoursePercentTypeGroup(coursePercentTypeGroup) > 0) {

			for (CoursePercentTypeGroupStudent coursePercentTypeGroupStudent : coursePercentTypeGroupStudents) {
				coursePercentTypeGroupStudent.setGroupId(coursePercentTypeGroup.getId());
				coursePercentTypeGroupStudent.setCourseId(courseId);
				coursePercentTypeGroupStudentMapper.addCoursePercentTypeGroupStudent(coursePercentTypeGroupStudent);
			}
			return true;
		}
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#getCourseTotalSemesterCount(long,
	 * long)
	 */
	public int getCourseTotalSemesterCount(long userId, long semesterId) {

		List<CourseStudent> courseStudentList = courseStudentMapper.getCourseListBySemesterStudentId(semesterId, userId);
		if (ListUtils.isEmptyList(courseStudentList)) {
			return 0;
		}
		return courseStudentList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#deleteCourseById(long)
	 */
	@Override
	public boolean deleteCourseById(long courseId) {
		Course course = courseMapper.getCourseById(courseId);
		if (course == null) {
			return false;
		}
		if (course.getStatus() == Course.FINISHED) {
			return false;
		}
		courseStudentMapper.deleteCourseStudentByCourse(courseId);
		return courseMapper.deleteCourse(courseId) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#getTheCourseListByUserId(long,
	 * int, int)
	 */
	@Override
	public List<Course> getTheCourseListByUserId(long userId, int limit, int offset) {
		List<CourseStudent> courseStudents = courseStudentMapper.getCourseListByUserId(userId, limit, offset);
		if (ListUtils.isEmptyList(courseStudents)) {
			return null;
		}
		List<Long> courseIds = new ArrayList<Long>(courseStudents.size());
		for (CourseStudent courseStudent : courseStudents) {
			courseIds.add(courseStudent.getCourseId());
		}
		return courseMapper.getCourseListByIds(courseIds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#getSearchProfile(long, long,
	 * java.util.List)
	 */
	@Override
	public List<SearchProfile> getSearchProfile(long semesterId, long classId, List<SearchProperty> searchProperties) {

		List<Profile> profileList = profileMapper.getProfileByClassId(classId, ProfileLevel.Student.getValue(), 0, -1);
		if (ListUtils.isEmptyList(profileList)) {
			return null;
		}
		List<SearchProfile> searchProfiles = new ArrayList<SearchProfile>();
		for (Profile profile : profileList) {
			List<CourseStudentPropertySemesterScore> courseStudentPropertySemesterScores = courseStudentPropertySemesterScoreMapper
					.getCourseStudentPropertySemesterScoreByStudentIdSemester(semesterId, profile.getUserId());
			if (ListUtils.isEmptyList(courseStudentPropertySemesterScores)) {
				continue;
			}
			boolean succ = true;
			for (CourseStudentPropertySemesterScore courseStudentPropertySemesterScore : courseStudentPropertySemesterScores) {
				for (SearchProperty searchProperty : searchProperties) {
					if (searchProperty.getPropertyId() == courseStudentPropertySemesterScore.getPropertyId()) {
						if (searchProperty.getValue() >= courseStudentPropertySemesterScore.getScore()) {
							succ = false;
							break;
						}
					}
				}
				if (!succ) {
					break;
				}
			}
			if (!succ) {
				continue;
			}
			SearchProfile searchProfile = new SearchProfile();
			searchProfile.setProfile(profile);
			searchProfile.setCourseStudentPropertySemesterScoreList(courseStudentPropertySemesterScores);
			searchProfiles.add(searchProfile);
		}
		return searchProfiles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#endStudentSemester(long)
	 */
	@Override
	public boolean endStudentSemester(long userId) {

		List<CourseStudentPropertySemesterScore> courseStudentPropertySemesterScoreList = courseStudentPropertySemesterScoreMapper
				.getCourseStudentPropertySemesterScoreByStudentId(userId);
		if (!ListUtils.isEmptyList(courseStudentPropertySemesterScoreList)) {
			List<CourseProperty> courseProperties = coursePropertyMapper.getAllCourseProperties();
			if (!ListUtils.isEmptyList(courseProperties)) {
				Map<Long, Double> totalScoreMap = new HashMap<Long, Double>();
				for (CourseStudentPropertySemesterScore courseStudentPropertySemesterScore : courseStudentPropertySemesterScoreList) {
					for (CourseProperty courseProperty : courseProperties) {
						if (courseProperty.getId() != courseStudentPropertySemesterScore.getPropertyId()) {
							continue;
						}
						Double score = totalScoreMap.get(courseProperty);
						if (score != null) {
							score += courseStudentPropertySemesterScore.getScore();
						} else {
							score = courseStudentPropertySemesterScore.getScore();
						}
						totalScoreMap.put(courseProperty.getId(), score);
					}
				}
				int semesterCount = courseStudentPropertySemesterScoreMapper.getCourseStudentPropertySemesterCount(userId);
				Iterator<Long> keySetIterator = totalScoreMap.keySet().iterator();
				while (keySetIterator.hasNext()) {
					long propertyId = keySetIterator.next();
					Double score = totalScoreMap.get(propertyId);
					double avgScore = score / semesterCount;
					ProfileProperty profileProperty = new ProfileProperty();
					profileProperty.setUserId(userId);
					Profile profile = profileMapper.getProfile(userId);
					profileProperty.setName(profile.getName());
					profileProperty.setPropertyId(propertyId);
					profileProperty.setScore(avgScore);
					profilePropertyMapper.addProfileProperty(profileProperty);
				}
			}
		}

		return profileMapper.updateProfileStatus(userId, 1) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.CourseService#getLastestSpecialtyId(long,
	 * long)
	 */
	@Override
	public long getLastestSemesterId(long userId) {
		CourseStudent courseStudent = courseStudentMapper.getLastestSemesterId(userId);
		if (courseStudent == null) {
			return 0;
		}
		return courseStudent.getSemesterId();
	}

	@Override
	public List<CourseStudentScore> getCourseStudentScoresByUserIdCourseId(long courseId, long userId) {

		return courseStudentScoreMapper.getCourseStudentScoresByCourseIdStudentId(courseId, userId);
	}

	@Override
	public List<CoursePercentTypeStage> getCoursePercentTypeStageListByCourseId(long courseId, long userId) {
		return coursePercentTypeStageMapper.getCoursePercentTypeStageListByStudentId(courseId, userId);
	}
}
