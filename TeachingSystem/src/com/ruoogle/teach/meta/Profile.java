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
	private int level;
	private long createTime;

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
		 * 管理员
		 */
		CompanyLeader {
			@Override
			public int getValue() {
				return 2;
			}
		},
		Admin {
			@Override
			public int getValue() {
				return 3;
			}
		};
		public abstract int getValue();

		public static ProfileLevel genProfileLevle(int t) {
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

}
