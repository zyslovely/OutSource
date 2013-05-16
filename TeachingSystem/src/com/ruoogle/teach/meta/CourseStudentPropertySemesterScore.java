package com.ruoogle.teach.meta;

import java.io.Serializable;

public class CourseStudentPropertySemesterScore implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private int semester;
	private long propertyId;
	private double score;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
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

}