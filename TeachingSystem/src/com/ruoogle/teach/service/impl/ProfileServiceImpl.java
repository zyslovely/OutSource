package com.ruoogle.teach.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import com.ruoogle.teach.mapper.ProfileMapper;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.service.ProfileService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午01:01:56
 * @see Class Description
 */
public class ProfileServiceImpl implements ProfileService {

	@Resource
	private ProfileMapper profileMapper;

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
		profile.setLevel(level);
		return profileMapper.addProfile(profile) > 0;
	}

}
