package com.ruoogle.teach.meta;

import java.io.Serializable;

public class Course implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private String name;
	private long semester;
	private long classId;
	private long teacherId;
	private int status;
	private String description;
	private String teacherName;

	public static final String KCOURSE_ID = "id";
	public static final String KCOURSE_NAME = "name";
	public static final String KCOURSE_SEMESTER = "semester";
	public static final String KCOURSE_CLASSID = "classId";
	public static final String KCOURSE_TEACHERID = "teacherId";
	public static final String KCOURSE_STATUS = "status";
	public static final String KCOURSE_DESCRIPTION = "description";
	public static final String KCOURSE_TEACHERNAME = "teacherName";

	public static final int VALID = 0;

	public static final int FINISHED = 1;

	public static final int Deleted = 2;

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

	public long getSemester() {
		return semester;
	}

	public void setSemester(long semester) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

}