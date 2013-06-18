package com.ruoogle.teach.service;

import java.util.List;

import com.ruoogle.teach.meta.Class;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.Semester;
import com.ruoogle.teach.meta.Specialty;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 下午08:37:36
 * @see Class Description
 */
public interface ClassService {
	/**
	 * 添加专业
	 * 
	 * @auther zyslovely@gmail.com
	 * @param SpecialtyName
	 * @param SpecialtyShortName
	 * @param semesterCount
	 * @return
	 */
	public boolean addSpecialty(String SpecialtyName, String SpecialtyShortName, int semesterCount);

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
	public List<Profile> getProfilesByClassId(long classId, int level);

	/**
	 * 得到班级
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @return
	 */
	public com.ruoogle.teach.meta.Class getClassById(long classId);

	/**
	 * 添加日志
	 * 
	 * @auther zyslovely@gmail.com
	 * @param content
	 * @param type
	 * @param courseId
	 * @param userId
	 * @return
	 */
	public boolean addJournal(String content, int type, long courseId, long userId);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public List<Class> getAllClass();

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public List<Semester> getAllSemesters();

	/**
	 * 根据专业获取班级列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param specialtyId
	 * @return
	 */
	public List<Class> getClassListBySpecialty(long specialtyId);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public List<Specialty> getSpecialties();

	/**
	 * 更新学生数量
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @return
	 */
	public boolean updateClassStudentCount(long classId);

	/**
	 * 删除专业
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public boolean deleteSpecialty(long id);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @return
	 */
	public boolean deleteClass(long classId);

	/**
	 * 删除
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public boolean deleteProfile(long userId);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param demoId
	 * @return
	 */
	public boolean deleteCourseType(long demoId);

	/**
	 * 获取学期
	 * 
	 * @auther zyslovely@gmail.com
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<Semester> getAllSemesterList(int limit, int offset);

	/**
	 * 添加新的学期
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @return
	 */
	public boolean addNewSemester(String name);

	/**
	 * 删除
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public boolean deleteSemester(long id);
}
