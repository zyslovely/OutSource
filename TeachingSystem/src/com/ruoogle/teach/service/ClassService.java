package com.ruoogle.teach.service;

import java.util.List;

import com.ruoogle.teach.meta.Profile;

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

	/**
	 * 添加学生信息
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @param number
	 * @return
	 */
	public boolean addStudentProfile(long classId, long number, String name);

	/**
	 * 获得用户信息列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @return
	 */
	public List<Profile> getProfilesByClassId(long classId);

	/**
	 * 得到班级
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @return
	 */
	public com.ruoogle.teach.meta.Class getClassById(long classId);

}
