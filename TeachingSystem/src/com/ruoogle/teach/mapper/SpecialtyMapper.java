package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.Specialty;

public interface SpecialtyMapper {
	public int addSpecialty(Specialty specialty);

	public Specialty getSpecialtyById(@Param(value = "id") long id);

	/**
	 * 获取专业
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public List<Specialty> getSpecialties();

	/**
	 * 删除
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public int deleteSpecialty(@Param(value = "id") long id);
}