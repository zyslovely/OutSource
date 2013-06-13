package com.ruoogle.teach.meta;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-31 上午12:36:38
 * @see Class Description
 */
public class CourseVO {

	private Course course;

	private Profile user;

	private Class class1;

	private double score = -1;

	private int haveGroupToScore = 0;

	public static final String KCourse_title = "title";
	public static final String KCoursec_className = "className";
	public static final String KCoursec_courseId = "courseId";

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Profile getUser() {
		return user;
	}

	public void setUser(Profile user) {
		this.user = user;
	}

	public Class getClass1() {
		return class1;
	}

	public void setClass1(Class class1) {
		this.class1 = class1;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getHaveGroupToScore() {
		return haveGroupToScore;
	}

	public void setHaveGroupToScore(int haveGroupToScore) {
		this.haveGroupToScore = haveGroupToScore;
	}

}
