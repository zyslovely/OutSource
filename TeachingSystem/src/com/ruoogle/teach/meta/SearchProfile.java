package com.ruoogle.teach.meta;

import java.util.List;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-6-16 下午04:04:54
 * @see Class Description
 */
public class SearchProfile {

	private Profile profile;

	private List<CourseStudentPropertySemesterScore> courseStudentPropertySemesterScoreList;

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<CourseStudentPropertySemesterScore> getCourseStudentPropertySemesterScoreList() {
		return courseStudentPropertySemesterScoreList;
	}

	public void setCourseStudentPropertySemesterScoreList(List<CourseStudentPropertySemesterScore> courseStudentPropertySemesterScoreList) {
		this.courseStudentPropertySemesterScoreList = courseStudentPropertySemesterScoreList;
	}

}
