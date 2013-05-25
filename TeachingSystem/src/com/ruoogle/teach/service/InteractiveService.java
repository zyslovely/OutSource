package com.ruoogle.teach.service;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-25 下午05:55:25
 * @see Class Description
 */
public interface InteractiveService {
	/**
	 * 添加互动
	 * 
	 * @auther zyslovely@gmail.com
	 * @param content
	 * @param courseId
	 * @param status
	 * @param photoUrl
	 * @return
	 */
	public boolean addInteractive(long userId, String content, long courseId, int status, String photoUrl, long forwardId);
}
