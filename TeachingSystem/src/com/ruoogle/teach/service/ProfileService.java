package com.ruoogle.teach.service;

import java.util.List;

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

	/**
	 * 老师列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param level
	 * @return
	 */
	public List<Profile> getProfileList(int level, int limit, int offset);

	/**
	 * 两种老师的情况
	 * 
	 * @auther zyslovely@gmail.com
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<Profile> getProfileListWithTeacher(int limit, int offset);

	public List<Profile> getProfileListByClassId(int level, int limit, int offset, long classId);
}
