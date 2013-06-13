package com.ruoogle.teach.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eason.web.util.ListUtils;
import com.ruoogle.teach.mapper.ClassMapper;
import com.ruoogle.teach.mapper.ProfileMapper;
import com.ruoogle.teach.mapper.SpecialtyMapper;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.Specialty;
import com.ruoogle.teach.service.ProfileService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午01:01:56
 * @see Class Description
 */
@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

	@Resource
	private ProfileMapper profileMapper;
	@Resource
	private ClassMapper classMapper;
	@Resource
	private SpecialtyMapper specialtyMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.ProfileService#addProfile(java.lang.String,
	 * java.lang.String, java.lang.String, int)
	 */
	@Override
	public boolean addProfile(String name, String userName, String passWord, int level) {
		Profile profile = new Profile();
		profile.setCreateTime(new Date().getTime());
		profile.setUserName(userName);
		profile.setPassword(passWord);
		profile.setName(name);
		profile.setLevel(level);
		return profileMapper.addProfile(profile) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ProfileService#changePassword(long,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean changePassword(long studentId, String newPassword, String oldPassword) {

		Profile profile = profileMapper.getProfile(studentId);
		if (profile == null || !profile.getPassword().equals(oldPassword)) {
			return false;
		}
		return profileMapper.updateProfilePassword(studentId, newPassword) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ProfileService#getProfile(long)
	 */
	@Override
	public Profile getProfile(long userId) {
		return profileMapper.getProfile(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.ProfileService#getProfileByUserName(java.lang
	 * .String)
	 */
	@Override
	public Profile getProfileByUserName(String userName) {
		return profileMapper.getProfileByUserName(userName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ProfileService#getProfileList(int, int,
	 * int)
	 */
	@Override
	public List<Profile> getProfileList(int level, int limit, int offset) {
		return profileMapper.getProfileListByLevel(level, limit, offset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.ProfileService#getProfileListByClassId(int,
	 * int, int, long)
	 */
	@Override
	public List<Profile> getProfileListByClassId(int level, int limit, int offset, long classId) {
		com.ruoogle.teach.meta.Class class1 = classMapper.getClassById(classId);
		Specialty specialty = specialtyMapper.getSpecialtyById(class1.getSpecialtyId());
		if (class1 == null || specialty == null) {
			return null;
		}
		List<Profile> profileList = profileMapper.getProfileByClassId(classId, level, limit, offset);
		if (ListUtils.isEmptyList(profileList)) {
			return null;
		}
		for (Profile profile : profileList) {
			profile.setClassName(class1.getName());
			profile.setSpecialtyName(specialty.getSpecialty());
		}
		return profileList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.ProfileService#getProfileListWithTeacher(int,
	 * int)
	 */
	@Override
	public List<Profile> getProfileListWithTeacher(int limit, int offset) {
		return profileMapper.getProfileListWithTeacher(limit, offset);
	}
}
