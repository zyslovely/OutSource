package com.ruoogle.teach.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruoogle.teach.mapper.ClassMapper;
import com.ruoogle.teach.mapper.CoursePercentTypeDemoMapper;
import com.ruoogle.teach.mapper.ProfileMapper;
import com.ruoogle.teach.mapper.SpecialtyMapper;
import com.ruoogle.teach.meta.CoursePercentTypeDemo;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.Specialty;
import com.ruoogle.teach.meta.Profile.ProfileLevel;
import com.ruoogle.teach.service.ClassService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 下午08:37:57
 * @see Class Description
 */
@Service("classService")
public class ClassServiceImpl implements ClassService {

	@Resource
	private ClassMapper classMapper;
	@Resource
	private CoursePercentTypeDemoMapper coursePercentTypeDemoMapper;

	@Resource
	private SpecialtyMapper specialtyMapper;
	@Resource
	private ProfileMapper profileMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.ClassService#addClassRoom(java.lang.String,
	 * int)
	 */
	public boolean addClassRoom(String name, int year, long specialtyId) {
		com.ruoogle.teach.meta.Class class1 = new com.ruoogle.teach.meta.Class();
		class1.setName(name);
		class1.setStartYear(year);
		class1.setSpecialtyId(specialtyId);
		return classMapper.addClass(class1) > 0;
	}

	/**
	 * 添加学生信息
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @param number
	 * @return
	 */
	public boolean addStudentProfile(long classId, int number) {
		Profile profile = new Profile();
		profile.setCreateTime(new Date().getTime());
		com.ruoogle.teach.meta.Class class1 = classMapper.getClassById(classId);
		Specialty specialty = specialtyMapper.getSpecialtyById(class1.getSpecialtyId());
		String userName = specialty.getShortSpecialty() + class1.getName() + number;
		String passWord = class1.getName() + number;
		profile.setUserName(userName);
		profile.setPassword(passWord);
		profile.setLevel(ProfileLevel.Student.getValue());
		return profileMapper.addProfile(profile) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.ClassService#addCoursePercentTypeDemo(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public boolean addCoursePercentTypeDemo(String name, String demoJson) {
		CoursePercentTypeDemo coursePercentTypeDemo = new CoursePercentTypeDemo();
		coursePercentTypeDemo.setName(name);
		coursePercentTypeDemo.setDemoJson(demoJson);
		return coursePercentTypeDemoMapper.addCoursePercentTypeDemo(coursePercentTypeDemo) > 0;
	}

}
