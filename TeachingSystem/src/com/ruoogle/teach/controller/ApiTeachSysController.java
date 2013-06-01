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

	@Resource
	private SchoolInfoService schoolInfoService;

	/**
	 * 校园信息
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showSchoolInfoList(HttpServletRequest request, HttpServletResponse response) {
		logger.info(request.getSession().getId());
		int type = ServletRequestUtils.getIntParameter(request, "type", -1);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 0);
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		if (type < 0) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code, ReturnCodeConstant.FAILED);
			return modelAndView;
		}
		List<SchoolInfo> schoolInfoList = schoolInfoService.getSchoolInfoList(limit, offset, type);
		JSONObject dataObject = new JSONObject();
		JSONArray schoolInfoArray = new JSONArray();
		if (!ListUtils.isEmptyList(schoolInfoList)) {
			for (SchoolInfo schoolInfo : schoolInfoList) {
				JSONObject schoolInfoObject = new JSONObject();
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_title, schoolInfo.getTitle());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_content, schoolInfo.getContent());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_imgUrl, schoolInfo.getImgUrl());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_infoType, schoolInfo.getInfoType());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_type, schoolInfo.getType());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_createTime, schoolInfo.getCreateTime());
				schoolInfoArray.add(schoolInfoObject);
			}
		}
		dataObject.put("schoolInfoList", schoolInfoArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data, dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code, ReturnCodeConstant.SUCCESS);
		modelAndView.addObject("returnObject", returnObject.toString());
		return modelAndView;
	}
}
