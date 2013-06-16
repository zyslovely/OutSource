package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.Semester;

public interface SemesterMapper {
	public int addSemester(Semester semester);

	public List<Semester> getAllSemester();

	/**
	 * 删除
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public int deleteSelemster(@Param(value = "id") long id);
}