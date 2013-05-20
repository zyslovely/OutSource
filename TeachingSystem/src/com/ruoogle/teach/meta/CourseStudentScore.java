package com.ruoogle.teach.meta;

import java.io.Serializable;

public class CourseStudentScore implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long courseId;
	private long studentId;
	private long percentType;
	private double percent;
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

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getPercentType() {
		return percentType;
	}

	public void setPercentType(long percentType) {
		this.percentType = percentType;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}