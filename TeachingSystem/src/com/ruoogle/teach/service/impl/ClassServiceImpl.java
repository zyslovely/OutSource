package com.ruoogle.teach.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eason.web.util.ListUtils;
import com.eason.web.util.StringUtil;
import com.ruoogle.teach.mapper.ClassMapper;
import com.ruoogle.teach.mapper.CoursePercentTypeDemoMapper;
import com.ruoogle.teach.mapper.JournalMapper;
import com.ruoogle.teach.mapper.ProfileMapper;
import com.ruoogle.teach.mapper.SemesterMapper;
import com.ruoogle.teach.mapper.SpecialtyMapper;
import com.ruoogle.teach.mapper.TeachMapper;
import com.ruoogle.teach.meta.CoursePercentTypeDemo;
import com.ruoogle.teach.meta.Journal;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.Semester;
import com.ruoogle.teach.meta.Specialty;
import com.ruoogle.teach.meta.Profile.ProfileLevel;
import com.ruoogle.teach.meta.Teach;
import com.ruoogle.teach.service.ClassService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 下午08:37:57
 * @see Class Description
 */
@Service("classService")
public class ClassServiceImpl implements ClassService {

	@Resource
	private ClassMapper classMapper;
	@Resource
	private CoursePercentTypeDemoMapper coursePercentTypeDemoMapper;
	@Resource
	private JournalMapper journalMapper;

	@Resource
	private SpecialtyMapper specialtyMapper;
	@Resource
	private ProfileMapper profileMapper;
	@Resource
	private SemesterMapper semesterMapper;

