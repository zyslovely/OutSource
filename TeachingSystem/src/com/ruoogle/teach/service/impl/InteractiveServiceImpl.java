package com.ruoogle.teach.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eason.web.util.HashMapMaker;
import com.eason.web.util.ListUtils;
import com.ruoogle.teach.mapper.ClassMapper;
import com.ruoogle.teach.mapper.CourseMapper;
import com.ruoogle.teach.mapper.CourseStudentMapper;
import com.ruoogle.teach.mapper.InteractiveBackMapper;
import com.ruoogle.teach.mapper.InteractiveMapper;
import com.ruoogle.teach.mapper.ProfileMapper;
import com.ruoogle.teach.meta.Course;
import com.ruoogle.teach.meta.CourseStudent;
import com.ruoogle.teach.meta.Interactive;
import com.ruoogle.teach.meta.InteractiveBack;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.Profile.ProfileLevel;
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

	@Resource
	private CourseStudentMapper courseStudentMapper;
	@Resource
	private ProfileMapper profileMapper;

	@Resource
	private CourseMapper courseMapper;

	@Resource
	private ClassMapper classMapper;

	@Resource
	private InteractiveBackMapper interactiveBackMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.InteractiveService#addInteractive(long,
	 * java.lang.String, long, int, java.lang.String, long)
	 */
	@Override
	public boolean addInteractive(long userId, String content, long courseId, int status, String photoUrl, long forwardId) {

		Profile profile = profileMapper.getProfile(userId);
		if (profile == null) {
			return false;
		}
		// 如果是保密的
		if (status == 1) {
			return this.addOneInteractive(content, courseId, status, photoUrl, forwardId, userId, userId);
		}
		if (profile.getLevel() == ProfileLevel.Teacher.getValue() || profile.getLevel() == ProfileLevel.CompanyLeader.getValue()) {
			List<CourseStudent> courseStudents = courseStudentMapper.getCourseStudentsByCourseId(courseId);
			if (!ListUtils.isEmptyList(courseStudents)) {
				for (CourseStudent courseStudent : courseStudents) {
					this.addOneInteractive(content, courseId, status, photoUrl, forwardId, userId, courseStudent.getUserId());
				}
			}
			List<CourseStudent> courseTeachers = courseStudentMapper.getCourseTeacherByCourseId(courseId);
			if (!ListUtils.isEmptyList(courseTeachers)) {
				for (CourseStudent courseStudent : courseTeachers) {
					this.addOneInteractive(content, courseId, status, photoUrl, forwardId, userId, courseStudent.getUserId());
				}
			}
			return true;
		} else if (profile.getLevel() == ProfileLevel.Student.getValue()) {
			Course course = courseMapper.getCourseById(courseId);
			if (course != null) {
				List<CourseStudent> courseStudents = courseStudentMapper.getCourseStudentsByCourseId(courseId);
				for (CourseStudent courseStudent : courseStudents) {
					this.addOneInteractive(content, courseId, status, photoUrl, forwardId, userId, courseStudent.getUserId());
				}
				List<CourseStudent> courseTeachers = courseStudentMapper.getCourseTeacherByCourseId(courseId);
				if (!ListUtils.isEmptyList(courseTeachers)) {
					for (CourseStudent courseStudent : courseTeachers) {
						this.addOneInteractive(content, courseId, status, photoUrl, forwardId, userId, courseStudent.getUserId());
					}
				}
			} else {
				List<Profile> profileList = profileMapper.getProfileByClassId(profile.getClassId(), ProfileLevel.Student.getValue(), 0, -1);
				if (!ListUtils.isEmptyList(profileList)) {
					for (Profile profile2 : profileList) {
						this.addOneInteractive(content, courseId, status, photoUrl, forwardId, userId, profile2.getUserId());
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 添加一条
	 * 
	 * @auther zyslovely@gmail.com
	 * @param content
	 * @param courseId
	 * @param status
	 * @param photoUrl
	 * @param forwardId
	 * @param userId
	 * @param showUserId
	 * @return
	 */
	private boolean addOneInteractive(String content, long courseId, int status, String photoUrl, long forwardId, long userId, long showUserId) {
		Interactive interactive = new Interactive();
		interactive.setUserId(userId);
		interactive.setCourseId(courseId);
		interactive.setCreateTime(new Date().getTime());
		interactive.setForwardId(forwardId);
		interactive.setShowUserId(showUserId);
		interactive.setPhotoUrl(photoUrl);
		interactive.setStatus(status);
		interactive.setContent(content);
		return interactiveMapper.addInteractive(interactive) > 0;
	}

	/**
	 * 获取
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<Interactive> getInteractiveByUserId(long userId, int limit, int offset) {

		List<Interactive> interactives = interactiveMapper.getInteractiveListByShowUserId(userId, limit, offset);
		if (ListUtils.isEmptyList(interactives)) {
			return null;
		}
		List<Long> ids = new ArrayList<Long>();
		List<Long> courseIds = new ArrayList<Long>();
		for (Interactive interactive : interactives) {
			ids.add(interactive.getUserId());
			courseIds.add(interactive.getCourseId());
		}
		List<Profile> profiles = profileMapper.getProfileListByIds(ids);
		Map<Long, Profile> profileMap = HashMapMaker.listToMap(profiles, "getUserId", Profile.class);
		List<Course> courseList = courseMapper.getCourseListByIds(courseIds);
		Map<Long, Course> courseMap = HashMapMaker.listToMap(courseList, "getId", Course.class);
		for (Interactive interactive : interactives) {
			Profile profile = profileMap.get(interactive.getUserId());
			if (profile != null) {
				interactive.setName(profile.getName());
			}
			if (interactive.getCourseId() != 0) {
				Course course = courseMap.get(interactive.getCourseId());
				if (course != null) {
					interactive.setCourseName(course.getName());
				}
			}
			if (interactive.getForwardId() > 0) {
				Interactive interactive2 = interactiveMapper.getInteractive(interactive.getForwardId());
				if (interactive2 != null) {
					Profile profile2 = profileMapper.getProfile(interactive2.getUserId());
					interactive.setForwardFromStr("转发自:" + profile2.getName());
				}
			}
			List<InteractiveBack> interactiveBackList = interactiveBackMapper.getInteractiveBack(interactive.getId());
			if (!ListUtils.isEmptyList(interactiveBackList)) {
				interactive.setSubInteractiveBackList(interactiveBackList);
			}
		}
		return interactives;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.InteractiveService#getInteractiveCountByUserId
	 * (long)
	 */
	public int getInteractiveCountByUserId(long userId) {
		return interactiveMapper.getInteractieTotalCount(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.InteractiveService#addForward(long,
	 * java.lang.String)
	 */
	@Override
	public boolean addForward(long id, String content, long userId) {
		Interactive interactive = interactiveMapper.getInteractive(id);
		if (interactive == null) {
			return false;
		}
		return this.addInteractive(userId, content, interactive.getCourseId(), 0, "", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.InteractiveService#addForwardBack(long,
	 * java.lang.String)
	 */
	@Override
	public boolean addForwardBack(long id, String content, long userId) {
		InteractiveBack interactiveBack = new InteractiveBack();
		interactiveBack.setContent(content);
		interactiveBack.setUserId(userId);
		interactiveBack.setCreateTime(new Date().getTime());
		interactiveBack.setInteractiveId(id);
		Profile profile = profileMapper.getProfile(userId);
		if (profile != null) {
			interactiveBack.setName(profile.getName());
		}
		return interactiveBackMapper.addInteractiveBack(interactiveBack) > 0;
	}
}
