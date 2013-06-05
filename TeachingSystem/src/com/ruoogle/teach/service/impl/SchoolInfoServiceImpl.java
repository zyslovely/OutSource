package com.ruoogle.teach.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruoogle.teach.mapper.SchoolInfoMapper;
import com.ruoogle.teach.meta.SchoolInfo;
import com.ruoogle.teach.service.SchoolInfoService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-6-2 上午12:48:18
 * @see Class Description
 */
@Service("schoolInfoService")
public class SchoolInfoServiceImpl implements SchoolInfoService {
	@Resource
	private SchoolInfoMapper schoolInfoMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.SchoolInfoService#getSchoolInfoList(int,
	 * int)
	 */
	@Override
	public List<SchoolInfo> getSchoolInfoList(int limit, int offset, int type) {
		return schoolInfoMapper.getSchoolInfoList(limit, offset, type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.SchoolInfoService#addSchoolInfo(java.lang.String
	 * , java.lang.String, int, int)
	 */
	@Override
	public boolean addSchoolInfo(String title, String content, int type, int infoType) {
		SchoolInfo schoolInfo = new SchoolInfo();
		schoolInfo.setContent(content);
		schoolInfo.setTitle(title);
		schoolInfo.setType(type);
		schoolInfo.setInfoType(infoType);
		return schoolInfoMapper.addSchoolInfo(schoolInfo) > 0;
	}

}
