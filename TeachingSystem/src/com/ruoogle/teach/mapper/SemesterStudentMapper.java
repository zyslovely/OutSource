package com.ruoogle.teach.mapper;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.SemesterStudent;

public interface SemesterStudentMapper {

	public int addSemesterStudent(SemesterStudent semesterStudent);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param semesterId
	 * @param studentId
	 * @return
	 */
	public SemesterStudent getSemesterStudentByStudentSemester(
			@Param(value = "semesterId") long semesterId,
			@Param(value = "studentId") long studentId);

	/**
	 * 更新
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public int updateSemesterStudentStatus(@Param(value = "id") long id,
			@Param(value = "status") int status);
}