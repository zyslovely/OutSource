package com.ruoogle.teach.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.ruoogle.teach.meta.Profile.ProfileLevel;
import com.ruoogle.teach.security.MySecurityDelegatingFilter;
import com.ruoogle.teach.security.MyUser;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-17 上午12:16:54
 * @see Class Description
 */
@Controller("webTeachSysPubController")
public class WebTeachSysPubController extends AbstractBaseController {

	private static final Logger logger = Logger
			.getLogger(WebTeachSysPubController.class);

	/**
	 * 首页
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showIndexView(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("webIndex");

		int error = ServletRequestUtils.getIntParameter(request, "error", 0);
		mv.addObject("error", error);
		return mv;
	}

	/**
	 * 登陆
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView doLogin(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());
		Long userId = MyUser.getMyUser(request);
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);

		try {
			if (myUser == null) {
				response.sendRedirect("/");
			} else if (myUser.getLevel() == ProfileLevel.Admin.getValue()) {
				response.sendRedirect("/teach/admin/specialty/list/");
			} else {
				response.sendRedirect("/teach/index/");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
