package com.ruoogle.teach.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.eason.web.util.ListUtils;
import com.ruoogle.teach.constant.BasicObjectConstant;
import com.ruoogle.teach.constant.ReturnCodeConstant;
import com.ruoogle.teach.meta.SchoolInfo;
import com.ruoogle.teach.service.SchoolInfoService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-25 下午02:44:15
 * @see Class Description
 */
@Controller("apiTeachSysController")
public class ApiTeachSysController extends AbstractBaseController {
	private static final Logger logger = Logger.getLogger(ApiTeachSysController.class);




}
