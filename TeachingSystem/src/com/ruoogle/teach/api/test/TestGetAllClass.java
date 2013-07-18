package com.ruoogle.teach.api.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 
 * @ClassName: TestGetAllClass
 * @Description: TODO
 * @author yunshang_734@163.com
 * @date 2013-7-18 上午01:21:38
 */
public class TestGetAllClass {
	public static void main(String[] args) {
		String strURL = "http://teach.zys-wings.com/teach/api/class/list/?specialtyId=1&token=vzMiFI7h8erVF6%2FYIqD1S87c6M%2B5j9Yq";
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
