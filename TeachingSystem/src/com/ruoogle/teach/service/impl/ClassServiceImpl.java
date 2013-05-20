package com.ruoogle.teach.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruoogle.teach.mapper.ClassMapper;
import com.ruoogle.teach.mapper.CoursePercentTypeDemoMapper;
import com.ruoogle.teach.meta.CoursePercentTypeDemo;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.ClassService#addClassRoom(java.lang.String,
	 * int)
	 */
	public boolean addClassRoom(String name, int year) {
		com.ruoogle.teach.meta.Class class1 = new com.ruoogle.teach.meta.Class();
		class1.setName(name);
		class1.setStartYear(year);
		return classMapper.addClass(class1) > 0;
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
