package com.ruoogle.teach.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.eason.web.util.DoubleUtil;
import com.eason.web.util.FileUtil;
import com.eason.web.util.ListUtils;
import com.ruoogle.teach.constant.BasicObjectConstant;
import com.ruoogle.teach.constant.ReturnCodeConstant;
import com.ruoogle.teach.meta.Class;
import com.ruoogle.teach.meta.Course;
import com.ruoogle.teach.meta.CoursePercentTypeGroup;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudent;
import com.ruoogle.teach.meta.CoursePercentTypeGroupStudentVO;
import com.ruoogle.teach.meta.CourseProperty;
import com.ruoogle.teach.meta.CourseScorePercent;
import com.ruoogle.teach.meta.CourseStudentPropertySemesterScore;
import com.ruoogle.teach.meta.CourseStudentScore;
import com.ruoogle.teach.meta.CourseStudentTotalScore;
import com.ruoogle.teach.meta.CourseVO;
import com.ruoogle.teach.meta.FeedBack;
import com.ruoogle.teach.meta.Interactive;
import com.ruoogle.teach.meta.InteractiveBack;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.Profile.ProfileLevel;
import com.ruoogle.teach.meta.SearchProfile;
import com.ruoogle.teach.meta.SearchProperty;
import com.ruoogle.teach.meta.Semester;
import com.ruoogle.teach.meta.Specialty;
import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.ClassService;
import com.ruoogle.teach.service.CourseService;
import com.ruoogle.teach.service.InteractiveService;
import com.ruoogle.teach.service.SchoolInfoService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-25 下午02:44:15
 * @see Class Description
 */
@Controller("apiTeachSysController")
public class ApiTeachSysController extends AbstractBaseController {
	private static final Logger logger = Logger
			.getLogger(ApiTeachSysController.class);
	@Resource
	private CourseService courseService;
	@Resource
	private ClassService classService;
	@Resource
	private SchoolInfoService schoolInfoService;
	@Resource
	private InteractiveService interactiveService;

	public static final String HOST = "http://teach.zys-wings.com";

