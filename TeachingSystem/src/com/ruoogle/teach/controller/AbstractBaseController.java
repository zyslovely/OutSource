package com.ruoogle.teach.controller;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.Profile.ProfileLevel;
import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.FeedBackService;
import com.ruoogle.teach.service.ProfileService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@corp.netease.com
 * @version CreateTime2012-3-5 01:45:43 Class Description
 */
public abstract class AbstractBaseController extends MultiActionController {
	@Resource(name = "paramResolver")
	private MethodNameResolver methodNameResolver;

	@Resource
	protected ProfileService profileService;
	@Resource
	protected FeedBackService feedBackService;

	/**
	 * 初始化
	 */
	@PostConstruct
	public void baseInit() {
		super.setMethodNameResolver(methodNameResolver);
	}

	/**
	 * 初始数据
	 * 
	 * @auther zyslovely@gmail.com
	 * @param mv
	 */
	public void setUD(ModelAndView mv, HttpServletRequest request) {
		Long userId = MyUser.getMyUser(request);
		Profile profile = profileService.getProfile(userId);

		switch (ProfileLevel.genProfileLevel(profile.getLevel())) {
		case Student:
			mv.addObject("levelName", "学生");
			break;
		case Teacher:
			mv.addObject("levelName", "老师");
			break;
		case CompanyLeader:
			mv.addObject("levelName", "企业老师");
			break;
		case Admin:
			mv.addObject("levelName", "管理员");
			break;
		default:
			break;
		}
		mv.addObject("visitUserId", profile.getUserId());
		mv.addObject("visitUserName", profile.getUserName());
		mv.addObject("passWord", profile.getPassword());
		mv.addObject("visitName", profile.getName());
		mv.addObject("level", profile.getLevel());
		int unreadCount = feedBackService.getUnreadCount(userId);
		mv.addObject("unreadCount", unreadCount);
	}

	/**
	 * 读取文件
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @return
	 * @throws FileUploadException
	 */
	public Iterator<FileItem> getUPFiles(HttpServletRequest request) throws FileUploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		return upload.parseRequest(request).iterator();
	}
}
