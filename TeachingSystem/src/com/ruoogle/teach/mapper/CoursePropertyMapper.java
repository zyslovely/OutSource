package com.ruoogle.teach.mapper;

import java.util.List;

import com.ruoogle.teach.meta.CourseProperty;

public interface CoursePropertyMapper {
	public int addCourseProperty(CourseProperty courseProperty);

	public List<CourseProperty> getAllCourseProperties();
}