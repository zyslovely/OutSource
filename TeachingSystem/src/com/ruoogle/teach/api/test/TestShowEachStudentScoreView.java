package com.ruoogle.teach.api.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 
 * @ClassName: TestShowEachStudentScoreView
 * @Description: TODO
 * @author yunshang_734@163.com
 * @date 2013-7-18 下午02:43:15
 */
public class TestShowEachStudentScoreView {
	public static void main(String[] args) {
		String strURL = "http://teach.zys-wings.com/teach/api/eachStudent/score/?courseId=10018&token=aERxZzhLVmtKbHkzTGxlNTYlMkI2elIweEE4NVJQeHZpNA==";
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(strURL);
		try {
			int result = httpclient.executeMethod(post);
			System.out.println("the result of post : " + result);
			System.out.println(" the response of post : "
					+ post.getResponseBodyAsString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error in post");
		} finally {
			post.releaseConnection();
		}
	}
}
