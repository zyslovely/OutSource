package com.ruoogle.teach.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-17 上午12:16:54
 * @see Class Description
 */
@Controller("webTeachSysPubController")
public class WebTeachSysPubController extends AbstractBaseController {
	/**
	 * 首页
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showIndexView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("webIndex");
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
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response) {
		logger.info(request.getSession().getId());
		try {
			response.sendRedirect("/teach/index/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
