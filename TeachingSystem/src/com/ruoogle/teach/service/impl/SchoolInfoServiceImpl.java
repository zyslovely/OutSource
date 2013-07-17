package com.ruoogle.teach.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eason.web.util.ListUtils;
import com.ruoogle.teach.mapper.ProfileMapper;
import com.ruoogle.teach.mapper.SchoolInfoJoinMapper;
import com.ruoogle.teach.mapper.SchoolInfoMapper;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.SchoolInfo;
import com.ruoogle.teach.meta.SchoolInfoJoin;
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

	@Resource
	private SchoolInfoJoinMapper schoolInfoJoinMapper;

	@Resource
	private ProfileMapper profileMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.SchoolInfoService#getSchoolInfoList(int,
	 * int)
	 */
	@Override
	public List<SchoolInfo> getSchoolInfoList(int limit, int offset, int type,
			long userId) {
		List<SchoolInfo> schoolInfos = schoolInfoMapper.getSchoolInfoList(
				limit, offset, type);
		if (ListUtils.isEmptyList(schoolInfos)) {
			return null;
		}
		if (userId < 0) {
			return schoolInfos;
		}
		for (SchoolInfo schoolInfo : schoolInfos) {
			SchoolInfoJoin schoolInfoJoin = schoolInfoJoinMapper
					.getSchoolInfoJoinByUser(schoolInfo.getId(), userId);
			if (schoolInfoJoin != null) {
				schoolInfo.setJoined(1);
			}
		}
		return schoolInfos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.SchoolInfoService#getSchoolInfo(long,
	 * long)
	 */
	@Override
	public SchoolInfo getSchoolInfo(long id, long userId) {

		SchoolInfo schoolInfo = schoolInfoMapper.getSchoolInfoById(id);
		if (schoolInfo == null) {
			return null;
		}
		if (userId < 0) {
			return schoolInfo;
		}
		SchoolInfoJoin schoolInfoJoin = schoolInfoJoinMapper
				.getSchoolInfoJoinByUser(id, userId);
		if (schoolInfoJoin != null) {
			schoolInfo.setJoined(1);
		}
		return schoolInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.SchoolInfoService#addSchoolInfo(java.lang.String
	 * , java.lang.String, int, int)
	 */
	@Override
	public boolean addSchoolInfo(String title, String content, int type,
			int infoType) {
		SchoolInfo schoolInfo = new SchoolInfo();
		schoolInfo.setContent(content);
		schoolInfo.setTitle(title);
		schoolInfo.setType(type);
		schoolInfo.setInfoType(infoType);
		schoolInfo.setStatus(SchoolInfo.SchoolInfoStatus.ongoing.getValue());
		return schoolInfoMapper.addSchoolInfo(schoolInfo) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.SchoolInfoService#joinSchoolInfo(long,
	 * long)
	 */
	@Override
	public boolean joinSchoolInfo(long userId, long infoId) {

		SchoolInfoJoin schoolInfoJoin = schoolInfoJoinMapper
				.getSchoolInfoJoinByUser(infoId, userId);
		if (schoolInfoJoin != null) {
			return true;
		}
		SchoolInfo schoolInfo = schoolInfoMapper.getSchoolInfoById(infoId);
		// 判断是否可以加入
		if (schoolInfo.getInfoType() == 0) {
			return false;
		}
		schoolInfoJoin = new SchoolInfoJoin();
		schoolInfoJoin.setCreateTime(new Date().getTime());
		schoolInfoJoin.setUserId(userId);
		schoolInfoJoin.setInfoId(infoId);
		return schoolInfoJoinMapper.addSchoolInfoJoin(schoolInfoJoin) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.SchoolInfoService#getJoinedSchoolInfoUserList
	 * (int, int, int)
	 */
	@Override
	public List<Profile> getJoinedSchoolInfoUserList(int limit, int offset,
			long infoId) {

		List<SchoolInfoJoin> schoolInfoJoins = schoolInfoJoinMapper
				.getSchoolInfoJoinList(infoId, limit, offset);
		if (ListUtils.isEmptyList(schoolInfoJoins)) {
			return null;
		}
		List<Long> userIds = new ArrayList<Long>();
		for (SchoolInfoJoin schoolInfoJoin : schoolInfoJoins) {
			userIds.add(schoolInfoJoin.getUserId());
		}
		return profileMapper.getProfileListByIds(userIds);
	}

	@Override
	// Not Finished
	public boolean removeSchoolInfo(long userId, long infoId, long adminId) {
		Profile profile = profileMapper.getProfile(adminId);
		if (profile.getLevel() != Profile.ProfileLevel.Admin.getValue()) {
			return false;
		}
		SchoolInfoJoin schoolInfoJoin = schoolInfoJoinMapper
				.getSchoolInfoJoinByUser(infoId, userId);
		if (schoolInfoJoin == null) {
			return false;
		}
		return schoolInfoJoinMapper.removeSchoolInfoJoinByUser(userId, infoId) > 0;
	}

	@Override
	public boolean finishSchoolInfo(long infoId, long userId) {
		Profile profile = profileMapper.getProfile(userId);
		if (profile.getLevel() != Profile.ProfileLevel.Admin.getValue()) {
			return false;
		}
		int status = SchoolInfo.SchoolInfoStatus.finished.getValue();
		return schoolInfoMapper.finishSchoolInfo(infoId,status) > 0;
	}
}
