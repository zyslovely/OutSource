package com.ruoogle.teach.mapper;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.Class;

public interface ClassMapper {
	public int addClass(Class aClass);

	public Class getClassById(@Param(value = "id") long id);
}