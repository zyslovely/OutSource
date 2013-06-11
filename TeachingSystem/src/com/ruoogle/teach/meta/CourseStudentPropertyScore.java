package com.ruoogle.teach.meta;

import java.io.Serializable;

public class CourseStudentPropertyScore implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long courseId;
	private long studentId;
	private long propertyId;
	private double score;
	private long semesterId;

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

	public long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(long propertyId) {
		this.propertyId = propertyId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public long getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(long semesterId) {
		this.semesterId = semesterId;
	}

}