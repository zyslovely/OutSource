package com.ruoogle.teach.meta;

import java.io.Serializable;

public class CoursePercentTypeGroupStudentScore implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long courseId;
	private long groupId;
	private long fromStudentId;
	private long toStudentId;
	private double score;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getFromStudentId() {
		return fromStudentId;
	}

	public void setFromStudentId(long fromStudentId) {
		this.fromStudentId = fromStudentId;
	}

	public long getToStudentId() {
		return toStudentId;
	}

	public void setToStudentId(long toStudentId) {
		this.toStudentId = toStudentId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}