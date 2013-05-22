package com.ruoogle.teach.mapper;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.Specialty;

public interface SpecialtyMapper {
	public int addSpecialty(Specialty specialty);

	public Specialty getSpecialtyById(@Param(value = "id") long id);
}