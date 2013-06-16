package com.ruoogle.teach.meta;

import java.io.Serializable;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午12:43:50
 * @see Class Description
 */
public class Profile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long userId;
	private String userName;
	private String password;
	private String name;
	private int level;
	private long createTime;
	private long classId;
	private long number;

	private int status;

	private String className;
	private String specialtyName;

	public static final String KProfile_userName = "username";
	public static final String KProfile_Name = "name";
	public static final String KProfile_passWord = "password";
	public static final String KProfile_token = "token";
	public static final String KProfile_level = "level";
	public static final String KProfile_userId = "userId";

	public enum ProfileLevel {
		/**
		 * 学生
		 */
		Student {
			@Override
			public int getValue() {
				return 0;
			}
		},
		/**
		 * 老师
		 */
		Teacher {
			@Override
			public int getValue() {
				return 1;
			}
		},
		/**
		 * 企业老师
		 */
		CompanyLeader {
			@Override
			public int getValue() {
				return 2;
			}
		},
		/**
		 * 管理员
		 */
		Admin {
			@Override
			public int getValue() {
				return 3;
			}
		};
		public abstract int getValue();

		public static ProfileLevel genProfileLevel(int t) {
			for (ProfileLevel level : ProfileLevel.values()) {
				if (level.getValue() == t)
					return level;
			}
			return null;
		}
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSpecialtyName() {
		return specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

}
