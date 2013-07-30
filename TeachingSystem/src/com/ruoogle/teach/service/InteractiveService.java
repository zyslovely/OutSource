package com.ruoogle.teach.service;

import java.util.List;

import com.ruoogle.teach.meta.Interactive;

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
	public boolean addInteractive(long userId, String content, long courseId,
			int status, String photoUrl, long forwardId);

	public List<Interactive> getInteractiveByUserId(long userId, int limit,
			int offset);

	public int getInteractiveCountByUserId(long userId);

	public Interactive getInteractive(long id);

	public boolean addForward(long id, String content, long userId);

	public boolean addForwardBack(long id, String content, long userId);

	public boolean deleteInteractive(long id);

	public boolean deleteInteractiveBack(long id);

	public List<Interactive> getInteractiveByCourseId(long courseId, long userId);

}
