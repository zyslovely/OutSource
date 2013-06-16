package com.ruoogle.teach.meta;

import java.io.Serializable;

public class InteractiveBack implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long userId;
	private long interactiveId;
	private String content;
	private long CreateTime;

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

	public long getInteractiveId() {
		return interactiveId;
	}

	public void setInteractiveId(long interactiveId) {
		this.interactiveId = interactiveId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

}