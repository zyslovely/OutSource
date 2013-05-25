package com.ruoogle.teach.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.ruoogle.teach.dwr.DwrPubTeachSysTeacherBean;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-25 下午02:44:25
 * @see Class Description
 */
@Controller("apiTeachSysPubController")
public class ApiTeachSysPubController extends AbstractBaseController {
	
	private static final Logger logger = Logger.getLogger(ApiTeachSysPubController.class);

	/**
	 * 登录
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView doLogin(HttpServletRequest request, HttpServletResponse response) {
		logger.info(request.getSession().getId());
		
		return null;
	}

}