	@Resource
	private TeachMapper teachMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.ClassService#addSpecialty(java.lang.String,
	 * java.lang.String, int)
	 */
	public boolean addSpecialty(String SpecialtyName,
			String SpecialtyShortName, int semesterCount) {
		Specialty specialty = new Specialty();
		specialty.setSemesterCount(semesterCount);
		specialty.setSpecialty(SpecialtyName);
		specialty.setShortSpecialty(SpecialtyShortName);
		return specialtyMapper.addSpecialty(specialty) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.ClassService#addClassRoom(java.lang.String,
	 * int)
	 */
	public boolean addClassRoom(String name, int year, long specialtyId) {
		Specialty specialty = specialtyMapper.getSpecialtyById(specialtyId);
		if (specialty == null) {
			return false;
		}
		com.ruoogle.teach.meta.Class class1 = new com.ruoogle.teach.meta.Class();
		class1.setName(name);
		class1.setStartYear(year);
		class1.setSemesterCount(specialty.getSemesterCount());
		class1.setSpecialtyId(specialtyId);
		class1.setSpecialty(specialty.getSpecialty());
		class1.setShortSpecialty(specialty.getShortSpecialty());
		return classMapper.addClass(class1) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ClassService#addStudentProfile(long, int)
	 */
	public boolean addStudentProfile(long classId, long number, String name) {
		Profile profile = new Profile();
		profile.setCreateTime(new Date().getTime());
		com.ruoogle.teach.meta.Class class1 = classMapper.getClassById(classId);
		if (class1 == null) {
			return false;
		}
		Specialty specialty = specialtyMapper.getSpecialtyById(class1
				.getSpecialtyId());
		if (specialty == null) {
			return false;
		}

		String userName = specialty.getShortSpecialty()
				+ StringUtil.removeZhongWen(class1.getName()) + number;
		String passWord = StringUtil.removeZhongWen(class1.getName()) + number;
		profile.setUserName(userName);
		profile.setPassword(passWord);
		profile.setNumber(number);
		profile.setName(name);
		profile.setClassId(classId);
		profile.setLevel(ProfileLevel.Student.getValue());
		return profileMapper.addProfile(profile) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ruoogle.teach.service.ClassService#addCoursePercentTypeDemo(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public boolean addCoursePercentTypeDemo(String name, String demoJson) {
		CoursePercentTypeDemo coursePercentTypeDemo = new CoursePercentTypeDemo();
		coursePercentTypeDemo.setName(name);
		coursePercentTypeDemo.setDemoJson(demoJson);
		return coursePercentTypeDemoMapper
				.addCoursePercentTypeDemo(coursePercentTypeDemo) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ClassService#getProfilesByClassId(long)
	 */
	@Override
	public List<Profile> getProfilesByClassId(long classId, int level) {
		return profileMapper.getProfileByClassId(classId, level, 0, -1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ClassService#getClassById(long)
	 */
	@Override
	public com.ruoogle.teach.meta.Class getClassById(long classId) {
		return classMapper.getClassById(classId);
	}

	/*
	 * 
	 */
	@Override
	public boolean addJournal(String content, int type, long courseId,
			long userId) {
		Journal journal = new Journal();
		journal.setContent(content);
		journal.setUserId(userId);
		journal.setCourseId(courseId);
		journal.setType(type);
		journal.setCreateTime(new Date().getTime());
		return journalMapper.addJournal(journal) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ClassService#getAllClass()
	 */
	@Override
	public List<com.ruoogle.teach.meta.Class> getAllClass() {
		return classMapper.getAllClass();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ClassService#getAllSemesters()
	 */
	@Override
	public List<Semester> getAllSemesters() {
		return semesterMapper.getAllSemester();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ClassService#getClassListBySpecialty(long)
	 */
	@Override
	public List<com.ruoogle.teach.meta.Class> getClassListBySpecialty(
			long specialtyId) {

		return classMapper.getClassListBySpecialty(specialtyId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ClassService#getSpecialties()
	 */
	@Override
	public List<Specialty> getSpecialties() {
		return specialtyMapper.getSpecialties();
	}

	@Override
	public boolean updateClassStudentCount(long classId) {
		com.ruoogle.teach.meta.Class class1 = classMapper.getClassById(classId);
		List<Profile> profileList = profileMapper.getProfileByClassId(
				class1.getId(), ProfileLevel.Student.getValue(), 0, -1);
		int count = 0;
		if (!ListUtils.isEmptyList(profileList)) {
			count = profileList.size();
		}

		return classMapper.updateClassStudentCount(classId, count) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ClassService#deleteSpecialty(long)
	 */
	@Override
	public boolean deleteSpecialty(long id) {
		if (specialtyMapper.deleteSpecialty(id) > 0) {
			List<com.ruoogle.teach.meta.Class> classList = classMapper
					.getClassListBySpecialty(id);
			if (ListUtils.isEmptyList(classList)) {
				for (com.ruoogle.teach.meta.Class aClass : classList) {
					profileMapper.deleteProfileByClassId(aClass.getId());
					classMapper.deleteClassBySpecialty(id);
				}
			}
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ClassService#deleteClass(long)
	 */
	public boolean deleteClass(long classId) {
		if (classMapper.deleteClassById(classId) > 0) {
			profileMapper.deleteProfileByClassId(classId);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ClassService#deleteProfile(long)
	 */
	public boolean deleteProfile(long userId) {
		return profileMapper.deleteProfileByUserId(userId) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ClassService#deleteCourseType(long)
	 */
	@Override
	public boolean deleteCourseType(long demoId) {
		return coursePercentTypeDemoMapper.deleteCoursePercentTypeDemo(demoId) > 0;
	}

	@Override
	public List<Semester> getAllSemesterList(int limit, int offset) {
		return semesterMapper.getAllSemester();
	}

	@Override
	public boolean addNewSemester(String name) {
		Semester semester = new Semester();
		semester.setName(name);
		return semesterMapper.addSemester(semester) > 0;
	}

	@Override
	public boolean deleteSemester(long id) {
		return semesterMapper.deleteSelemster(id) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ClassService#addTeach(java.lang.String,
	 * long)
	 */
	@Override
	public boolean addTeach(String name, long demoId) {
		Teach teach = new Teach();
		teach.setName(name);
		teach.setDemoId(demoId);
		return teachMapper.addTeach(teach) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ruoogle.teach.service.ClassService#getTeachList(int, int)
	 */
	@Override
	public List<Teach> getTeachList(int limit, int offset) {
		List<Teach> teachList = teachMapper.getTeachList(limit, offset);

		if (ListUtils.isEmptyList(teachList)) {
			return null;
		}
		List<CoursePercentTypeDemo> coursePercentTypeDemos = coursePercentTypeDemoMapper
				.getCoursePercentTypeDemos(0, -1);
		if (!ListUtils.isEmptyList(coursePercentTypeDemos)) {
			for (Teach teach : teachList) {
				for (CoursePercentTypeDemo coursePercentTypeDemo : coursePercentTypeDemos) {
					if (coursePercentTypeDemo.getId() == teach.getDemoId()) {
						teach.setDemoName(coursePercentTypeDemo.getName());
						break;
					}
				}
			}
		}
		return teachList;
	}

	@Override
	public boolean deleteTeach(long id) {
		return teachMapper.deleteTeach(id) > 0;
	}

	@Override
	public CoursePercentTypeDemo getCoursePercentTypeDemoByTeachId(long teachId) {

		Teach teach = teachMapper.getTeachById(teachId);
		if (teach == null) {
			return null;
		}
		return coursePercentTypeDemoMapper.getCoursePercentTypeDemoById(teach
				.getDemoId());

	}
}
