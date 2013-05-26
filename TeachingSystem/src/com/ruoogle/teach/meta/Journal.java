package com.ruoogle.teach.meta;

import java.io.Serializable;

public class Journal implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private long userId;
	private String content;
	private long courseId;
	private int type;
	private long CreateTime;

	public enum JournalType {
		/**
		 * 学习日志
		 */
		StudyJournal {
			@Override
			public int getValue() {
				return 0;
			}
		},
		/**
		 * 实习日志
		 */
		InternJournal {
			@Override
			public int getValue() {
				return 1;
			}
		};
		public abstract int getValue();

		public static JournalType genJournalType(int t) {
			for (JournalType type : JournalType.values()) {
				if (type.getValue() == t)
					return type;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

}