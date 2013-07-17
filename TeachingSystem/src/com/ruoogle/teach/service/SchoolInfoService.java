package com.ruoogle.teach.service;

import java.util.List;

import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.SchoolInfo;
import com.ruoogle.teach.meta.SchoolInfoJoin;

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
	public boolean addSchoolInfo(String title, String content, int type,
			int infoType);

	public List<SchoolInfo> getSchoolInfoList(int limit, int offset, int type,
			long userId);

	public List<Profile> getJoinedSchoolInfoUserList(int limit, int offset,
			long infoId);

	public SchoolInfo getSchoolInfo(long id, long userId);

	public boolean removeSchoolInfo(long userId, long infoId, long adminId);

	public boolean finishSchoolInfo(long infoId, long userId);

	public boolean joinSchoolInfo(long userId, long infoId, long phoneNum);

	public boolean joinSchoolInfo(long infoId, String name, String origin,
			long phoneNum, String graduateSch);

	public List<SchoolInfoJoin> getSchoolInfoJoinsByInfoId(long infoId,
			int limit, int offset);

	public boolean addSchoolInfo(String title, String content, int type,
			int infoType, String bimgurl, String simgurl);

	public boolean deleteSchoolInfo(long infoId);
}
