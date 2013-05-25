package com.ruoogle.teach.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruoogle.teach.mapper.InteractiveMapper;
import com.ruoogle.teach.meta.Interactive;
import com.ruoogle.teach.service.InteractiveService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-25 下午05:55:37
 * @see Class Description
 */
@Service("interactiveService")
public class InteractiveServiceImpl implements InteractiveService {
	@Resource
	private InteractiveMapper interactiveMapper;

	@Override
	public boolean addInteractive(long userId, String content, long courseId, int status, String photoUrl, long forwardId) {
		Interactive interactive = new Interactive();
		interactive.setUserId(userId);
		interactive.setCourseId(courseId);
		interactive.setCreateTime(new Date().getTime());
		interactive.setForwardId(forwardId);
		interactive.setPhotoUrl(photoUrl);
		interactive.setStatus(status);
		interactive.setContent(content);
		return interactiveMapper.addInteractive(interactive) > 0;
	}
}
