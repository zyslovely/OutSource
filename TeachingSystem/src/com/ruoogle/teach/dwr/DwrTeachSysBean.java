package com.ruoogle.teach.dwr;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import com.eason.web.util.StringUtil;
import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.ClassService;
import com.ruoogle.teach.service.CourseService;
import com.ruoogle.teach.service.FeedBackService;
import com.ruoogle.teach.service.InteractiveService;
import com.ruoogle.teach.service.ProfileService;

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
	@Resource
	private ProfileService profileService;

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
	public boolean addFeedBack(long toUserId, long feedbackId, String content,
			long courseId) {
		WebContext ctx = WebContextFactory.get();
		Long fromUserId = MyUser.getMyUser(ctx.getHttpServletRequest());
		return feedBackService.addFeedBack(toUserId, feedbackId, content,
				courseId, fromUserId);
	}

	/**
	 * 添加互动内容
	 */
	public boolean addInteractive(String content, long courseId, int status,
			String photoUrl, long forwardId) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		if (StringUtils.isEmpty(content)) {
			return false;
		}
		return interactiveService.addInteractive(userId, content, courseId,
				status, photoUrl, forwardId);
	}

	/**
	 * 转发
	 * 
	 * @auther zyslovely@gmail.com
	 * @param content
	 * @param forwardId
	 * @return
	 */
	public boolean addForward(String content, long forwardId) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		if (StringUtils.isEmpty(content)) {
			return false;
		}
		return interactiveService.addForward(forwardId, content, userId);
	}

	public boolean addInteractiveBack(long id, String content) {

		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		if (StringUtils.isEmpty(content)) {
			return false;
		}
		return interactiveService.addForwardBack(id, content, userId);
	}

	/**
	 * 添加日志
	 */
	public boolean addJournal(String content, int type, long courseId) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		return classService.addJournal(content, type, courseId, userId);
	}

	/**
	 * 改密码
	 * 
	 * @auther zyslovely@gmail.com
	 * @param newPassword1
	 * @return
	 */
	public boolean changePass(String newPassword1, String newPassword2) {
		if (StringUtils.isEmpty(newPassword2)
				|| StringUtils.isEmpty(newPassword1)) {
			return false;
		}
		if (!newPassword1.equals(newPassword2)) {
			return false;
		}
		if (!StringUtil.isAllShuziYinwen(newPassword1)) {
			return false;
		}
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());

		return profileService.changePassword(userId, newPassword1);
	}

	/**
	 * 删除互动
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public boolean deleteInteractive(long id) {
		return interactiveService.deleteInteractive(id);
	}

	/**
	 * 删除互动评论
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public boolean deleteInteractiveBack(long id) {
		return interactiveService.deleteInteractiveBack(id);
	}

}
