package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.Class;

public interface ClassMapper {
	public int addClass(Class aClass);

	public Class getClassById(@Param(value = "id") long id);

	/**
	 * 获取班级
	 * 
	 * @auther zyslovely@gmail.com
	 * @param specialtyId
	 * @return
	 */
	public List<Class> getClassListBySpecialty(
			@Param(value = "specialtyId") long specialtyId);

	public List<Class> getAllClass();

	/**
	 * 更新学生数量
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @param count
	 * @return
	 */
	public int updateClassStudentCount(@Param(value = "classId") long classId,
			@Param(value = "count") int count);

	/**
	 * 删除
	 * 
	 * @auther zyslovely@gmail.com
	 * @param specialtyId
	 * @return
	 */
	public int deleteClassBySpecialty(
			@Param(value = "specialtyId") long specialtyId);

	/**
	 * 删除
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public int deleteClassById(@Param(value = "id") long id);

}