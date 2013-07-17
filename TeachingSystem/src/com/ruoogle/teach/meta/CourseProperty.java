package com.ruoogle.teach.meta;

import java.io.Serializable;

public class CourseProperty implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private String name;

	
	public static final String KCourseProperty_name = "property_Name";
	public static final String KCourseProperty_id = "property_Id";
	public static final String KCourseProperty_Score = "property_score";
	
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