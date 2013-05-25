package com.ruoogle.teach.dwr;

import javax.annotation.Resource;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.FeedBackService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-25 下午01:09:23
 * @see Class Description
 */
@Service("dwrTeachSysBean")
public class DwrTeachSysBean {
	@Resource
	private FeedBackService feedBackService;

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

}
