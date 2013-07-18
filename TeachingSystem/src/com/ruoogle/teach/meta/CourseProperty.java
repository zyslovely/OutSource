package com.ruoogle.teach.meta;

import java.io.Serializable;

public class CourseProperty implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private String name;

	
	public static final String KCourseProperty_name = "name";
	public static final String KCourseProperty_id = "id";
	public static final String KCourseProperty_Score = "score";
	
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

}