package com.ruoogle.teach.meta;

import java.io.Serializable;

public class Interactive implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long userId;
	private String content;
	private long courseId;
	private long forwardId;
	private String photoUrl;
	private long CreateTime;
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getForwardId() {
		return forwardId;
	}

	public void setForwardId(long forwardId) {
		this.forwardId = forwardId;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}