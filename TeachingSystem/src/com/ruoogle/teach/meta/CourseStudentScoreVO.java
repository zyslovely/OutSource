package com.ruoogle.teach.meta;

import java.util.List;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-6-11 下午11:34:38
 * @see Class Description
 */
public class CourseStudentScoreVO {

	private List<CourseStudentScore> scoreList;

	private CourseStudentTotalScore totalScore;

	private String name;

	private long userId;

	public List<CourseStudentScore> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<CourseStudentScore> scoreList) {
		this.scoreList = scoreList;
	}

	public CourseStudentTotalScore getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(CourseStudentTotalScore totalScore) {
		this.totalScore = totalScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