	/**
	 * 课程列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showCourseList(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());
		long userId = MyUser.getMyUserFromToken(request);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 0);
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		long semesterId = ServletRequestUtils.getLongParameter(request,
				"semesterId", -1L);
		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		if (userId < 0) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			return modelAndView;
		}
		if (semesterId < 0) {
			semesterId = courseService.getLastestSemesterId(userId);
		}
		Profile profile = profileService.getProfile(userId);
		List<CourseVO> courseList = courseService.getCourseVOListByUserId(
				userId, profile.getLevel(), semesterId, limit, offset);
		JSONObject dataObject = new JSONObject();
		List<CourseStudentPropertySemesterScore> courseStudentPropertySemesterScores = null;
		List<CourseProperty> coursePropertieList = null;
		// 传入雷达图信息
		if (profile.getLevel() == ProfileLevel.Student.getValue()
				&& offset <= 0) {
			courseStudentPropertySemesterScores = courseService
					.getCourseStudentPropertySemesterScoresByStudentId(
							profile.getUserId(), semesterId);
			if (!ListUtils.isEmptyList(courseStudentPropertySemesterScores)) {
				double maxScore = 0;
				for (CourseStudentPropertySemesterScore courseStudentPropertySemesterScore : courseStudentPropertySemesterScores) {
					if (courseStudentPropertySemesterScore.getScore() > maxScore) {
						maxScore = courseStudentPropertySemesterScore
								.getScore();
					}
				}
				for (CourseStudentPropertySemesterScore courseStudentPropertySemesterScore : courseStudentPropertySemesterScores) {
					double endScore = courseStudentPropertySemesterScore
							.getScore() / maxScore * 10;
					courseStudentPropertySemesterScore
							.setScore(DoubleUtil.round(endScore, 2,
									RoundingMode.HALF_UP.ordinal()));
				}
				coursePropertieList = courseService.getAllCourseProperties();
			}
		}
		HttpReturn.returnShowCourseList(courseList,
				courseStudentPropertySemesterScores, coursePropertieList,
				dataObject);
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		modelAndView.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return modelAndView;
	}

	/**
	 * 学生列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showStudentList(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());

		int limit = ServletRequestUtils.getIntParameter(request, "limit", 0);
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int classId = ServletRequestUtils
				.getIntParameter(request, "classId", 0);
		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		List<Profile> profileList = profileService.getProfileListByClassId(
				ProfileLevel.Student.getValue(), limit, offset, classId);

		JSONObject dataObject = new JSONObject();
		JSONArray courseArray = new JSONArray();
		if (!ListUtils.isEmptyList(profileList)) {
			for (Profile profile : profileList) {
				JSONObject courseObject = new JSONObject();
				courseObject.put(Profile.KProfile_Name, profile.getName());
				courseObject.put(Profile.KProfile_userId, profile.getUserId());
				courseArray.add(courseObject);
			}
		}
		dataObject.put("profileList", courseArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		modelAndView.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return modelAndView;
	}

	/**
	 * 显示参加活动的学生信息(老师)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showSchoolInfoUsers(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());

		int limit = ServletRequestUtils.getIntParameter(request, "limit", 0);
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		long infoId = ServletRequestUtils.getLongParameter(request, "infoId",
				-1L);
		long userId = MyUser.getMyUserFromToken(request);
		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		Profile teachProfile = profileService.getProfile(userId);

		if (teachProfile.getLevel() != ProfileLevel.Admin.getValue()
				&& teachProfile.getLevel() != ProfileLevel.Teacher.getValue()
				&& teachProfile.getLevel() != ProfileLevel.CompanyLeader
						.getValue()) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			modelAndView.addObject("returnObject", returnObject.toString());
			return modelAndView;
		}

		List<Profile> profileList = schoolInfoService
				.getJoinedSchoolInfoUserList(limit, offset, infoId);

		JSONObject dataObject = new JSONObject();
		JSONArray courseArray = new JSONArray();
		if (!ListUtils.isEmptyList(profileList)) {
			for (Profile profile : profileList) {
				JSONObject profileObject = new JSONObject();
				profileObject.put(Profile.KProfile_Name, profile.getName());
				profileObject.put(Profile.KProfile_className,
						profile.getClassName());
				profileObject.put(Profile.KProfile_specialtyName,
						profile.getSpecialtyName());
				profileObject.put(Profile.KProfile_graduateSch,
						profile.getGraduateSch());
				profileObject.put(Profile.KProfile_origin, profile.getOrigin());
				profileObject.put(Profile.KProfile_phoneNum,
						profile.getPhoneNum());
				courseArray.add(profileObject);
			}
		}
		dataObject.put("profileList", courseArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		modelAndView.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return modelAndView;
	}

	/**
	 * 取消加入校园信息活动
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView removeSchoolInfo(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());

		long infoId = ServletRequestUtils.getLongParameter(request, "infoId",
				-1L);
		long userId = ServletRequestUtils.getLongParameter(request, "userId",
				-1L);
		long adminId = MyUser.getMyUserFromToken(request);

		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();

		if (userId < 0) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			modelAndView.addObject("returnObject", returnObject.toString());
			return modelAndView;
		}

		boolean succ = schoolInfoService.removeSchoolInfo(userId, infoId,
				adminId);

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
	 * 获取反馈列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView getFeedBackList(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());

		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();

		long userId = MyUser.getMyUserFromToken(request);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 0);
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);

		List<FeedBack> feedBackList = feedBackService.getFeedBackList(userId,
				limit, offset, 0);
		JSONObject dataObject = new JSONObject();
		JSONArray feedBackArray = new JSONArray();
		if (!ListUtils.isEmptyList(feedBackList)) {
			for (FeedBack feedBack : feedBackList) {
				JSONObject feedBackObject = new JSONObject();
				feedBackObject.put("id", feedBack.getId());
				feedBackObject.put("fromUserId", feedBack.getFromUserId());
				feedBackObject.put("toUserId", feedBack.getToUserId());
				feedBackObject.put("content", feedBack.getContent());
				feedBackObject.put("createTime", feedBack.getCreateTime());
				if (feedBack.getCourse() != null) {
					feedBackObject.put("courseName", feedBack.getCourse()
							.getName());
					feedBackObject
							.put("courseId", feedBack.getCourse().getId());
				}
				feedBackObject.put("status", feedBack.getStatus());
				feedBackObject.put("feedbackId", feedBack.getFeedbackId());
				feedBackObject.put("fromName", feedBack.getFromName());
				feedBackObject.put("toName", feedBack.getToName());

				feedBackArray.add(feedBackObject);
			}
		}

		dataObject.put("feedbackList", feedBackArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		modelAndView.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return modelAndView;
	}

	/**
	 * 学期列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showSemesterList(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());
		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		List<Semester> semesterList = classService.getAllSemesters();
		JSONObject dataObject = new JSONObject();
		JSONArray semesterArray = new JSONArray();
		if (!ListUtils.isEmptyList(semesterList)) {
			for (Semester semester : semesterList) {
				JSONObject semesterObject = new JSONObject();
				semesterObject.put(Semester.KSemester_id, semester.getId());
				semesterObject.put(Semester.KSemester_name, semester.getName());
				semesterArray.add(semesterObject);
			}
		}
		dataObject.put("semesterList", semesterArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		modelAndView.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return modelAndView;
	}

	/**
	 * 添加反馈
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public ModelAndView addFeedBack(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		logger.info(request.getSession().getId());

		long toUserId = ServletRequestUtils.getLongParameter(request,
				"toUserId", -1L);
		long feedbackId = ServletRequestUtils.getLongParameter(request,
				"feedbackId", -1L);
		String content = request.getParameter("content");
		content = new String(content.getBytes("iso-8859-1"), "utf-8");

		long courseId = ServletRequestUtils.getLongParameter(request,
				"courseId", -1L);
		long fromUserId = MyUser.getMyUserFromToken(request);

		logger.info("addfeedback  " + content);
		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();

		if (toUserId < 0 || feedbackId < 0 || content == null || courseId < 0) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			modelAndView.addObject("returnObject", returnObject.toString());
			return modelAndView;
		}

		boolean succ = feedBackService.addFeedBack(toUserId, feedbackId,
				content, courseId, fromUserId);

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
	 * 显示搜索
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showSearch(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());
		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();

		long specialtyId = ServletRequestUtils.getLongParameter(request,
				"specialtyId", -1L);
		long classId = ServletRequestUtils.getLongParameter(request, "classId",
				-1L);
		long semesterId = ServletRequestUtils.getLongParameter(request,
				"semesterId", -1L);
		String properties = ServletRequestUtils.getStringParameter(request,
				"properties", null);

		if (!StringUtils.isEmpty(properties)) {
			String[] onePropertiesList = properties.split(";");
			if (!ArrayUtils.isEmpty(onePropertiesList)) {
				List<SearchProperty> searchProperties = new ArrayList<SearchProperty>();
				for (String str : onePropertiesList) {
					SearchProperty searchProperty = new SearchProperty();
					String[] propertyIdValue = str.split(",");
					if (ArrayUtils.isEmpty(onePropertiesList)) {
						continue;
					}
					if (propertyIdValue.length < 2
							|| StringUtils.isEmpty(propertyIdValue[0])
							|| StringUtils.isEmpty(propertyIdValue[1])) {
						continue;
					}
					searchProperty.setPropertyId(Long
							.valueOf(propertyIdValue[0]));
					searchProperty.setValue(Double.valueOf(propertyIdValue[1]));
					searchProperties.add(searchProperty);
				}
				if (!ListUtils.isEmptyList(searchProperties)) {
					List<SearchProfile> searchProfileList = courseService
							.getSearchProfile(semesterId, classId, specialtyId,
									searchProperties);
					JSONObject dataObject = new JSONObject();
					JSONArray searchProfileArray = new JSONArray();
					if (!ListUtils.isEmptyList(searchProfileList)) {
						for (SearchProfile searchProfile : searchProfileList) {
							JSONObject searchProfileObject = new JSONObject();
							if (searchProfile.getProfile() != null) {
								searchProfileObject.put(
										Profile.KProfile_userId, searchProfile
												.getProfile().getUserId());
								searchProfileObject.put(Profile.KProfile_Name,
										searchProfile.getProfile().getName());
							}
							JSONArray cSPSSArray = new JSONArray();
							if (!ListUtils
									.isEmptyList(searchProfile
											.getCourseStudentPropertySemesterScoreList())) {
								for (CourseStudentPropertySemesterScore courseStudentPropertySemesterScore : searchProfile
										.getCourseStudentPropertySemesterScoreList()) {
									JSONObject cSPSSObject = new JSONObject();
									BigDecimal b = new BigDecimal(
											courseStudentPropertySemesterScore
													.getScore());
									double f1 = b.setScale(2,
											BigDecimal.ROUND_HALF_UP)
											.doubleValue();
									cSPSSObject.put("score", f1);
									List<CourseProperty> courseProperties = courseService
											.getAllCourseProperties();
									for (CourseProperty courseProperty : courseProperties) {
										if (courseProperty.getId() == courseStudentPropertySemesterScore
												.getPropertyId()) {
											cSPSSObject.put("name",
													courseProperty.getName());
											break;
										}
									}

									cSPSSArray.add(cSPSSObject);
								}
							}

							searchProfileObject.put(
									"courseStudentPropertySemesterScoreList",
									cSPSSArray.toString());
							BigDecimal b = new BigDecimal(
									searchProfile.getTotalScore());
							double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP)
									.doubleValue();
							searchProfileObject.put("totalScore", f1);
							searchProfileArray.add(searchProfileObject);
						}
					}
					dataObject.put("searchProfileList",
							searchProfileArray.toString());
					returnObject.put(BasicObjectConstant.kReturnObject_Data,
							dataObject.toString());
					returnObject.put(BasicObjectConstant.kReturnObject_Code,
							ReturnCodeConstant.SUCCESS);
					mv.addObject("returnObject", returnObject.toString());
					logger.info(returnObject.toString());
					return mv;
				}
			}
		}
		return mv;
	}

	/**
	 * @Title: getAllSpecialty
	 * @Description: 获取所有专业
	 * @Auther: yunshang_734@163.com
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView getAllSpecialty(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());
		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		JSONObject dataObject = new JSONObject();
		JSONArray specialtyArray = new JSONArray();
		List<Specialty> specialtyList = classService.getSpecialties();
		if (!ListUtils.isEmptyList(specialtyList)) {
			for (Specialty specialty : specialtyList) {
				JSONObject specialtyObject = new JSONObject();
				specialtyObject.put("specialty", specialty.getSpecialty());
				specialtyObject.put("specialtyId", specialty.getId());
				specialtyObject.put("shortSpecialty",
						specialty.getShortSpecialty());
				specialtyArray.add(specialtyObject);
			}
			logger.info(specialtyArray.toString());
		}
		dataObject.put("specialtyList", specialtyArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		mv.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return mv;
	}

	/**
	 * 
	 * @Title: getAllClassBySpecialtyId
	 * @Description: TODO
	 * @Auther: yunshang_734@163.com
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ModelAndView
	 */
	public ModelAndView getAllClassBySpecialtyId(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());
		long specialtyId = ServletRequestUtils.getLongParameter(request,
				"specialtyId", -1L);
		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		JSONObject dataObject = new JSONObject();
		JSONArray specialtyClassArray = new JSONArray();
		List<com.ruoogle.teach.meta.Class> classList = classService
				.getClassListBySpecialty(specialtyId);
		if (!ListUtils.isEmptyList(classList)) {
			for (com.ruoogle.teach.meta.Class specialtyClass : classList) {
				JSONObject specialtyClassObject = new JSONObject();
				specialtyClassObject.put(
						"className",
						specialtyClass.getShortSpecialty()
								+ specialtyClass.getName());
				specialtyClassObject.put("specialtyClass",
						specialtyClass.getSpecialty());
				specialtyClassObject.put("shortSpecialty",
						specialtyClass.getShortSpecialty());
				specialtyClassObject.put("classId", specialtyClass.getId());
				specialtyClassArray.add(specialtyClassObject);
			}
			logger.info(specialtyClassArray.toString());
		}
		dataObject.put("specialtyClassList", specialtyClassArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		mv.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return mv;
	}

