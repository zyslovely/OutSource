package com.ruoogle.teach.meta;

import java.io.Serializable;

import com.ruoogle.teach.meta.CoursePercentTypeDemo.CoursePercentType;

public class CourseScorePercent implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long courseId;
	private long percentType;
	private double percent;
	private long teacherId;
	private int objectCount;
	/**
	 * 不存数据库
	 */
	private String name;


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
		CoursePercentType type = CoursePercentType.genCoursePercentType((int) percentType);
		if (type != null) {
			this.name = type.getName();
		}
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

	public int getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(int objectCount) {
		this.objectCount = objectCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}