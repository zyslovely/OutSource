package com.ruoogle.teach.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.ruoogle.teach.constant.BasicObjectConstant;
import com.ruoogle.teach.constant.ReturnCodeConstant;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.security.MySecurityDelegatingFilter;
import com.ruoogle.teach.security.MyUser;

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
		ModelAndView modelAndView = new ModelAndView("return");
		Long userId = MyUser.getMyUserFromToken(request);
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		Profile profile = profileService.getProfile(userId);
		JSONObject returnObject = new JSONObject();
		if (profile == null) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code, ReturnCodeConstant.UserNoFound);
			return modelAndView;
		}

		JSONObject dataObject = new JSONObject();
		dataObject.put(Profile.KProfile_userName, profile.getUserName());
		dataObject.put(Profile.KProfile_Name, profile.getName());
		dataObject.put(Profile.KProfile_passWord, profile.getPassword());
		dataObject.put(Profile.KProfile_token, myUser.getApiToken());
		dataObject.put(Profile.KProfile_level, profile.getLevel());
		returnObject.put(BasicObjectConstant.kReturnObject_Data, dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code, ReturnCodeConstant.SUCCESS);
		return modelAndView;
	}
}
