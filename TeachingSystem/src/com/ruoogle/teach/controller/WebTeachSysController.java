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
import com.ruoogle.teach.meta.CourseProperty;
import com.ruoogle.teach.meta.CourseScorePercent;
import com.ruoogle.teach.meta.CourseVO;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.Semester;
import com.ruoogle.teach.meta.Specialty;
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
		if (semesterId < 0) {

		}
		List<CourseVO> courseList = courseService.getCourseVOListByUserId(userId, myUser.getLevel(), semesterId, 0, -1);
		mv.addObject("courseList", courseList);
		List<Semester> semesters = classService.getAllSemesters();
		mv.addObject("semesters", semesters);
		mv.addObject("semesterId", semesterId);
		this.setUD(mv, request);
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

	public ModelAndView showCourseView(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("CourseInfo");

		long courseId = ServletRequestUtils.getLongParameter(request, "courseId", -1L);

		Course course = courseService.getCourseById(courseId);
		mv.addObject("course", course);
		boolean isPercentType = false;
		List<CourseScorePercent> courseScorePercents = courseService.getCourseScorePercentListByCourseId(courseId);
		for (CourseScorePercent courseScorePercent : courseScorePercents) {
			if ((int) courseScorePercent.getPercentType() == CoursePercentType.AvgGrading.getValue()) {
				isPercentType = true;
			}
			courseScorePercent.setPercent(courseScorePercent.getPercent() * 100);
		}
		mv.addObject("isPercentType", isPercentType ? 1 : 0);
		mv.addObject("courseScorePercents", courseScorePercents);
		this.setUD(mv, request);
		return mv;
	}

}
