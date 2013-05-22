package com.ruoogle.teach.meta;

import java.io.Serializable;

public class Specialty implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private String specialty;
	private String shortSpecialty;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getShortSpecialty() {
		return shortSpecialty;
	}

	public void setShortSpecialty(String shortSpecialty) {
		this.shortSpecialty = shortSpecialty;
	}

}