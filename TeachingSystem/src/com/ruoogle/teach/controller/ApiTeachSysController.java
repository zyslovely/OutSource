package com.ruoogle.teach.controller;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.eason.web.util.DoubleUtil;
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
import com.ruoogle.teach.meta.CourseVO;
import com.ruoogle.teach.meta.FeedBack;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.SearchProfile;
import com.ruoogle.teach.meta.SearchProperty;
import com.ruoogle.teach.meta.Semester;
import com.ruoogle.teach.meta.SchoolInfo;
import com.ruoogle.teach.meta.Specialty;
import com.ruoogle.teach.meta.Profile.ProfileLevel;
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
	 * 加入校园信息活动
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView joinSchoolInfo(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());

		long infoId = ServletRequestUtils.getLongParameter(request, "infoId",
				-1L);
		long userId = MyUser.getMyUserFromToken(request);
		ModelAndView modelAndView = new ModelAndView("return");
		JSONObject returnObject = new JSONObject();
		if (userId < 0) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			modelAndView.addObject("returnObject", returnObject.toString());
			return modelAndView;
		}
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
			String origin = ServletRequestUtils.getStringParameter(request,
					"origin", "");
			String graduateSch = ServletRequestUtils.getStringParameter(
					request, "graduateSch", "");
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

		if (teachProfile.getLevel() != ProfileLevel.Admin.getValue()) {
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
				feedBackObject
						.put("createTimeStr", feedBack.getCreateTimeStr());
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
	 */
	public ModelAndView addFeedBack(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info(request.getSession().getId());

		long toUserId = ServletRequestUtils.getLongParameter(request,
				"toUserId", -1L);
		long feedbackId = ServletRequestUtils.getLongParameter(request,
				"feedbackId", -1L);
		String content = ServletRequestUtils.getStringParameter(request,
				"content", "");
		long courseId = ServletRequestUtils.getLongParameter(request,
				"courseId", -1L);
		long fromUserId = MyUser.getMyUserFromToken(request);

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
									cSPSSObject.put("score",
											courseStudentPropertySemesterScore
													.getScore());
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
							searchProfileObject.put("totalScore",
									searchProfile.getTotalScore());
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
				coursePercentTypeGroupStudentObject.put("score",
						coursePercentTypeGroupStudent.getScore());
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
		int both = 2, n = 0;
		for (CoursePercentTypeGroupStudent coursePercentTypeGroupStudent : coursePercentTypeGroupStudentList) {
			if (coursePercentTypeGroupStudent.getStudentId() == toUserId
					|| coursePercentTypeGroupStudent.getStudentId() == fromUserId) {
				n++;
			}
		}
		if (n != both) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			modelAndView.addObject("returnObject", returnObject.toString());
			return modelAndView;
		}

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
		long userId = MyUser.getMyUser(request);
		long courseId = ServletRequestUtils.getLongParameter(request,
				"courseId", -1L);
		if (courseId < 0) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			mv.addObject("returnObject", returnObject.toString());
			logger.info(returnObject.toString());
			return mv;
		}
		Course course = courseService.getCourseById(courseId);
		JSONArray courseArray = new JSONArray();
		JSONObject courseObject = new JSONObject();
		courseObject.put("id", course.getId());
		courseObject.put("name", course.getName());
		courseObject.put("description", course.getDescription());
		courseArray.add(courseObject);

		JSONObject isEachStudentObject = new JSONObject();
		boolean isEachStudent = false;
		List<CoursePercentTypeGroup> coursePercentTypeGroupList = courseService
				.getCoursePercentTypeGroupByCourseId(course.getId());
		if (!ListUtils.isEmptyList(coursePercentTypeGroupList)) {
			if (!(courseService.checkStudentFinishedScoreGroup(userId,
					course.getId()))) {
				isEachStudent = true;
			}
		}
		isEachStudentObject.put("isEachStudent", isEachStudent);

		JSONArray percentTypeArray = new JSONArray();
		JSONObject percentTypeObject = new JSONObject();
		List<CourseScorePercent> courseScorePercents = courseService
				.getCourseScorePercentListByCourseId(courseId);
		List<CourseStudentScore> courseStudentScores = courseService
				.getCourseStudentScoresByUserIdCourseId(courseId, userId);
		for (CourseScorePercent courseScorePercent : courseScorePercents) {
			for (CourseStudentScore courseStudentScore : courseStudentScores) {
				if (courseScorePercent.getPercentType() == courseStudentScore
						.getPercentType()) {
					percentTypeObject.put("id", courseScorePercent.getId());
					percentTypeObject.put("name", courseScorePercent.getName());
					percentTypeObject.put("score",
							courseStudentScore.getScore());
					percentTypeArray.add(percentTypeObject);
				}
			}
		}

		JSONArray returnArray = new JSONArray();

		returnArray.add(courseArray.toString());
		returnArray.add(isEachStudentObject);
		returnArray.add(percentTypeArray.toString());

		returnObject.put(BasicObjectConstant.kReturnObject_Data, "");
		returnObject.put("returnCourseView", returnArray.toString());
		mv.addObject("returnObject", returnObject.toString());
		return mv;
	}

	/**
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
		JSONObject feedbackObject = new JSONObject();
		FeedBack feedBack = feedBackService.getFeedBack(id);
		if (feedBack == null) {
			returnObject.put(BasicObjectConstant.kReturnObject_Code,
					ReturnCodeConstant.FAILED);
			mv.addObject("returnObject", returnObject.toString());
			logger.info(returnObject.toString());
			return mv;
		}
		feedbackObject.put("id", feedBack.getId());
		feedbackObject.put("fromUserId", feedBack.getFromUserId());
		feedbackObject.put("toUserId", feedBack.getToUserId());
		feedbackObject.put("content", feedBack.getContent());
		feedbackObject.put("createTime", feedBack.getCreateTime());
		feedbackObject.put("courseId", feedBack.getCourseId());
		feedbackObject.put("status", feedBack.getStatus());
		feedbackObject.put("feedbackId", feedBack.getFeedbackId());
		feedbackObject.put("fromName", feedBack.getFromName());
		feedbackObject.put("toName", feedBack.getToName());
		feedbackObject.put("createTimeStr", feedBack.getCreateTime());
		returnArray.add(feedbackObject);
		List<FeedBack> feedBacks = feedBackService
				.getFeedBackListByFeedBackId(feedBack.getId());
		if (!ListUtils.isEmptyList(feedBacks)) {
			for (FeedBack feedback : feedBacks) {
				JSONObject feedbackObject2 = new JSONObject();
				feedbackObject2.put("id", feedback.getId());
				feedbackObject2.put("fromUserId", feedback.getFromUserId());
				feedbackObject2.put("toUserId", feedback.getToUserId());
				feedbackObject2.put("content", feedback.getContent());
				feedbackObject2.put("createTime", feedback.getCreateTime());
				feedbackObject2.put("courseId", feedback.getCourseId());
				feedbackObject2.put("status", feedback.getStatus());
				feedbackObject2.put("feedbackId", feedback.getFeedbackId());
				feedbackObject2.put("fromName", feedback.getFromName());
				feedbackObject2.put("toName", feedback.getToName());
				feedbackObject2.put("createTimeStr", feedback.getCreateTime());
				returnArray.add(feedbackObject2);
			}
		}
		returnObject.put(BasicObjectConstant.kReturnObject_Data, "");
		returnObject.put("feedbackList", returnArray.toString());
		mv.addObject("feedBackList", returnObject);
		logger.info(returnObject.toString());
		return mv;
	}
}
