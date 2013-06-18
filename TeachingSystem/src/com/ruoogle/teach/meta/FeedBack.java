package com.ruoogle.teach.meta;

import java.io.Serializable;
import java.util.List;

public class FeedBack implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long fromUserId;
	private long toUserId;
	private String content;
	private long createTime;
	private long courseId;
	private int status;
	private long feedbackId;
	private String fromName;
	private String toName;
	
	private String createTimeStr;
	private Course course;

	List<FeedBack> subFeedBackList;
	public static final int Unread = 1;
	public static final int readed = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public long getToUserId() {
		return toUserId;
	}

	public void setToUserId(long toUserId) {
		this.toUserId = toUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<FeedBack> getSubFeedBackList() {
		return subFeedBackList;
	}

	public void setSubFeedBackList(List<FeedBack> subFeedBackList) {
		this.subFeedBackList = subFeedBackList;
	}

}