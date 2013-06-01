package com.ruoogle.teach.service;

import java.util.List;

import com.ruoogle.teach.meta.SchoolInfo;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-6-2 上午12:48:00
 * @see Class Description
 */
public interface SchoolInfoService {
	/**
	 * 添加学校信息
	 * 
	 * @auther zyslovely@gmail.com
	 * @param schoolInfo
	 * @return
	 */
	public boolean addSchoolInfo(String title, String content, int type, int infoType);

	public List<SchoolInfo> getSchoolInfoList(int limit, int offset,int type);
}
