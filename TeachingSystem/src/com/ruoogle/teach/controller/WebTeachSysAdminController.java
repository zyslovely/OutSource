package com.ruoogle.teach.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午12:50:55
 * @see Class Description
 */
@Controller("webTeachSysAdminController")
public class WebTeachSysAdminController extends AbstractBaseController {

	private static final Logger logger = Logger.getLogger(WebTeachSysAdminController.class);

	/**
	 * 添加百分比
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAdminNewPerCentTypeView(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("admin_newPercent");
		return mv;
	}
}
