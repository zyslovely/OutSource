package com.ruoogle.teach.meta;

import java.io.Serializable;

public class Teach implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private String name;
	private long demoId;

	private String demoName;

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

	public long getDemoId() {
		return demoId;
	}

	public void setDemoId(long demoId) {
		this.demoId = demoId;
	}

	public String getDemoName() {
		return demoName;
	}

	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}

}