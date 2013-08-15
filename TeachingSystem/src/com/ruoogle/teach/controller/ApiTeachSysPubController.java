package com.ruoogle.teach.controller;

import java.io.UnsupportedEncodingException;
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
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.SchoolInfo;
import com.ruoogle.teach.security.MySecurityDelegatingFilter;
import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.ClassService;
import com.ruoogle.teach.service.SchoolInfoService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-25 下午02:44:25
 * @see Class Description
 */
@Controller("apiTeachSysPubController")
public class ApiTeachSysPubController extends AbstractBaseController {

	private static final Logger logger = Logger
			.getLogger(ApiTeachSysPubController.class);

	@Resource
	private SchoolInfoService schoolInfoService;
	@Resource
	private ClassService classService;

	/**
	 * 登录
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView doLogin(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());
		ModelAndView modelAndView = new ModelAndView("return");
		String userName = ServletRequestUtils.getStringParameter(request,
				"username", null);
		Profile profile = profileService.getProfileByUserName(userName);

		JSONObject returnObject = new JSONObject();
		if (profile == null) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.UserNoFound);
			modelAndView.addObject("returnObject", returnObject);
			return modelAndView;
		}
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(profile
				.getUserId());
		JSONObject dataObject = new JSONObject();
		dataObject.put(Profile.KProfile_userName, profile.getUserName());
		dataObject.put(Profile.KProfile_Name, profile.getName());
		dataObject.put(Profile.KProfile_passWord, profile.getPassword());
		dataObject.put(Profile.KProfile_userId, profile.getUserId());
		dataObject.put(Profile.KProfile_token, myUser.getApiToken());
		dataObject.put(Profile.KProfile_level, profile.getLevel());
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		modelAndView.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return modelAndView;
	}

	/**
	 * 加入校园信息活动
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public ModelAndView joinSchoolInfo(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		logger.info(request.getSession().getId());

		long infoId = ServletRequestUtils.getLongParameter(request, "infoId",
				-1L);
		long userId = MyUser.getMyUserFromToken(request);
		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
//		if (userId < 0) {
//			returnObject.put(BasicObjectConstant.kReturnObject_Code,
//					ReturnCodeConstant.FAILED);
//			modelAndView.addObject("returnObject", returnObject.toString());
//			return modelAndView;
//		}
		SchoolInfo schoolInfo = schoolInfoService.getSchoolInfo(infoId, userId);
		if (schoolInfo.getStatus() != SchoolInfo.SchoolInfoStatus.ongoing
				.getValue()) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			modelAndView.addObject("returnObject", returnObject.toString());
			return modelAndView;
		}
		boolean succ;
		long phoneNum = ServletRequestUtils.getLongParameter(request,
				"phoneNum", -1L);
		if (schoolInfo.getType() == SchoolInfo.SchoolInfoType.specialty
				.getValue()) {
			succ = schoolInfoService.joinSchoolInfo(userId, infoId, phoneNum);
		} else if (schoolInfo.getType() == SchoolInfo.SchoolInfoType.school
				.getValue()) {
			String name = ServletRequestUtils.getStringParameter(request,
					"name", "");
			name = new String(name.getBytes("iso-8859-1"), "utf-8");
			String origin = ServletRequestUtils.getStringParameter(request,
					"origin", "");
			origin = new String(origin.getBytes("iso-8859-1"), "utf-8");
			String graduateSch = ServletRequestUtils.getStringParameter(
					request, "graduateSch", "");
			graduateSch = new String(graduateSch.getBytes("iso-8859-1"),
					"utf-8");
			succ = schoolInfoService.joinSchoolInfo(infoId, name, origin,
					phoneNum, graduateSch);
		} else {
			succ = false;
		}
		if (succ) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.SUCCESS);
		} else {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
		}
		returnObject.put(BasicObjectConstant.kReturnObject_Data, "");
		modelAndView.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return modelAndView;
	}

	/**
	 * 显示单个校园信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showSchoolInfo(HttpServletRequest request,
			HttpServletResponse response) {

		long id = ServletRequestUtils.getLongParameter(request, "infoId", -1L);
		long userId = MyUser.getMyUserFromToken(request);

		SchoolInfo schoolInfo = schoolInfoService.getSchoolInfo(id, userId);
		JSONObject dataObject = new JSONObject();
		JSONObject schoolInfoObject = new JSONObject();
		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		if (schoolInfo != null) {
			schoolInfoObject.put(SchoolInfo.KSchoolInfo_id, schoolInfo.getId());
			schoolInfoObject.put(SchoolInfo.KSchoolInfo_title,
					schoolInfo.getTitle());
			schoolInfoObject.put(SchoolInfo.KSchoolInfo_content,
					schoolInfo.getContent());
			schoolInfoObject.put(SchoolInfo.KSchoolInfo_bImgUrl,
					schoolInfo.getbImgUrl());
			schoolInfoObject.put(SchoolInfo.KSchoolInfo_sImgUrl,
					schoolInfo.getsImgUrl());
			schoolInfoObject.put(SchoolInfo.KSchoolInfo_infoType,
					schoolInfo.getInfoType());
			schoolInfoObject.put(SchoolInfo.KSchoolInfo_type,
					schoolInfo.getType());
			schoolInfoObject.put(SchoolInfo.KSchoolInfo_createTime,
					schoolInfo.getCreateTime());
			schoolInfoObject.put(SchoolInfo.KSchoolInfo_joined,
					schoolInfo.getJoined());
			schoolInfoObject.put(SchoolInfo.KSchoolInfo_status,
					schoolInfo.getStatus());
			dataObject.put("schoolInfo", schoolInfoObject.toString());
		}

		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		modelAndView.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return modelAndView;
	}

	/**
	 * 校园信息
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showSchoolInfoList(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());
		int type = ServletRequestUtils.getIntParameter(request, "type", -1);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 0);
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		long userId = MyUser.getMyUserFromToken(request);

		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		if (type < 0) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			modelAndView.addObject("returnObject", returnObject);
			return modelAndView;
		}
		List<SchoolInfo> schoolInfoList = schoolInfoService.getSchoolInfoList(
				limit, offset, type, userId);
		JSONObject dataObject = new JSONObject();
		JSONArray schoolInfoArray = new JSONArray();
		if (!ListUtils.isEmptyList(schoolInfoList)) {
			for (SchoolInfo schoolInfo : schoolInfoList) {
				JSONObject schoolInfoObject = new JSONObject();
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_id,
						schoolInfo.getId());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_title,
						schoolInfo.getTitle());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_content,
						schoolInfo.getContent());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_bImgUrl,
						schoolInfo.getbImgUrl());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_sImgUrl,
						schoolInfo.getsImgUrl());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_infoType,
						schoolInfo.getInfoType());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_type,
						schoolInfo.getType());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_createTime,
						schoolInfo.getCreateTime());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_joined,
						schoolInfo.getJoined());
				schoolInfoObject.put(SchoolInfo.KSchoolInfo_status,
						schoolInfo.getStatus());
				schoolInfoArray.add(schoolInfoObject);
			}
		}
		dataObject.put("schoolInfoList", schoolInfoArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		modelAndView.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return modelAndView;
	}

}
