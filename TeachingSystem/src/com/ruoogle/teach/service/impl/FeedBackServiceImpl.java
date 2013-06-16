package com.ruoogle.teach.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eason.web.util.HashMapMaker;
import com.eason.web.util.ListUtils;
import com.eason.web.util.TimeUtil;
import com.ruoogle.teach.mapper.CourseMapper;
import com.ruoogle.teach.mapper.CourseStudentMapper;
import com.ruoogle.teach.mapper.FeedBackMapper;
import com.ruoogle.teach.mapper.ProfileMapper;
import com.ruoogle.teach.meta.Course;
import com.ruoogle.teach.meta.CourseStudent;
import com.ruoogle.teach.meta.FeedBack;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.Profile.ProfileLevel;
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

	@Resource
	private ProfileMapper profileMapper;
	@Resource
	private CourseMapper courseMapper;
	@Resource
	private CourseStudentMapper courseStudentMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.FeedBackService#addFeedBack(long, long,
	 * java.lang.String, long, long)
	 */
	@Override
	public boolean addFeedBack(long toUserId, long feedbackId, String content, long courseId, long fromUserId) {
		if (toUserId <= 0 && courseId > 0) {
			List<CourseStudent> courseStudents = courseStudentMapper.getCourseTeacherByCourseId(courseId);
			if (ListUtils.isEmptyList(courseStudents)) {
				return false;
			}
			for (CourseStudent courseStudent : courseStudents) {
				this.addAFeedBack(courseStudent.getUserId(), feedbackId, content, courseId, fromUserId);
			}
			return true;
		} else {
			return this.addAFeedBack(toUserId, feedbackId, content, courseId, fromUserId);
		}
	}

	/**
	 * 一个
	 * 
	 * @auther zyslovely@gmail.com
	 * @param toUserId
	 * @param feedbackId
	 * @param content
	 * @param courseId
	 * @param fromUserId
	 * @return
	 */
	private boolean addAFeedBack(long toUserId, long feedbackId, String content, long courseId, long fromUserId) {
		// TODO Auto-generated method stub
		FeedBack feedBack = new FeedBack();
		feedBack.setContent(content);
		feedBack.setToUserId(toUserId);
		feedBack.setFromUserId(fromUserId);
		feedBack.setCourseId(courseId);
		feedBack.setFeedbackId(feedbackId);
		feedBack.setCreateTime(new Date().getTime());
		feedBack.setStatus(FeedBack.Unread);
		return feedBackMapper.addFeedBack(feedBack) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.FeedBackService#getUnreadCount(long)
	 */
	@Override
	public int getUnreadCount(long userId) {
		return feedBackMapper.getUnreadCount(userId);
	}

	@Override
	public List<FeedBack> getFeedBackList(long userId, int limit, int offset, long courseId) {
		List<FeedBack> feedBacks = feedBackMapper.getFeedBacksByUserId(userId, courseId, limit, offset);
		if (ListUtils.isEmptyList(feedBacks)) {
			return null;
		}
		this.insertInfoForFeedback(feedBacks);
		return feedBacks;
	}

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param feedBacks
	 */
	private void insertInfoForFeedback(List<FeedBack> feedBacks) {
		List<Long> ids = new ArrayList<Long>();
		List<Long> courseIds = new ArrayList<Long>();
		for (FeedBack feedBack : feedBacks) {
			ids.add(feedBack.getFromUserId());
			ids.add(feedBack.getToUserId());
			courseIds.add(feedBack.getCourseId());
		}
		List<Profile> profileList = profileMapper.getProfileListByIds(ids);
		Map<Long, Profile> profileMap = HashMapMaker.listToMap(profileList, "getUserId", Profile.class);

		List<Course> courseList = courseMapper.getCourseListByIds(courseIds);
		Map<Long, Course> courseMap = HashMapMaker.listToMap(courseList, "getId", Course.class);
		for (FeedBack feedBack : feedBacks) {
			Profile fromProfile = profileMap.get(feedBack.getFromUserId());
			if (fromProfile != null) {
				feedBack.setFromName(fromProfile.getName());
			}
			Profile toProfile = profileMap.get(feedBack.getToUserId());
			if (toProfile != null) {
				feedBack.setToName(toProfile.getName());
			}
			feedBack.setCreateTimeStr(TimeUtil.getFormatTime(feedBack.getCreateTime()));
			Course course = courseMap.get(feedBack.getCourseId());
			if (course != null) {
				feedBack.setCourse(course);
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.FeedBackService#updateFeedBackReaded(long)
	 */
	@Override
	public boolean updateFeedBackReaded(long feedbackId) {
		return feedBackMapper.updateFeedBackReaded(feedbackId) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.FeedBackService#getFeedBackListFromUserId(long,
	 * int, int)
	 */
	@Override
	public List<FeedBack> getFeedBackListFromUserId(long fromUserId, int limit, int offset, long toUserId) {
		List<FeedBack> feedBacks = feedBackMapper.getFeedBacksByFromUserId(limit, offset, fromUserId, toUserId);
		if (ListUtils.isEmptyList(feedBacks)) {
			return null;
		}
		this.insertInfoForFeedback(feedBacks);
		return feedBacks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.FeedBackService#getFeedBackListCourseId(long,
	 * int, int, long)
	 */
	@Override
	public List<FeedBack> getFeedBackListCourseId(long courseId, int limit, int offset, long toUserId) {
		List<FeedBack> feedBacks = feedBackMapper.getFeedBacksByCourseId(limit, offset, courseId, toUserId);
		if (ListUtils.isEmptyList(feedBacks)) {
			return null;
		}
		this.insertInfoForFeedback(feedBacks);
		return feedBacks;
	}
}
