package com.ruoogle.teach.meta;

import java.io.Serializable;

public class CoursePercentTypeStage implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long courseId;
	private long studentId;
	private double score;
	private int stageIndex;

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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getStageIndex() {
		return stageIndex;
	}

	public void setStageIndex(int stageIndex) {
		this.stageIndex = stageIndex;
	}

}