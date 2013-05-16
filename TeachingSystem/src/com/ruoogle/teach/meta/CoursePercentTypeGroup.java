package com.ruoogle.teach.meta;

import java.io.Serializable;

public class CoursePercentTypeGroup implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long courseId;
	private int count;

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}