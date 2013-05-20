package com.ruoogle.teach.service;

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
}
