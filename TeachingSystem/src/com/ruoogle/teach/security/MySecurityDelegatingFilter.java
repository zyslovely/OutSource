package com.ruoogle.teach.security;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.ServletRequestUtils;

import com.eason.web.util.CookieUtil;
import com.ruoogle.teach.mapper.ProfileMapper;
import com.ruoogle.teach.meta.Profile;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-8 下午11:48:44
 * @see Class Description
 */
public class MySecurityDelegatingFilter extends HttpServlet implements Filter {

	private static final String[] noAuthURIConfig = { "/**/webTeachPub.do" };

	private static final String[] noAdminURIConfig = { "/**/webTeach.do", "/**/webExcel.do", "/**/webUpload.do", "/**/webAdminTeach.do", "/**/*.dwr" };

	private static final String[] noAuthApiURIConfig = { "/**/apiTeachPub.do" };
	private static final String[] noAdminApiURIConfig = { "/**/apiTeach.do", };

	private static final PathMatcher urlMatcher = new AntPathMatcher();

	private static final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-teachcore-dao.xml");

	public static ConcurrentHashMap<Long, MyUser> userMap = new ConcurrentHashMap<Long, MyUser>();

	// @Resource
	// private ProfileMapper profileMapper;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(MySecurityDelegatingFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info("in MySecurityDelegatingFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
		if (this.noNeedAuthConfig(uri, httpRequest) && this.noNeedAdminConfig(uri, httpRequest)) {
			throw new ServletException();
		}
		try {
			if (this.isRequestFromWeb(uri, httpRequest)) {

				this.dealRequestFromWeb(request, response, arg2);

			} else {

				this.dealRequestFromApi(httpRequest, httpResponse, arg2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	private void dealRequestFromApi(ServletRequest request, ServletResponse response, FilterChain arg2) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
		if (this.noNeedAuthConfig(uri, httpRequest)) {
			String actionName = ServletRequestUtils.getStringParameter(httpRequest, "action", "null");
			if (actionName != null && actionName.equals("doLogin")) {
				String userName = ServletRequestUtils.getStringParameter(httpRequest, "username", null);
				String passWord = ServletRequestUtils.getStringParameter(httpRequest, "password", null);
				ProfileMapper profileMapper = (ProfileMapper) ctx.getBean("profileMapper");
				Profile profile = profileMapper.getProfileByUserName(userName);
				if (profile != null && profile.getPassword().equals(passWord)) {
					MyUser myUser = new MyUser();
					myUser.setUserId(profile.getUserId());
					myUser.setApiToken(MyUser.genToken(profile.getUserId()));
					myUser.setLevel(profile.getLevel());
					userMap.put(myUser.getUserId(), myUser);
					arg2.doFilter(request, response);
					return;
				} else {
					logger.error("账号密码失败");
					httpResponse.setStatus(412);
					return;
				}
			}
		}
		// 如果需要认证
		if (this.noNeedAdminConfig(uri, httpRequest) && !this.noNeedAuthConfig(uri, httpRequest)) {
			String token = ServletRequestUtils.getStringParameter(httpRequest, "token", null);
			Long userId = ServletRequestUtils.getLongParameter(httpRequest, "userid", -1L);
			if (!MyUser.checkToken(token, userId)) {
				logger.error("找不到用户，说明用户不没登陆，返回到最初页面");
				httpResponse.sendRedirect("/");
				return;
			}
		}
		arg2.doFilter(request, response);
	}

	/**
	 * 处理来自web的请求
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @param arg2
	 * @throws Exception
	 */
	private void dealRequestFromWeb(ServletRequest request, ServletResponse response, FilterChain arg2) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
		if (this.noNeedAuthConfig(uri, httpRequest)) {
			String actionName = ServletRequestUtils.getStringParameter(httpRequest, "action", "null");
			if (actionName != null && actionName.equals("doLogin")) {
				String userName = ServletRequestUtils.getStringParameter(httpRequest, "username", null);
				userName = new String(userName.getBytes("iso-8859-1"), "UTF-8");
				String passWord = ServletRequestUtils.getStringParameter(httpRequest, "password", null);
				passWord = new String(passWord.getBytes("iso-8859-1"), "UTF-8");
				int rememberMe = ServletRequestUtils.getIntParameter(httpRequest, "remember", 0);
				ProfileMapper profileMapper = (ProfileMapper) ctx.getBean("profileMapper");
				Profile profile = profileMapper.getProfileByUserName(userName);
				if (profile != null && profile.getPassword().equals(passWord)) {
					MyUser myUser = new MyUser();
					myUser.setUserId(profile.getUserId());
					myUser.setSessionStr(httpRequest.getSession().getId());
					myUser.setLevel(profile.getLevel());
					userMap.put(myUser.getUserId(), myUser);

					if (rememberMe == 1) {
						CookieUtil.setCookie(httpResponse, CookieUtil.PARA_LOGIN_COOKIE, httpRequest.getSession().getId(), 1000 * 60 * 60 * 24);
					}
					httpRequest.getSession().setAttribute("login", true);
					httpRequest.getSession().setAttribute("userId", myUser.getUserId());
					arg2.doFilter(request, response);
					return;
				} else {
					logger.error("账号密码失败");
					httpResponse.sendRedirect("/?error=1");
					return;
				}
			}
		}

		// 如果需要认证
		if (this.noNeedAdminConfig(uri, httpRequest) && !this.noNeedAuthConfig(uri, httpRequest)) {
			Long userId = MyUser.getMyUser(httpRequest);
			MyUser myUser = userMap.get(userId);
			Cookie cookie = CookieUtil.getCookie(httpRequest, CookieUtil.PARA_LOGIN_COOKIE);
			if (myUser == null && cookie == null) {
				logger.error("找不到用户，说明用户不没登陆，返回到最初页面");
				httpResponse.sendRedirect("/");
				return;
			}
		}
		arg2.doFilter(request, response);
	}

	private boolean noNeedAuthConfig(String uri, HttpServletRequest request) {
		for (String ptn : noAuthURIConfig) {
			if (urlMatcher.match(ptn, uri)) {
				return true;
			}
		}
		for (String ptn : noAuthApiURIConfig) {
			if (urlMatcher.match(ptn, uri)) {
				return true;
			}
		}
		return false;
	}

	private boolean noNeedAdminConfig(String uri, HttpServletRequest request) {

		for (String ptn : noAdminURIConfig) {
			if (urlMatcher.match(ptn, uri)) {
				return true;
			}
		}
		for (String ptn : noAdminApiURIConfig) {
			if (urlMatcher.match(ptn, uri)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否来自web请求
	 * 
	 * @auther zyslovely@gmail.com
	 * @param uri
	 * @param request
	 * @return
	 */
	private boolean isRequestFromWeb(String uri, HttpServletRequest request) {
		for (String ptn : noAuthURIConfig) {
			if (urlMatcher.match(ptn, uri)) {
				return true;
			}
		}
		for (String ptn : noAdminURIConfig) {
			if (urlMatcher.match(ptn, uri)) {
				return true;
			}
		}
		return false;
	}

}
