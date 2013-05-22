package com.ruoogle.teach.service;

import java.util.List;

import com.ruoogle.teach.meta.CoursePercentType;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 下午08:37:36
 * @see Class Description
 */
public interface ClassService {
	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param year
	 * @return
	 */
	public boolean addClassRoom(String name, int year, long specialtyId);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param demoJson
	 * @return
	 */
	public boolean addCoursePercentTypeDemo(String name, String demoJson);

}
