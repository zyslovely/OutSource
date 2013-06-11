package com.ruoogle.teach.meta;

import java.io.Serializable;

public class SemesterStudent implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long semesterId;
	private long studentId;
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(long semesterId) {
		this.semesterId = semesterId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}