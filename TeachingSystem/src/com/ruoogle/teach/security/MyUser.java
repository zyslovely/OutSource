package com.ruoogle.teach.security;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.ServletRequestUtils;

import com.eason.web.util.Base64Util;
import com.eason.web.util.CookieUtil;
import com.eason.web.util.DesUtil;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-28 下午09:43:18
 * @see Class Description
 */
public class MyUser {
	private static final Logger logger = Logger.getLogger(MyUser.class);
	private long userId;
	private int level;
	private String sessionStr;
	private String apiToken;

	public static final String TOKENAUTH = "teach_authToken";

	private static final String CODECHAR_STRING = "#####";

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
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

	/**
	 * session取
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @return
	 */
	public static long getMyUser(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("userId");
		if (obj == null) {
			String cookieUser = CookieUtil.getCookieValue(request,
					CookieUtil.USER_COOKIE_STRING, null);
			if (cookieUser == null) {
				return -1;
			}
			return Long.valueOf(cookieUser);
		}
		return Long.valueOf(obj.toString());

	}

	/**
	 * token去取
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @return
	 */
	public static long getMyUserFromToken(HttpServletRequest request) {
		String token = ServletRequestUtils.getStringParameter(request, "token",
				null);
		if (StringUtils.isEmpty(token)) {
			return -1;
		}
		logger.info("getMyUserFromToken token=" + token);
		try {
			token = new String(Base64Util.decode(token));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] tokens = MyUser.getTokens(token);
		if (ArrayUtils.isEmpty(tokens)) {
			return -1;
		}
		logger.info("getMyUserFromToken token[0]=" + tokens[0]);
		return Long.valueOf(tokens[0]);

	}

	/**
	 * 生成token
	 * 
	 * @auther zyslovely@gmail.com
	 * @param userId
	 * @return
	 */
	public static String genToken(long userId) {
		String encodeString = String.valueOf(userId) + CODECHAR_STRING
				+ new Date().getTime();
		DesUtil desUtil = new DesUtil(TOKENAUTH);
		String oriToken = desUtil.getEncString(encodeString);
		return Base64Util.encode(oriToken.getBytes());
	}

	/**
	 * 检查token有效性
	 * 
	 * @auther zyslovely@gmail.com
	 * @param token
	 * @param userId
	 * @return
	 */
	public static boolean checkToken(String token, long userId) {
		String[] tokens = MyUser.getTokens(token);
		long tokenUserId = Long.valueOf(tokens[0]);
		return tokenUserId == userId;
	}

	/**
	 * 得到token中的信息
	 * 
	 * @param token
	 * @return
	 */
	private static String[] getTokens(String token) {
		DesUtil desUtil = new DesUtil(TOKENAUTH);
		String decode = desUtil.getDecString(token);
		return StringUtils.split(decode, CODECHAR_STRING);
	}
}
