package com.ruoogle.teach.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruoogle.teach.mapper.CoursePercentTypeMapper;
import com.ruoogle.teach.meta.CoursePercentType;
import com.ruoogle.teach.service.CourseService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午12:38:00
 * @see Class Description
 */
@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService {

	@Resource
	private CoursePercentTypeMapper coursePercentTypeMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.CourseService#addNewCoursePercentType(java.
	 * lang.String, java.lang.String)
	 */
	public boolean addNewCoursePercentType(String name, String desc) {
		CoursePercentType coursePercentType = new CoursePercentType();
		coursePercentType.setName(name);
		coursePercentType.setDesc(desc);
		return coursePercentTypeMapper.addCoursePercentType(coursePercentType) > 0;
	}
}
