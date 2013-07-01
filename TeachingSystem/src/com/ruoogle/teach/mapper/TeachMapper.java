package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.Teach;

public interface TeachMapper {
	public int addTeach(Teach teach);

	public List<Teach> getTeachList(@Param(value = "limit") int limit,
			@Param(value = "offset") int offset);

	public int deleteTeach(@Param(value = "id") long id);

	public Teach getTeachById(@Param(value = "id") long id);
}