package com.ruoogle.teach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.SchoolInfo;

public interface SchoolInfoMapper {
	public int addSchoolInfo(SchoolInfo schoolInfo);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<SchoolInfo> getSchoolInfoList(@Param(value = "limit") int limit, @Param(value = "offset") int offset, @Param("type") int type);

}