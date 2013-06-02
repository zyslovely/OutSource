package com.ruoogle.teach.meta;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-31 上午12:36:38
 * @see Class Description
 */
public class CourseVO {

	private Course course;

	private Profile teacher;

	private Class class1;

	public static final String KCourse_title = "title";
	public static final String KCoursec_className = "className";
	public static final String KCoursec_courseId = "courseId";

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Profile getTeacher() {
		return teacher;
	}

	public void setTeacher(Profile teacher) {
		this.teacher = teacher;
	}

	public Class getClass1() {
		return class1;
	}

	public void setClass1(Class class1) {
		this.class1 = class1;
	}

}
