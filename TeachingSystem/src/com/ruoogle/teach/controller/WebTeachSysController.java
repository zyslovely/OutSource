package com.ruoogle.teach.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.ruoogle.teach.security.MySecurityDelegatingFilter;
import com.ruoogle.teach.security.MyUser;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-17 上午12:16:41
 * @see Class Description
 */
@Controller("webTeachSysController")
public class WebTeachSysController extends AbstractBaseController {
	private static final Logger logger = Logger.getLogger(WebTeachSysController.class);
	/**
	 * 退出
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Long userId = MyUser.getMyUser(request);
		MySecurityDelegatingFilter.userMap.remove(userId);
		HttpSession session = request.getSession();
		session.removeAttribute("login");
		session.removeAttribute("userId");
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 登陆后首页
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView teachIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("teachIndex");
		this.setUD(mv, request);
		return mv;
	}

	
}
