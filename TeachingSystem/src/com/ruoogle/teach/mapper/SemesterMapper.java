package com.ruoogle.teach.mapper;

import java.util.List;

import com.ruoogle.teach.meta.Semester;

public interface SemesterMapper {
	public int addSemester(Semester semester);

	public List<Semester> getAllSemester();
}