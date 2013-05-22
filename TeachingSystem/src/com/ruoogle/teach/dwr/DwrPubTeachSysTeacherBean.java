package com.ruoogle.teach.dwr;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import com.ruoogle.teach.meta.CourseScorePercent;
import com.ruoogle.teach.meta.CourseScorePercentProperty;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudent.GroupLevel;
import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.CourseService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 下午09:19:55
 * @see Class Description
 */
@Service("dwrPubTeachSysTeacherBean")
public class DwrPubTeachSysTeacherBean {
	private static final Logger logger = Logger.getLogger(DwrPubTeachSysTeacherBean.class);
	@Resource
	private CourseService courseService;

	/**
	 * 添加新的课程
	 * 
	 * @auther zyslovely@gmail.com
	 * @param courseName
	 * @param coursePercentTypes
	 * @param classId
	 */
	public boolean addNewCourse(String courseName, List<CourseScorePercent> CourseScorePercents, long classId, int year,
			List<CourseScorePercentProperty> courseScorePercentProperties, List<Long> studentIds) {
		WebContext ctx = WebContextFactory.get();
		Long teacherId = MyUser.getMyUser(ctx.getHttpServletRequest());
		return courseService.addNewCourse(courseScorePercentProperties, courseName, CourseScorePercents, classId, year, teacherId, studentIds);
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
}
