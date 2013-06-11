package com.ruoogle.teach.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.ruoogle.teach.meta.Course;
import com.ruoogle.teach.meta.CoursePercentTypeDemo;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudentVO;
import com.ruoogle.teach.meta.CourseProperty;
import com.ruoogle.teach.meta.CourseScorePercent;
import com.ruoogle.teach.meta.CourseStudentPropertySemesterScore;
import com.ruoogle.teach.meta.CourseStudentVO;
import com.ruoogle.teach.meta.CourseVO;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.Semester;
import com.ruoogle.teach.meta.CoursePercentTypeDemo.CoursePercentType;
import com.ruoogle.teach.meta.Profile.ProfileLevel;
import com.ruoogle.teach.security.MySecurityDelegatingFilter;
import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.ClassService;
import com.ruoogle.teach.service.CourseService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-17 上午12:16:41
 * @see Class Description
 */
@Controller("webTeachSysController")
public class WebTeachSysController extends AbstractBaseController {
	private static final Logger logger = Logger.getLogger(WebTeachSysController.class);

	@Resource
	private CourseService courseService;
	@Resource
	private ClassService classService;

	/**
	 * 退出
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Long userId = MyUser.getMyUser(request);
		MySecurityDelegatingFilter.userMap.remove(userId);
		HttpSession session = request.getSession();
		session.removeAttribute("login");
		session.removeAttribute("userId");
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 登陆后首页
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView teachIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("teachIndex");
		Long userId = MyUser.getMyUser(request);
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		long semesterId = ServletRequestUtils.getLongParameter(request, "semesterId", -1L);
		List<Semester> semesters = classService.getAllSemesters();
		mv.addObject("semesters", semesters);
		mv.addObject("semesterId", semesterId);
		this.setUD(mv, request);
		if (semesterId < 0) {
			return mv;
		}
		List<CourseVO> courseList = courseService.getCourseVOListByUserId(userId, myUser.getLevel(), semesterId, 0, -1);
		if (myUser.getLevel() == ProfileLevel.Student.getValue()) {
			List<CourseStudentPropertySemesterScore> courseStudentPropertySemesterScores = courseService
					.getCourseStudentPropertySemesterScoresByStudentId(myUser.getUserId(), semesterId);
			mv.addObject("courseStudentPropertySemesterScores", courseStudentPropertySemesterScores);
			List<CourseProperty> coursePropertieList = courseService.getAllCourseProperties();
			mv.addObject("coursePropertyList", coursePropertieList);
		}
		mv.addObject("courseList", courseList);

		return mv;
	}

	/**
	 * 创建课程页面
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView teachCreate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("teachCreate");

		List<CoursePercentTypeDemo> coursePercentTypeDemos = courseService.getCoursePercentTypeDemos(0, -1);
		mv.addObject("coursePercentTypeDemos", coursePercentTypeDemos);
		List<Profile> teacherProfiles = profileService.getProfileList(ProfileLevel.Teacher.getValue(), 0, -1);
		mv.addObject("teacherProfiles", teacherProfiles);
		List<CourseProperty> courseProperties = courseService.getAllCourseProperties();
		mv.addObject("courseProperties", courseProperties);
		List<com.ruoogle.teach.meta.Class> classList = classService.getAllClass();
		mv.addObject("classList", classList);
		List<Semester> semesters = classService.getAllSemesters();
		mv.addObject("semesters", semesters);
		this.setUD(mv, request);
		return mv;
	}

	/**
	 * 课程信息页
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showCourseView(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("CourseInfo");

		long courseId = ServletRequestUtils.getLongParameter(request, "courseId", -1L);
		if (courseId < 0) {
			try {
				response.sendRedirect("/teach/index/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		Course course = courseService.getCourseById(courseId);
		mv.addObject("course", course);
		mv.addObject("courseId", courseId);
		
		boolean isEachStudent = false;
		List<CourseScorePercent> courseScorePercents = courseService.getCourseScorePercentListByCourseId(courseId);
		for (CourseScorePercent courseScorePercent : courseScorePercents) {
			if ((int) courseScorePercent.getPercentType() == CoursePercentType.EachStudent.getValue()) {
				isEachStudent = true;
			}
			courseScorePercent.setPercent(courseScorePercent.getPercent());
		}
		mv.addObject("isEachStudent", isEachStudent ? 1 : 0);
		mv.addObject("courseScorePercents", courseScorePercents);
		this.setUD(mv, request);
		return mv;
	}

	/**
	 * 课程打分页面
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showCourseScore(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("CourseScore");

		long percentTypeId = ServletRequestUtils.getLongParameter(request, "percentTypeId", -1L);
		long courseId = ServletRequestUtils.getLongParameter(request, "courseId", -1L);
		int stage = ServletRequestUtils.getIntParameter(request, "stage", -1);
		if (percentTypeId < 0) {
			try {
				response.sendRedirect("/teach/course/" + courseId + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		mv.addObject("percentTypeId", percentTypeId);
		mv.addObject("courseId", courseId);
		mv.addObject("stage", stage);
		mv.addObject("percentTypeName", CoursePercentType.genCoursePercentType((int) percentTypeId).getName());
		List<CourseStudentVO> studentList;
		// 如果是分期的平时成绩
		if (stage > 0 && percentTypeId == CoursePercentType.AvgGrading.getValue()) {

			mv.addObject("stageName", "第" + stage + "次成绩");

			studentList = courseService.getCourseStudentVOsFromStage(stage, courseId);
		} else if (percentTypeId == CoursePercentType.EachStudent.getValue()) {
			// 学生互评
			studentList = null;
		} else {
			studentList = courseService.getCourseStudentVOsByCourseId(courseId, percentTypeId);

		}
		mv.addObject("studentList", studentList);
		this.setUD(mv, request);
		return mv;
	}

	/**
	 * 团队互评
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showEachStudentView(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("eachStudent");

		long courseId = ServletRequestUtils.getLongParameter(request, "courseId", -1L);
		if (courseId < 0) {
			try {
				response.sendRedirect("/teach/index/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		Course course = courseService.getCourseById(courseId);
		mv.addObject("course", course);
		boolean isEachStudent = false;
		List<CourseScorePercent> courseScorePercents = courseService.getCourseScorePercentListByCourseId(courseId);
		for (CourseScorePercent courseScorePercent : courseScorePercents) {
			if ((int) courseScorePercent.getPercentType() == CoursePercentType.EachStudent.getValue()) {
				isEachStudent = true;
			}
			courseScorePercent.setPercent(courseScorePercent.getPercent());
		}
		mv.addObject("isEachStudent", isEachStudent ? 1 : 0);
		mv.addObject("courseScorePercents", courseScorePercents);
		this.setUD(mv, request);
		return mv;
	}

	/**
	 * 打分页面
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showEachStudentScoreView(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("eachStudentScore");
		Long fromStudentId = MyUser.getMyUser(request);
		long courseId = ServletRequestUtils.getLongParameter(request, "courseId", -1L);
		if (courseId < 0) {
			try {
				response.sendRedirect("/teach/index/");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		mv.addObject("courseId", courseId);
		Course course = courseService.getCourseById(courseId);
		mv.addObject("course", course);
		List<CoursePercentTypeGroupStudentVO> coursePercentTypeGroupStudentVO = courseService.getCoursePercentTypeGroupStudentScoresFromStudentID(
				fromStudentId, courseId);
		mv.addObject("coursePercentTypeGroupStudentVO", coursePercentTypeGroupStudentVO);
		this.setUD(mv, request);
		return mv;
	}
}
