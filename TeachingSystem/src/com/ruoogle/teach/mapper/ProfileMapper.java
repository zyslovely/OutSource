package com.ruoogle.teach.mapper;

import org.apache.ibatis.annotations.Param;

import com.ruoogle.teach.meta.Profile;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午12:55:34
 * @see Class Description
 */
public interface ProfileMapper {
	/**
	 * 添加账户
	 * 
	 * @param profile
	 * @return
	 */
	public int addProfile(Profile profile);

	/**
	 * 获取账户
	 * 
	 * @param UserId
	 * @return
	 */
	public Profile getProfile(long UserId);

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userName
	 * @return
	 */
	public Profile getProfileByUserName(@Param(value = "username") String userName);

}
