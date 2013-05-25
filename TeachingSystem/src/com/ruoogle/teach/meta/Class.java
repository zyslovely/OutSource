package com.ruoogle.teach.meta;

import java.io.Serializable;

public class Class implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private String name;
	private int startYear;
	private long specialtyId;
	private int semesterCount;

	public int getSemesterCount() {
		return semesterCount;
	}

	public void setSemesterCount(int semesterCount) {
		this.semesterCount = semesterCount;
	}

	public long getSpecialtyId() {
		return specialtyId;
	}

	public void setSpecialtyId(long specialtyId) {
		this.specialtyId = specialtyId;
	}

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

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

}