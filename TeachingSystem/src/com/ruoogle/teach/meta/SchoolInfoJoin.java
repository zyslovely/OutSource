package com.ruoogle.teach.meta;

import java.io.Serializable;

public class SchoolInfoJoin implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long infoId;
	private long userId;
	private String name;
	private String origin;
	private String graduateSch;
	private long phoneNum;
	private long createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getInfoId() {
		return infoId;
	}

	public void setInfoId(long infoId) {
		this.infoId = infoId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOrigin() {
		return origin;
	}

	public void setGraduateSch(String graduateSch) {
		this.graduateSch = graduateSch;
	}

	public String getGraduateSch() {
		return graduateSch;
	}

	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}

	public long getPhoneNum() {
		return phoneNum;
	}

}