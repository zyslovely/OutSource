package com.ruoogle.teach.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.eason.web.util.ListUtils;
import com.ruoogle.teach.constant.BasicObjectConstant;
import com.ruoogle.teach.constant.ReturnCodeConstant;
import com.ruoogle.teach.meta.CourseVO;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.Profile.ProfileLevel;
import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.ClassService;
import com.ruoogle.teach.service.CourseService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-25 下午02:44:15
 * @see Class Description
 */
@Controller("apiTeachSysController")
public class ApiTeachSysController extends AbstractBaseController {
	private static final Logger logger = Logger.getLogger(ApiTeachSysController.class);
	@Resource
	private CourseService courseService;
	@Resource
	private ClassService classService;

	/**
	 * 课程列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showCourseList(HttpServletRequest request, HttpServletResponse response) {
		logger.info(request.getSession().getId());
		long userId = MyUser.getMyUserFromToken(request);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 0);
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int semesterId = ServletRequestUtils.getIntParameter(request, "semesterId", 0);
		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		if (userId < 0) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code, ReturnCodeConstant.FAILED);
			return modelAndView;
		}
		Profile profile = profileService.getProfile(userId);
		List<CourseVO> courseList = courseService.getCourseVOListByUserId(userId, profile.getLevel(), semesterId, limit, offset);
		JSONObject dataObject = new JSONObject();
		JSONArray courseArray = new JSONArray();
		if (!ListUtils.isEmptyList(courseList)) {
			for (CourseVO course : courseList) {
				JSONObject courseObject = new JSONObject();
				courseObject.put(CourseVO.KCourse_title, course.getCourse().getName());
				courseObject.put(CourseVO.KCoursec_className, course.getClass1().getName());
				courseObject.put(CourseVO.KCoursec_courseId, course.getCourse().getId());
				courseArray.add(courseObject);
			}
		}
		dataObject.put("courseList", courseArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data, dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code, ReturnCodeConstant.SUCCESS);
		modelAndView.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return modelAndView;
	}

	/**
	 * 学生列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showStudentList(HttpServletRequest request, HttpServletResponse response) {
		logger.info(request.getSession().getId());

		int limit = ServletRequestUtils.getIntParameter(request, "limit", 0);
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int classId = ServletRequestUtils.getIntParameter(request, "classId", 0);
		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		List<Profile> profileList = profileService.getProfileListByClassId(ProfileLevel.Student.getValue(), limit, offset, classId);

		JSONObject dataObject = new JSONObject();
		JSONArray courseArray = new JSONArray();
		if (!ListUtils.isEmptyList(profileList)) {
			for (Profile profile : profileList) {
				JSONObject courseObject = new JSONObject();
				courseObject.put(Profile.KProfile_Name, profile.getName());
				courseObject.put(Profile.KProfile_userId, profile.getUserId());
				courseArray.add(courseObject);
			}
		}
		dataObject.put("profileList", courseArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data, dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code, ReturnCodeConstant.SUCCESS);
		modelAndView.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return modelAndView;
	}
}
