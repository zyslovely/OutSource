package com.ruoogle.teach.dwr;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruoogle.teach.meta.CoursePercentType;
import com.ruoogle.teach.meta.CoursePercentTypeDemo;
import com.ruoogle.teach.meta.CourseScorePercentProperty;
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
	 * 添加课程评分类型
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param desc
	 * @return
	 */
	public boolean addNewCoursePercentType(String name, String desc) {
		return courseService.addNewCoursePercentType(name, desc);
	}

	/**
	 * 添加班级
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param year
	 * @return
	 */
	public boolean addClassRoom(String name, int year) {
		return classService.addClassRoom(name, year);
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
	public boolean addCoursePercentTypeDemo(String name, List<CoursePercentType> coursePercentTypes) {
		return classService.addCoursePercentTypeDemo(name, CoursePercentTypeDemo.getCoursePercentTypeList(coursePercentTypes));
	}

}
