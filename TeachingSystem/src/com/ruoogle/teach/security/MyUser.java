package com.ruoogle.teach.security;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-28 下午09:43:18
 * @see Class Description
 */
public class MyUser {

	private long userId;
	private long shopId;
	private int level;
	private String sessionStr;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getSessionStr() {
		return sessionStr;
	}

	public void setSessionStr(String sessionStr) {
		this.sessionStr = sessionStr;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public static long getMyUser(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("userId");
		if (obj == null) {
			return 0;
		}
		return Long.valueOf(obj.toString());

	}
}
