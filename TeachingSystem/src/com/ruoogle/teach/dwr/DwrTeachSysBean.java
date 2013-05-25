package com.ruoogle.teach.dwr;

import javax.annotation.Resource;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.ClassService;
import com.ruoogle.teach.service.CourseService;
import com.ruoogle.teach.service.FeedBackService;
import com.ruoogle.teach.service.InteractiveService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-25 下午01:09:23
 * @see Class Description
 */
@Service("dwrTeachSysBean")
public class DwrTeachSysBean {
	@Resource
	private FeedBackService feedBackService;
	@Resource
	private InteractiveService interactiveService;
	@Resource
	private CourseService courseService;
	@Resource
	private ClassService classService;

	/**
	 * 添加反馈
	 * 
	 * @auther zyslovely@gmail.com
	 * @param fromUserId
	 * @param toUserId
	 * @param feedbackId
	 * @param content
	 * @param courseId
	 * @return
	 */
	public boolean addFeedBack(long toUserId, long feedbackId, String content, long courseId) {
		WebContext ctx = WebContextFactory.get();
		Long fromUserId = MyUser.getMyUser(ctx.getHttpServletRequest());
		return feedBackService.addFeedBack(toUserId, feedbackId, content, courseId, fromUserId);
	}

	/**
	 * 添加互动内容
	 */
	public boolean addInteractive(String content, long courseId, int status, String photoUrl, long forwardId) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		return interactiveService.addInteractive(userId, content, courseId, status, photoUrl, forwardId);
	}

	/**
	 * 添加日志
	 */
	public boolean addJournal(String content, int type, long courseId) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		return classService.addJournal(content, type, courseId, userId);
	}
}
