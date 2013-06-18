package com.ruoogle.teach.meta;

import java.io.Serializable;

public class SchoolInfo implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private String title;
	private String content;
	private long CreateTime;
	private int type;
	private int infoType;
	private String imgUrl;

	public static final String KSchoolInfo_title = "title";
	public static final String KSchoolInfo_content = "content";
	public static final String KSchoolInfo_createTime = "createTime";
	public static final String KSchoolInfo_type = "type";
	public static final String KSchoolInfo_infoType = "infoType";
	public static final String KSchoolInfo_imgUrl = "imgUrl";

	public enum SchoolInfoType {
		/**
		 * 学校
		 */
		school {
			@Override
			public int getValue() {
				return 0;
			}
		},
		/**
		 * 学院
		 */
		specialty {
			@Override
			public int getValue() {
				return 1;
			}
		};
		public abstract int getValue();

		public static SchoolInfoType genSchoolInfoType(int t) {
			for (SchoolInfoType type : SchoolInfoType.values()) {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getInfoType() {
		return infoType;
	}

	public void setInfoType(int infoType) {
		this.infoType = infoType;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}