	/**
	 * 
	 * @Title: getPropertyList
	 * @Description: TODO
	 * @Auther: yunshang_734@163.com
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView getPropertyList(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());
		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		JSONObject dataObject = new JSONObject();
		JSONArray propertyArray = new JSONArray();
		List<CourseProperty> propertyList = courseService
				.getAllCourseProperties();
		if (!ListUtils.isEmptyList(propertyList)) {
			for (CourseProperty property : propertyList) {
				JSONObject propertyObject = new JSONObject();
				propertyObject.put(CourseProperty.KCourseProperty_id,
						property.getId());
				propertyObject.put(CourseProperty.KCourseProperty_name,
						property.getName());
				propertyArray.add(propertyObject);
			}
			logger.info(propertyArray.toString());
		}
		dataObject.put("propertyList", propertyArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		mv.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return mv;
	}

	/**
	 * 
	 * @Title: showEachStudentScoreView
	 * @Description: TODO
	 * @Auther: yunshang_734@163.com
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView showEachStudentScoreView(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());
		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		JSONObject dataObject = new JSONObject();
		JSONArray coursePercentTypeGroupStudentArray = new JSONArray();
		long fromStudentId = MyUser.getMyUserFromToken(request);
		long courseId = ServletRequestUtils.getLongParameter(request,
				"courseId", -1L);
		if (courseId < 0) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			mv.addObject("returnObject", returnObject.toString());
			logger.info(returnObject.toString());
			return mv;

		}
		List<CoursePercentTypeGroupStudentVO> coursePercentTypeGroupStudentVO = courseService
				.getCoursePercentTypeGroupStudentScoresFromStudentID(
						fromStudentId, courseId);

		if (!ListUtils.isEmptyList(coursePercentTypeGroupStudentVO)) {
			for (CoursePercentTypeGroupStudentVO coursePercentTypeGroupStudent : coursePercentTypeGroupStudentVO) {
				JSONObject coursePercentTypeGroupStudentObject = new JSONObject();
				coursePercentTypeGroupStudentObject.put("userId",
						coursePercentTypeGroupStudent.getUserId());
				coursePercentTypeGroupStudentObject.put("name",
						coursePercentTypeGroupStudent.getName());
				BigDecimal b = new BigDecimal(
						coursePercentTypeGroupStudent.getScore());
				double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP)
						.doubleValue();
				coursePercentTypeGroupStudentObject.put("score", f1);
				coursePercentTypeGroupStudentObject.put("groupId",
						coursePercentTypeGroupStudent.getGroupId());
				coursePercentTypeGroupStudentArray
						.add(coursePercentTypeGroupStudentObject);
			}
			logger.info(coursePercentTypeGroupStudentArray.toString());
		}
		dataObject.put("coursePercentTypeGroupStudentVOList",
				coursePercentTypeGroupStudentArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				dataObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		mv.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return mv;
	}

	/**
	 * 
	 * @Title: addGroupScore
	 * @Description: TODO
	 * @Auther: yunshang_734@163.com
	 * @2013-7-18下午11:22:15
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView addGroupScore(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());

		long toUserId = ServletRequestUtils.getLongParameter(request,
				"toUserId", -1L);
		long groupId = ServletRequestUtils.getLongParameter(request, "groupId",
				-1L);
		long courseId = ServletRequestUtils.getLongParameter(request,
				"courseId", -1L);
		long score = ServletRequestUtils.getLongParameter(request, "score", 1L);
		long fromUserId = MyUser.getMyUserFromToken(request);

		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();

		if (toUserId < 0 || courseId < 0 || score < 0 || groupId < 0
				|| fromUserId < 0) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			modelAndView.addObject("returnObject", returnObject.toString());
			return modelAndView;
		}
		List<CoursePercentTypeGroupStudent> coursePercentTypeGroupStudentList = courseService
				.getCoursePercentTypeGroupStudentListByGroupId(courseId,
						groupId);
		// int both = 2, n = 0;
		// for (CoursePercentTypeGroupStudent coursePercentTypeGroupStudent :
		// coursePercentTypeGroupStudentList) {
		// if (coursePercentTypeGroupStudent.getStudentId() == toUserId
		// || coursePercentTypeGroupStudent.getStudentId() == fromUserId) {
		// n++;
		// }
		// }
		// if (n != both) {
		// returnObject.put(BasicObjectConstant.kReturnObject_Code,
		// ReturnCodeConstant.FAILED);
		// modelAndView.addObject("returnObject", returnObject.toString());
		// return modelAndView;
		// }

		boolean succ = courseService.addGroupScore(toUserId, courseId, groupId,
				score, fromUserId);

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
	 * 
	 * @Title: showCourseView
	 * @Description: 课程信息页面
	 * @Auther: yunshang_734@163.com
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView showCourseView(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		long userId = MyUser.getMyUserFromToken(request);
		long courseId = ServletRequestUtils.getLongParameter(request,
				"courseId", -1L);
		if (courseId < 0) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			mv.addObject("returnObject", returnObject.toString());
			logger.info(returnObject.toString());
			return mv;
		}
		JSONObject totalObject = new JSONObject();
		Course course = courseService.getCourseById(courseId);

		JSONObject courseObject = new JSONObject();
		courseObject.put("id", course.getId());
		courseObject.put("name", course.getName());
		courseObject.put("description", course.getDescription());
		totalObject.put("course", courseObject);

		int isEachStudent = 0;
		List<CoursePercentTypeGroup> coursePercentTypeGroupList = courseService
				.getCoursePercentTypeGroupByCourseId(course.getId());
		if (!ListUtils.isEmptyList(coursePercentTypeGroupList)) {
			if (!(courseService.checkStudentFinishedScoreGroup(userId,
					course.getId()))) {
				isEachStudent = 1;
			}
		}

		totalObject.put("isEachStudent", isEachStudent);

		JSONArray percentTypeArray = new JSONArray();
		List<CourseScorePercent> courseScorePercents = courseService
				.getCourseScorePercentListByCourseId(courseId);
		List<CourseStudentScore> courseStudentScores = courseService
				.getCourseStudentScoresByUserIdCourseId(courseId, userId);
		for (CourseScorePercent courseScorePercent : courseScorePercents) {
			boolean hasScore = false;
			JSONObject percentTypeObject = new JSONObject();
			percentTypeObject.put("id", courseScorePercent.getId());
			percentTypeObject.put("name", courseScorePercent.getName());
			for (CourseStudentScore courseStudentScore : courseStudentScores) {
				if (courseScorePercent.getPercentType() == courseStudentScore
						.getPercentType()) {
					BigDecimal b = new BigDecimal(courseStudentScore.getScore());
					double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue();
					percentTypeObject.put("score", f1);
					hasScore = true;
					break;
				}
			}
			if (!hasScore) {
				percentTypeObject.put("score", -1);
			}
			percentTypeArray.add(percentTypeObject);
		}
		CourseStudentTotalScore courseStudentTotalScore = courseService
				.showCourseStudentTotalScore(userId, courseId);
		if (courseStudentTotalScore == null) {
			totalObject.put("totalScore", -1);
		} else {
			JSONObject percentTypeObject = new JSONObject();
			percentTypeObject.put("id", 0);
			percentTypeObject.put("name", "总分");
			BigDecimal b = new BigDecimal(courseStudentTotalScore.getScore());
			double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			percentTypeObject.put("score", f1);
			percentTypeArray.add(percentTypeObject);
			totalObject.put("totalScore", courseStudentTotalScore.getScore());
		}
		totalObject.put("percentTypeArray", percentTypeArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				totalObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		mv.addObject("returnObject", returnObject.toString());
		return mv;
	}

	/**
	 * 获取单个反馈的详细信息列表
	 * 
	 * @Title: getFeedBack
	 * @Description: TODO
	 * @Auther: yunshang_734@163.com
	 * @2013-7-18下午08:46:16
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView getFeedBack(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		JSONArray returnArray = new JSONArray();
		long id = ServletRequestUtils.getLongParameter(request, "id", -1L);
		JSONObject object = new JSONObject();
		JSONObject feedbackObject = new JSONObject();
		FeedBack feedBack = feedBackService.getFeedBack(id);
		if (feedBack == null) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			mv.addObject("returnObject", returnObject.toString());
			logger.info(returnObject.toString());
			return mv;
		}
		feedbackObject.put(FeedBack.KFeedBack_id, feedBack.getId());
		feedbackObject.put(FeedBack.KFeedBack_fromUserId,
				feedBack.getFromUserId());
		feedbackObject.put(FeedBack.KFeedBack_toUserId, feedBack.getToUserId());
		feedbackObject.put(FeedBack.KFeedBack_content, feedBack.getContent());
		if (feedBack.getCourse() != null) {
			feedbackObject.put("courseName", feedBack.getCourse().getName());
		}
		feedbackObject.put(FeedBack.KFeedBack_status, feedBack.getStatus());
		feedbackObject.put(FeedBack.KFeedBack_feedbackId,
				feedBack.getFeedbackId());
		feedbackObject.put(FeedBack.KFeedBack_fromName, feedBack.getFromName());
		feedbackObject.put(FeedBack.KFeedBack_toName, feedBack.getToName());
		feedbackObject.put(FeedBack.KFeedBack_createTime,
				feedBack.getCreateTime());
		returnArray.add(feedbackObject);
		List<FeedBack> feedBacks = feedBackService
				.getFeedBackListByFeedBackId(feedBack.getId());
		if (!ListUtils.isEmptyList(feedBacks)) {
			for (int i = feedBacks.size() - 1; i >= 0; i--) {
				FeedBack feedBack2 = feedBacks.get(i);
				JSONObject feedbackObject2 = new JSONObject();
				feedbackObject2.put(FeedBack.KFeedBack_id, feedBack2.getId());
				feedbackObject2.put(FeedBack.KFeedBack_fromUserId,
						feedBack2.getFromUserId());
				feedbackObject2.put(FeedBack.KFeedBack_toUserId,
						feedBack2.getToUserId());
				feedbackObject2.put(FeedBack.KFeedBack_content,
						feedBack2.getContent());
				if (feedBack2.getCourse() != null) {
					feedbackObject2.put("courseName", feedBack2.getCourse()
							.getName());
				}
				feedbackObject2.put(FeedBack.KFeedBack_status,
						feedBack2.getStatus());
				feedbackObject2.put(FeedBack.KFeedBack_feedbackId,
						feedBack2.getFeedbackId());
				feedbackObject2.put(FeedBack.KFeedBack_fromName,
						feedBack2.getFromName());
				feedbackObject2.put(FeedBack.KFeedBack_toName,
						feedBack2.getToName());
				feedbackObject2.put(FeedBack.KFeedBack_createTime,
						feedBack2.getCreateTime());
				returnArray.add(feedbackObject2);
			}
		}
		object.put("feedbackList", returnArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				object.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		mv.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return mv;
	}

	/**
	 * 活动
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public ModelAndView addForward(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();

		long userId = MyUser.getMyUserFromToken(request);
		String content = ServletRequestUtils.getStringParameter(request,
				"content", "");
		content = new String(content.getBytes("iso-8859-1"), "utf-8");
		long forwardId = ServletRequestUtils.getLongParameter(request,
				"forwardId", -1L);

		if (StringUtils.isEmpty(content)) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			mv.addObject("returnObject", returnObject.toString());
			logger.info(returnObject.toString());
			return mv;
		}
		boolean succ = interactiveService
				.addForward(forwardId, content, userId);
		if (succ) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.SUCCESS);
		} else {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
		}
		returnObject.put(BasicObjectConstant.kReturnObject_Data, "");
		mv.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return mv;
	}

	/**
	 * @throws UnsupportedEncodingException
	 * 
	 * @Title: addInteractive
	 * @Description: 添加评论
	 * @Auther: yunshang_734@163.com
	 * @2013-7-18下午11:27:07
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView addInteractive(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();

		long userId = MyUser.getMyUserFromToken(request);
		String content = ServletRequestUtils.getStringParameter(request,
				"content", "");
		content = new String(content.getBytes("iso-8859-1"), "utf-8");
		long courseId = ServletRequestUtils.getLongParameter(request,
				"courseId", -1L);
		int status = ServletRequestUtils.getIntParameter(request, "status", 0);
		String photoUrl = ServletRequestUtils.getStringParameter(request,
				"photoUrl", "");

		if (StringUtils.isEmpty(content) || StringUtils.isEmpty(photoUrl)
				|| userId < 0 || status < 0) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			mv.addObject("returnObject", returnObject.toString());
			logger.info(returnObject.toString());
			return mv;
		}
		boolean succ = interactiveService.addInteractive(userId, content,
				courseId, status, photoUrl, 0);
		;
		if (succ) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.SUCCESS);
		} else {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
		}
		returnObject.put(BasicObjectConstant.kReturnObject_Data, "");
		mv.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return mv;
	}

	/**
	 * @throws UnsupportedEncodingException
	 * 
	 * @Title: addInteractiveBack
	 * @Description: 回复评论
	 * @Auther: yunshang_734@163.com
	 * @2013-7-18下午11:28:53
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView addInteractiveBack(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();

		long userId = MyUser.getMyUserFromToken(request);
		String content = ServletRequestUtils.getStringParameter(request,
				"content", "");
		content = new String(content.getBytes("iso-8859-1"), "utf-8");
		long id = ServletRequestUtils.getLongParameter(request, "id", -1L);

		if (StringUtils.isEmpty(content) || userId < 0 || id < 0) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			mv.addObject("returnObject", returnObject.toString());
			logger.info(returnObject.toString());
			return mv;
		}
		boolean succ = interactiveService.addForwardBack(id, content, userId);
		if (succ) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.SUCCESS);
		} else {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
		}
		returnObject.put(BasicObjectConstant.kReturnObject_Data, "");
		mv.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return mv;
	}

	/**
	 * 
	 * @Title: showInteractive
	 * @Description: TODO
	 * @Auther: yunshang_734@163.com
	 * @2013-7-20上午11:59:59
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView showInteractive(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		JSONArray returnArray = new JSONArray();

		long userId = MyUser.getMyUserFromToken(request);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 10);
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);

		List<Interactive> interactiveList = interactiveService
				.getInteractiveByUserId(userId, limit, offset);
		if (ListUtils.isEmptyList(interactiveList)) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.SUCCESS);
			mv.addObject("returnObject", returnObject.toString());
			logger.info(returnObject.toString());
			return mv;
		}
		for (Interactive interactive : interactiveList) {
			JSONObject interactiveObject = new JSONObject();
			interactiveObject.put(Interactive.KINTERACTIVE_ID,
					interactive.getId());
			interactiveObject.put(Interactive.KINTERACTIVE_USERID,
					interactive.getUserId());
			interactiveObject.put(interactive.KINGTERACTIVE_NAME,
					interactive.getName());
			interactiveObject.put(Interactive.KINTERACTIVE_CONTENT,
					interactive.getContent());
			interactiveObject.put(Interactive.KINTERACTIVE_COURSEID,
					interactive.getCourseId());
			interactiveObject.put(Interactive.KINGTERACTIVE_COURSENAME,
					interactive.getCourseName());
			interactiveObject.put(Interactive.KINTERACTIVE_PHOTOURL,
					interactive.getPhotoUrl());
			interactiveObject.put(Interactive.KINGTERACTIVE_CREATETIME,
					interactive.getCreateTime());
			interactiveObject.put(Interactive.KINGTERACTIVE_STATUS,
					interactive.getStatus());
			returnArray.add(interactiveObject);
		}
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				returnArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		mv.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return mv;
	}

	/**
	 * 用户课程列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showUserCourse(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		JSONArray returnArray = new JSONArray();

		long userId = MyUser.getMyUserFromToken(request);
		List<Course> courseList = courseService.getTheCourseListByUserId(
				userId, 0, -1);

		if (ListUtils.isEmptyList(courseList)) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.SUCCESS);
			mv.addObject("returnObject", returnObject.toString());
			logger.info(returnObject.toString());
			return mv;
		}
		for (Course course : courseList) {
			JSONObject courseObject = new JSONObject();
			courseObject.put(Course.KCOURSE_NAME, course.getName());
			courseObject.put(Course.KCOURSE_ID, course.getId());
			returnArray.add(courseObject);
		}
		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				returnArray.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);
		mv.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return mv;
	}

	/**
	 * 单个互动
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showSingleInteractive(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		long id = ServletRequestUtils.getLongParameter(request, "id", -1L);

		Interactive interactive = interactiveService.getInteractive(id);
		if (interactive == null) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			mv.addObject("returnObject", returnObject.toString());
			logger.info(returnObject.toString());
			return mv;
		}
		JSONObject interactiveObject = new JSONObject();
		interactiveObject.put(Interactive.KINTERACTIVE_ID, interactive.getId());
		interactiveObject.put(Interactive.KINTERACTIVE_USERID,
				interactive.getUserId());
		interactiveObject.put(Interactive.KINTERACTIVE_CONTENT,
				interactive.getContent());
		interactiveObject.put(Interactive.KINTERACTIVE_COURSEID,
				interactive.getCourseId());
		interactiveObject.put(Interactive.KINGTERACTIVE_COURSENAME,
				interactive.getCourseName());
		interactiveObject.put(Interactive.KINTERACTIVE_PHOTOURL,
				interactive.getPhotoUrl());
		interactiveObject.put(Interactive.KINGTERACTIVE_CREATETIME,
				interactive.getCreateTime());
		interactiveObject.put(Interactive.KINGTERACTIVE_STATUS,
				interactive.getStatus());
		interactiveObject.put(interactive.KINGTERACTIVE_NAME,
				interactive.getName());
		if (!ListUtils.isEmptyList(interactive.getSubInteractiveBackList())) {

			JSONArray array = new JSONArray();
			for (InteractiveBack interactiveBack : interactive
					.getSubInteractiveBackList()) {
				JSONObject object = new JSONObject();
				object.put("content", interactiveBack.getContent());
				object.put("name", interactiveBack.getName());
				object.put("userId", interactiveBack.getUserId());
				array.add(object);
			}
			interactiveObject.put(Interactive.KINGTERACTIVE_BACK,
					array.toString());
		}

		returnObject.put(BasicObjectConstant.kReturnObject_Data,
				interactiveObject.toString());
		returnObject.put(BasicObjectConstant.kReturnObject_Code,
				ReturnCodeConstant.SUCCESS);

		mv.addObject("returnObject", returnObject.toString());
		logger.info(returnObject.toString());
		return mv;
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView authUpload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RequestContext requestContext = new ServletRequestContext(request);
		ModelAndView mv = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		if (request.getMethod().toLowerCase().equals("post")) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(2000000);
			List items = new ArrayList();
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				logger.error("文件上传发生错误" + e1.getMessage());
			}
			Iterator it = items.iterator();
			while (it.hasNext()) {
				FileItem fileItem = (FileItem) it.next();
				if (fileItem.isFormField()) {
					logger.error("");
				} else {
					if (fileItem.getName() != null && fileItem.getSize() != 0) {
						JSONObject object = new JSONObject();
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy/MM/dd/");
						java.util.Date date = new java.util.Date();
						String str = sdf.format(date);
						Random random = new Random(1000);

						String path = "/home/ubuntu/static/interactive/img/"
								+ str;
						String url = HOST + "/static/interactive/img/" + str;
						FileUtil.CreateDir(path);
						String name = new Date().getTime() + "_"
								+ random.nextInt() + ".jpg";
						String fileName = path + name;
						url = url + name;
						fileItem.setFieldName(fileName);

						File file = new File(fileName);
						if (file.isFile()) {
							file.delete();
						}

						fileItem.write(file);

						object.put("imageUrl", url);

						returnObject.put(
								BasicObjectConstant.kReturnObject_Data,
								object.toString());
						returnObject.put(
								BasicObjectConstant.kReturnObject_Code,
								ReturnCodeConstant.SUCCESS);
						mv.addObject("returnObject", returnObject.toString());

						return mv;

					} else {
						logger.error("文件没有选择 或 文件内容为空");
					}
				}
			}
		}
		return mv;
	}

}
