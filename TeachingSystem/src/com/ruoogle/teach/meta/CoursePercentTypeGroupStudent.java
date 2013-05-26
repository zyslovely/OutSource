package com.ruoogle.teach.meta;

import java.io.Serializable;

public class CoursePercentTypeGroupStudent implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long courseId;
	private long groupId;
	private long studentId;
	private int level;

	public enum GroupLevel {
		/**
		 * 普通学生
		 */
		Normal {
			@Override
			public int getValue() {
				return 0;
			}
		},
		/**
		 * 学生队长
		 */
		Leader {
			@Override
			public int getValue() {
				return 1;
			}
		};
		public abstract int getValue();

		public static GroupLevel genGroupLevel(int t) {
			for (GroupLevel level : GroupLevel.values()) {
				if (level.getValue() == t)
					return level;
			}
			return null;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}