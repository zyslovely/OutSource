package com.ruoogle.teach.dwr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import com.ruoogle.teach.meta.Course;
import com.ruoogle.teach.meta.CourseScorePercent;
import com.ruoogle.teach.meta.CourseScorePercentProperty;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudent.GroupLevel;
import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.ClassService;
import com.ruoogle.teach.service.CourseService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 下午09:19:55
 * @see Class Description
 */
@Service("dwrTeachSysTeacherBean")
public class DwrTeachSysTeacherBean {
	private static final Logger logger = Logger.getLogger(DwrTeachSysTeacherBean.class);
	@Resource
	private CourseService courseService;
	@Resource
	private ClassService classService;

	/**
	 * 添加新的课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseName
	 * @param coursePercentTypes
	 * @param classId
	 */
	public boolean addNewCourse(String courseName, CourseScorePercent CourseScorePercents[], long semesterId, long classId,
			CourseScorePercentProperty courseScorePercentProperties[], String desc) {
		WebContext ctx = WebContextFactory.get();
		Long teacherId = MyUser.getMyUser(ctx.getHttpServletRequest());
		return courseService.addNewCourse(Arrays.asList(courseScorePercentProperties), courseName, Arrays.asList(CourseScorePercents), classId,
				teacherId, semesterId, desc);
	}

	/**
	 * 添加学生课程分数
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseStudentScores
	 * @return
	 */
	public boolean insertCourseScore(long courseId, long studentId, long percentType, double score) {
		WebContext ctx = WebContextFactory.get();
		Long teacherId = MyUser.getMyUser(ctx.getHttpServletRequest());
		return courseService.insertCourseScore(courseId, studentId, percentType, score, teacherId);

	}

	/**
	 * 插入阶段课程分数
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param stage
	 * @param score
	 * @param studentId
	 * @return
	 */
	public boolean insertCourseStageScore(long courseId, int stage, double score, long studentId) {
		WebContext ctx = WebContextFactory.get();
		Long teacherId = MyUser.getMyUser(ctx.getHttpServletRequest());
		return courseService.insertCourseStageScore(courseId, stage, score, studentId, teacherId);
	}

	/**
	 * 添加分组
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @param studentId
	 */
	public boolean addCourseGroup(long courseId, long studentId, long groupId, int leader) {
		WebContext ctx = WebContextFactory.get();
		Long teacherId = MyUser.getMyUser(ctx.getHttpServletRequest());
		return courseService.addCourseGroup(courseId, studentId, groupId, teacherId, GroupLevel.genGroupLevel(leader));
	}

	/**
	 * 结束课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public boolean finishCourse(long courseId) {
		WebContext ctx = WebContextFactory.get();
		Long teacherId = MyUser.getMyUser(ctx.getHttpServletRequest());
		return courseService.finishCourse(courseId, teacherId);
	}

	/**
	 * 所有成绩录入完成
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseId
	 * @return
	 */
	public boolean IsAllScoreInsertFinished(long courseId) {
		return courseService.checkIsAllScoreInsertFinished(courseId);
	}

	/**
	 * 获取班级列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param specialtyId
	 * @return
	 */
	public List<com.ruoogle.teach.meta.Class> getClassListBySpecialty(long specialtyId) {
		return classService.getClassListBySpecialty(specialtyId);
	}

	public List<Course> getList() {
		List<Course> courses = new ArrayList<Course>();
		Course course = new Course();
		course.setClassId(1);
		courses.add(course);
		return courses;
	}
}
