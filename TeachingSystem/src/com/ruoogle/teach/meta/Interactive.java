package com.ruoogle.teach.meta;

import java.io.Serializable;
import java.util.List;

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
	private long oriid;
	private long showUserId;
	private String name;
	private String forwardFromStr;
	private String courseName;
	private List<InteractiveBack> subInteractiveBackList;

	public static final String KINTERACTIVE_ID = "id";
	public static final String KINTERACTIVE_USERID = "userId";
	public static final String KINTERACTIVE_CONTENT = "content";
	public static final String KINTERACTIVE_COURSEID = "courseId";
	public static final String KINTERACTIVE_FORWARDID = "forwardId";
	public static final String KINTERACTIVE_PHOTOURL = "photoUrl";
	public static final String KINGTERACTIVE_CREATETIME = "createTime";
	public static final String KINGTERACTIVE_STATUS = "status";
	public static final String KINGTERACTIVE_ORIID = "oriid";
	public static final String KINGTERACTIVE_SHOWUSERID = "showUserId";
	public static final String KINGTERACTIVE_NAME = "name";
	public static final String KINGTERACTIVE_FORWARDFROMSTR = "forwardFromStr";
	public static final String KINGTERACTIVE_COURSENAME = "courseName";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public long getShowUserId() {
		return showUserId;
	}

	public void setShowUserId(long showUserId) {
		this.showUserId = showUserId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getForwardFromStr() {
		return forwardFromStr;
	}

	public void setForwardFromStr(String forwardFromStr) {
		this.forwardFromStr = forwardFromStr;
	}

	public List<InteractiveBack> getSubInteractiveBackList() {
		return subInteractiveBackList;
	}

	public void setSubInteractiveBackList(
			List<InteractiveBack> subInteractiveBackList) {
		this.subInteractiveBackList = subInteractiveBackList;
	}

	public long getOriid() {
		return oriid;
	}

	public void setOriid(long oriid) {
		this.oriid = oriid;
	}

}