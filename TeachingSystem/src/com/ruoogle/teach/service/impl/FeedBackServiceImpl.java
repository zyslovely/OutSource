package com.ruoogle.teach.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruoogle.teach.mapper.FeedBackMapper;
import com.ruoogle.teach.meta.FeedBack;
import com.ruoogle.teach.service.FeedBackService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-25 下午01:12:11
 * @see Class Description
 */
@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService {

	@Resource
	private FeedBackMapper feedBackMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.FeedBackService#addFeedBack(long, long,
	 * java.lang.String, long, long)
	 */
	@Override
	public boolean addFeedBack(long toUserId, long feedbackId, String content, long courseId, long fromUserId) {
		// TODO Auto-generated method stub
		FeedBack feedBack = new FeedBack();
		feedBack.setContent(content);
		feedBack.setToUserId(toUserId);
		feedBack.setFromUserId(fromUserId);
		feedBack.setCourseId(courseId);
		feedBack.setFeedbackId(feedbackId);
		feedBack.setCreateTime(new Date().getTime());
		return feedBackMapper.addFeedBack(feedBack) > 0;
	}
}
