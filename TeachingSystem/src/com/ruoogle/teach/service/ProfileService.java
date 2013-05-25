package com.ruoogle.teach.service;

import com.ruoogle.teach.meta.Profile;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午01:01:42
 * @see Class Description
 */
public interface ProfileService {
	/**
	 * 添加用户
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param userName
	 * @param passWrod
	 * @param level
	 * @return
	 */
	public boolean addProfile(String name, String userName, String passWord, int level);

	/**
	 * 改密码
	 * 
	 * @auther zyslovely@gmail.com
	 * @param studentId
	 * @param newPassword
	 * @param oldPassword
	 * @return
	 */
	public boolean changePassword(long studentId, String newPassword, String oldPassword);

	/**
	 * 获得用户信息
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public Profile getProfile(long userId);

	public Profile getProfileByUserName(String userName);
}
