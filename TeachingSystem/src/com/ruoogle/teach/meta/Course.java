package com.ruoogle.teach.meta;

import java.io.Serializable;

public class Course implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private String name;
	private int semester;
	private long classId;
	private long teacherId;
	private int status;

	public static final int VALID = 0;

	public static final int FINISHED = 1;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}