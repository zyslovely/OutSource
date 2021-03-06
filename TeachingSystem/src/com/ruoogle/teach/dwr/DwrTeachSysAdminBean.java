package com.ruoogle.teach.dwr;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import com.ruoogle.teach.meta.CoursePercentTypeDemo;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.ClassService;
import com.ruoogle.teach.service.CourseService;
import com.ruoogle.teach.service.ProfileService;
import com.ruoogle.teach.service.SchoolInfoService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午12:41:48
 * @see Class Description
 */
@Service("dwrTeachSysAdminBean")
public class DwrTeachSysAdminBean {
	@Resource
	private CourseService courseService;
	@Resource
	private ClassService classService;
	@Resource
	private ProfileService profileService;
	@Resource
	private SchoolInfoService schoolInfoService;

	/**
	 * 添加专业
	 * 
	 * @auther zyslovely@gmail.com
	 * @param SpecialtyName
	 * @param SpecialtyShortName
	 * @param semesterCount
	 * @return
	 */
	public boolean addSpecialty(String SpecialtyName,
			String SpecialtyShortName, int semesterCount) {
		if (StringUtils.isEmpty(SpecialtyName)
				|| StringUtils.isEmpty(SpecialtyShortName)) {
			return false;
		}
		return classService.addSpecialty(SpecialtyName, SpecialtyShortName,
				semesterCount);
	}

	/**
	 * 添加班级
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param year
	 * @return
	 */
	public boolean addClassRoom(String name, int year, long specialtyId) {
		if (StringUtils.isEmpty(name)) {
			return false;
		}
		return classService.addClassRoom(name, year, specialtyId);
	}

	/**
	 * 添加教师
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param level
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public boolean addTeacherPassPort(String name, int level, String userName,
			String passWord) {
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)
				|| StringUtils.isEmpty(name)) {
			return false;
		}
		return profileService.addProfile(name, userName, passWord, level);
	}

	/**
	 * 添加课程类型模型
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param coursePercentTypes
	 * @return
	 */
	public boolean addCoursePercentTypeDemo(String name,
			Integer[] coursePercentTypeId, Double[] percents) {
		if (StringUtils.isEmpty(name)
				|| ArrayUtils.isEmpty(coursePercentTypeId)
				|| ArrayUtils.isEmpty(percents)) {
			return false;
		}
		return classService.addCoursePercentTypeDemo(
				name,
				CoursePercentTypeDemo.getCoursePercentTypeList(
						Arrays.asList(coursePercentTypeId),
						Arrays.asList(percents)));
	}

	/**
	 * 新建学期
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @return
	 */
	public boolean addNewSemester(String name) {
		return classService.addNewSemester(name);
	}

	/**
	 * 删除学期
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public boolean deleteSemester(long id) {
		return classService.deleteSemester(id);
	}

	/**
	 * 删除专业
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public boolean deleteSpecialty(long id) {
		return classService.deleteSpecialty(id);
	}

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param classId
	 * @return
	 */
	public boolean deleteClass(long classId) {
		return classService.deleteClass(classId);
	}

	public boolean deleteUser(long userId) {
		return classService.deleteProfile(userId);
	}

	public boolean deleteCourseType(long demoId) {
		return classService.deleteCourseType(demoId);
	}

	public boolean deleteCourse(long courseId) {
		return courseService.deleteCourseById(courseId);
	}

	/**
	 * 结束所有学业
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public boolean endAllSemester(long userId) {

		return courseService.endStudentSemester(userId);
	}

	public boolean addNewTeach(String name, long demoId) {
		return classService.addTeach(name, demoId);
	}

	public boolean deleteNewTeach(long id) {
		return classService.deleteTeach(id);
	}

	/**
	 * 完成校园信息
	 * 
	 * @param infoId
	 * @return
	 */
	public boolean finishSchoolInfo(long infoId) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		return schoolInfoService.finishSchoolInfo(infoId, userId);
	}

	/**
	 * 添加校园信息
	 * 
	 * @param title
	 * @param content
	 * @param bImgUrl
	 * @param sImgUrl
	 * @param type
	 * @param infoType
	 * @return
	 */
	public boolean addSchoolInfo(String title, String content, String bImgUrl,
			String sImgUrl, int type, int infoType) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		Profile profile = profileService.getProfile(userId);
		if (profile.getLevel() != Profile.ProfileLevel.Admin.getValue()) {
			return false;
		}
		return schoolInfoService.addSchoolInfo(title, content, type, infoType,
				bImgUrl, sImgUrl);
	}

	public boolean deleteSchoolInfo(long infoId) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		Profile profile = profileService.getProfile(userId);
		if (profile.getLevel() != Profile.ProfileLevel.Admin.getValue()) {
			return false;
		}
		return schoolInfoService.deleteSchoolInfo(infoId);
	}
}
