package com.ruoogle.teach.meta;

import java.io.Serializable;

public class CourseStudentTotalScore implements Serializable {
	private static final long serialVersionUID = 62132123L;
	private long id;
	private long courseId;
	private long studentId;
	private double score;
	private long semester;

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

	public long getSemester() {
		return semester;
	}

	public void setSemester(long semester) {
		this.semester = semester;
	}

}