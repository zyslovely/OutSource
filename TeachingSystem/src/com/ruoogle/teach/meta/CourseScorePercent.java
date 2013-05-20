package com.ruoogle.teach.meta;

import java.io.Serializable;

public class CourseScorePercent implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long courseId;
	private long percentType;
	private double percent;
	private long teacherId;

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

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}

}