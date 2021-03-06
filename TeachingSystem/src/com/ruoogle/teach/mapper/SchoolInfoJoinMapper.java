package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.SchoolInfoJoin;

public interface SchoolInfoJoinMapper {
	public int addSchoolInfoJoin(SchoolInfoJoin schoolInfoJoin);

	public List<SchoolInfoJoin> getSchoolInfoJoinList(
			@Param(value = "infoId") long infoId,
			@Param(value = "limit") int limit,
			@Param(value = "offset") int offset);

	public SchoolInfoJoin getSchoolInfoJoinByUser(
			@Param(value = "infoId") long infoId,
			@Param(value = "userId") long userId);

	public int removeSchoolInfoJoinByUser(@Param(value = "userId") long userId,
			@Param(value = "infoId") long infoId);

	public SchoolInfoJoin getSchoolInfoJoinByName(
			@Param(value = "infoId") long infoId,
			@Param(value = "name") String name,
			@Param(value = "phoneNum") long phoneNum);
}