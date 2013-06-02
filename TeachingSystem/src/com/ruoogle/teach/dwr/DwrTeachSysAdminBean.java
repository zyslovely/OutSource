package com.ruoogle.teach.dwr;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ruoogle.teach.meta.CoursePercentTypeDemo;
import com.ruoogle.teach.service.ClassService;
import com.ruoogle.teach.service.CourseService;
import com.ruoogle.teach.service.ProfileService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午12:41:48
 * @see Class Description
 */
@Service("dwrTeachSysAdminBean")
public class DwrTeachSysAdminBean {
	@Resource
	private CourseService courseService;
	@Resource
	private ClassService classService;
	@Resource
	private ProfileService profileService;

	/**
	 * 添加专业
	 * 
	 * @auther zyslovely@gmail.com
	 * @param SpecialtyName
	 * @param SpecialtyShortName
	 * @param semesterCount
	 * @return
	 */
	public boolean addSpecialty(String SpecialtyName, String SpecialtyShortName, int semesterCount) {
		if (StringUtils.isEmpty(SpecialtyName) || StringUtils.isEmpty(SpecialtyShortName)) {
			return false;
		}
		return classService.addSpecialty(SpecialtyName, SpecialtyShortName, semesterCount);
	}

	/**
	 * 添加班级
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param year
	 * @return
	 */
	public boolean addClassRoom(String name, int year, long specialtyId) {
		if (StringUtils.isEmpty(name)) {
			return false;
		}
		return classService.addClassRoom(name, year, specialtyId);
	}

	/**
	 * 添加老师
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param level
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public boolean addTeacherPassPort(String name, int level, String userName, String passWord) {
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord) || StringUtils.isEmpty(name)) {
			return false;
		}
		return profileService.addProfile(name, userName, passWord, level);
	}

	/**
	 * 添加课程类型模型
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param coursePercentTypes
	 * @return
	 */
	public boolean addCoursePercentTypeDemo(String name, List<Integer> coursePercentTypeId, List<Double> percents) {
		return classService.addCoursePercentTypeDemo(name, CoursePercentTypeDemo.getCoursePercentTypeList(coursePercentTypeId, percents));
	}

}
