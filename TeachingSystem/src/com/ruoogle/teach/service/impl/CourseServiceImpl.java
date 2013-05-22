package com.ruoogle.teach.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.eason.web.util.HashMapMaker;
import com.eason.web.util.ListUtils;
import com.ruoogle.teach.mapper.CourseMapper;
import com.ruoogle.teach.mapper.CoursePercentTypeGroupMapper;
import com.ruoogle.teach.mapper.CoursePercentTypeGroupStudentMapper;
import com.ruoogle.teach.mapper.CoursePercentTypeGroupStudentScoreMapper;
import com.ruoogle.teach.mapper.CoursePercentTypeMapper;
import com.ruoogle.teach.mapper.CoursePercentTypeStageMapper;
import com.ruoogle.teach.mapper.CoursePropertyMapper;
import com.ruoogle.teach.mapper.CourseScorePercentMapper;
import com.ruoogle.teach.mapper.CourseScorePercentPropertyMapper;
import com.ruoogle.teach.mapper.CourseStudentMapper;
import com.ruoogle.teach.mapper.CourseStudentScoreMapper;
import com.ruoogle.teach.mapper.CourseStudentTotalScoreMapper;
import com.ruoogle.teach.meta.Course;
import com.ruoogle.teach.meta.CoursePercentType;
import com.ruoogle.teach.meta.CoursePercentTypeGroup;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudent;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudentScore;
import com.ruoogle.teach.meta.CoursePercentTypeStage;
import com.ruoogle.teach.meta.CourseProperty;
import com.ruoogle.teach.meta.CourseScorePercent;
import com.ruoogle.teach.meta.CourseScorePercentProperty;
import com.ruoogle.teach.meta.CourseStudent;
import com.ruoogle.teach.meta.CourseStudentScore;
import com.ruoogle.teach.meta.CourseStudentTotalScore;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudent.GroupLevel;
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
	private CoursePercentTypeMapper coursePercentTypeMapper;

	@Resource
	private CourseScorePercentMapper courseScorePercentMapper;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#addNewCoursePercentType(java.
	 * lang.String, java.lang.String)
	 */
	public boolean addNewCoursePercentType(String name, String desc) {
		CoursePercentType coursePercentType = new CoursePercentType();
		coursePercentType.setName(name);
		coursePercentType.setDesc(desc);
		return coursePercentTypeMapper.addCoursePercentType(coursePercentType) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#addNewCourse(java.lang.String,
	 * java.util.List, long, int, long)
	 */
	@Override
	public boolean addNewCourse(List<CourseScorePercentProperty> courseScorePercentProperties, String courseName,
			List<CourseScorePercent> CourseScorePercents, long classId, int year, long teacherId, List<Long> studentIds) {
		Course course = new Course();
		course.setName(courseName);
		course.setClassId(classId);
		course.setSemester(year);
		course.setTeacherId(teacherId);
		course.setStatus(Course.VALID);
		if (courseMapper.addCourse(course) <= 0) {
			return false;
		}

		for (CourseScorePercent courseScorePercent : CourseScorePercents) {
			courseScorePercent.setCourseId(course.getId());
			courseScorePercentMapper.addCourseScorePercent(courseScorePercent);
		}

		for (CourseScorePercentProperty courseScorePercentProperty : courseScorePercentProperties) {
			courseScorePercentProperty.setCourseId(course.getId());
			courseScorePercentPropertyMapper.addCourseScorePercentProperty(courseScorePercentProperty);
		}
		for (Long studentId : studentIds) {
			CourseStudent courseStudent = new CourseStudent();
			courseStudent.setClassId(classId);
			courseStudent.setSemester(year);
			courseStudent.setStudentId(studentId);
			courseStudent.setCourseId(course.getId());
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

		// 判断当前老师有没有权限
		CourseScorePercent courseScorePercent = courseScorePercentMapper.getCourseScorePercentByTeacher(teacherId, courseId, percentType);
		if (courseScorePercent == null) {
			logger.error("当前老师有没有权限输入分数");
			return false;
		}
		CourseStudentScore courseStudentScore = courseStudentScoreMapper.getCourseStudentScoreByStudentId(studentId, percentType, courseId);
		if (courseStudentScore == null) {
			courseStudentScore = new CourseStudentScore();
			courseStudentScore.setCourseId(courseId);
			courseStudentScore.setStudentId(studentId);
			courseStudentScore.setPercentType(percentType);
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

		return false;
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
		// 未完成
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
			double percent = 0;
			for (CourseScorePercentProperty courseScorePercentProperty : courseScorePercentProperties) {
				CourseStudentScore courseStudentScore = courseStudentScoreMap.get(courseScorePercentProperty.getPercentType());
				score += courseStudentScore.getScore() * courseStudentScore.getPercent();
				percent += courseStudentScore.getPercent();
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
			score = courseStudentScore.getPercent() * courseStudentScore.getScore();
		}
		CourseStudentTotalScore courseStudentTotalScore = new CourseStudentTotalScore();
		courseStudentTotalScore.setCourseId(courseId);
		courseStudentTotalScore.setSemester(course.getSemester());
		courseStudentTotalScore.setScore(score);
		courseStudentTotalScore.setStudentId(studentId);
		return courseStudentTotalScoreMapper.addCourseStudentTotalScore(courseStudentTotalScore) > 0;

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
			if (courseScorePercent.getPercentType() == CourseScorePercent.PercentType_Stage && teacherId == courseScorePercent.getTeacherId()) {
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
	 * @see com.ruoogle.teach.service.CourseService#addCourseGroup(long, long,
	 * long, long)
	 */
	@Override
	public boolean addCourseGroup(long courseId, long studentId, long groupId, long teacherId, GroupLevel level) {

		CoursePercentTypeGroupStudent coursePercentTypeGroupStudent = coursePercentTypeGroupStudentMapper.getCoursePercentTypeGroupStudentByIds(
				studentId, courseId);
		if ((coursePercentTypeGroupStudent != null && groupId < 0) || (coursePercentTypeGroupStudent == null && groupId > 0)) {
			return false;
		}
		if (groupId < 0) {
			CoursePercentTypeGroup coursePercentTypeGroup = coursePercentTypeGroupMapper.getCoursePercentTypeGroup(courseId);
			int count = coursePercentTypeGroupStudentMapper.getCoursePercentTypeGroupCountByIds(courseId);
			if (coursePercentTypeGroup == null || (count + 1 >= coursePercentTypeGroup.getCount())) {
				return false;
			}
			coursePercentTypeGroupStudent = new CoursePercentTypeGroupStudent();
			coursePercentTypeGroupStudent.setCourseId(courseId);
			coursePercentTypeGroupStudent.setGroupId(groupId);
			coursePercentTypeGroupStudent.setLevel(level.getValue());
			coursePercentTypeGroupStudent.setStudentId(studentId);
			return coursePercentTypeGroupStudentMapper.addCoursePercentTypeGroupStudent(coursePercentTypeGroupStudent) > 0;
		} else {
			coursePercentTypeGroupStudent.setGroupId(groupId);
			coursePercentTypeGroupStudent.setLevel(level.getValue());
			return coursePercentTypeGroupStudentMapper.updateCoursePercentTypeGroupStudentMapper(coursePercentTypeGroupStudent) > 0;
		}
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
			return coursePercentTypeGroupStudentScoreMapper.addCoursePercentTypeGroupStudentScore(coursePercentTypeGroupStudentScore) > 0;
		} else {

		}
		return false;
	}

	private checkIfGroupScoreFinished() {

	}
}
