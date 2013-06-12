package com.ruoogle.teach.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.ruoogle.teach.meta.CoursePercentTypeDemo;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.SchoolInfo;
import com.ruoogle.teach.meta.Specialty;
import com.ruoogle.teach.meta.Profile.ProfileLevel;
import com.ruoogle.teach.meta.SchoolInfo.SchoolInfoType;
import com.ruoogle.teach.service.ClassService;
import com.ruoogle.teach.service.CourseService;
import com.ruoogle.teach.service.ProfileService;
import com.ruoogle.teach.service.SchoolInfoService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午12:50:55
 * @see Class Description
 */
@Controller("webTeachSysAdminController")
public class WebTeachSysAdminController extends AbstractBaseController {

	private static final Logger logger = Logger.getLogger(WebTeachSysAdminController.class);

	@Resource
	private ProfileService profileService;
	@Resource
	private CourseService courseService;
	@Resource
	private ClassService classService;
	@Resource
	private SchoolInfoService schoolInfoService;

	/**
	 * 
	 * 显示添加专业
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddSpecialty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("admin_newSpecialty");
		List<Specialty> specialties = classService.getSpecialties();
		mv.addObject("specialties", specialties);
		this.setUD(mv, request);
		return mv;
	}

	/**
	 * 显示添加班级
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddClass(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("admin_newClass");
		long specialtyId = ServletRequestUtils.getLongParameter(request, "specialtyId", -1L);
		if (specialtyId < 0) {

		}
		mv.addObject("specialtyId", specialtyId);
		List<com.ruoogle.teach.meta.Class> classList = classService.getClassListBySpecialty(specialtyId);

		mv.addObject("classList", classList);
		List<Specialty> specialties = classService.getSpecialties();
		mv.addObject("specialties", specialties);
		this.setUD(mv, request);
		return mv;
	}

	/**
	 * 显示添加Course类型
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddCourseType(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("admin_newCourseType");
		List<CoursePercentTypeDemo> coursePercentTypeDemos = courseService.getCoursePercentTypeDemos(0, -1);
		mv.addObject("coursePercentTypeDemos", coursePercentTypeDemos);

		this.setUD(mv, request);
		return mv;
	}

	/**
	 * 显示添加学生
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddStudent(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("admin_newStudent");
		long classId = ServletRequestUtils.getLongParameter(request, "classId", -1L);
		long specialtyId = ServletRequestUtils.getLongParameter(request, "specialtyId", -1L);

		if (classId > 0) {
			List<Profile> studentList = profileService.getProfileListByClassId(ProfileLevel.Student.getValue(), 0, -1, classId);
			mv.addObject("studentList", studentList);
		}
		if (specialtyId > 0) {
			List<com.ruoogle.teach.meta.Class> classList = classService.getClassListBySpecialty(specialtyId);
			mv.addObject("classList", classList);
		}

		List<Specialty> specialties = classService.getSpecialties();
		mv.addObject("specialties", specialties);
		mv.addObject("classId", classId);
		mv.addObject("specialtyId", specialtyId);
		this.setUD(mv, request);
		return mv;
	}

	/**
	 * 显示添加专业
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddTeacher(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("admin_newTeacher");
		List<Profile> teacherList = profileService.getProfileList(ProfileLevel.Teacher.getValue(), 0, -1);
		List<Profile> companyTeacherList = profileService.getProfileList(ProfileLevel.CompanyLeader.getValue(), 0, -1);
		teacherList.addAll(companyTeacherList);
		mv.addObject("teacherList", teacherList);
		this.setUD(mv, request);
		return mv;
	}

	/**
	 * 添加校园信息
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddSchoolInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("admin_newSchoolInfo");
		int type = ServletRequestUtils.getIntParameter(request, "type", -1);
		if (type < 0) {

		}
		List<SchoolInfo> schoolInfos = schoolInfoService.getSchoolInfoList(0, -1, SchoolInfoType.school.getValue());
		mv.addObject("schoolInfos", schoolInfos);
		this.setUD(mv, request);
		return mv;
	}
}
