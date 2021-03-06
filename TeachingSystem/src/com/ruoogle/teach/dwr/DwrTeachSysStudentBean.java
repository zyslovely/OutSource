package com.ruoogle.teach.dwr;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.CourseService;
import com.ruoogle.teach.service.ProfileService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-22 上午12:13:14
 * @see Class Description
 */
@Service("dwrTeachSysStudentBean")
public class DwrTeachSysStudentBean {

	private static final Logger logger = Logger
			.getLogger(DwrTeachSysStudentBean.class);
	@Resource
	private CourseService courseService;
	@Resource
	ProfileService profileService;

	/**
	 * 添加分组分数
	 * 
	 * @auther zyslovely@gmail.com
	 * @param toStudentId
	 * @param courseId
	 * @param groupId
	 * @param score
	 * @return
	 */
	public boolean addGroupScore(long toStudentId, long courseId, long groupId,
			double score) {
		WebContext ctx = WebContextFactory.get();
		Long fromStudentId = MyUser.getMyUser(ctx.getHttpServletRequest());
		return courseService.addGroupScore(toStudentId, courseId, groupId,
				score, fromStudentId);
	}

}
