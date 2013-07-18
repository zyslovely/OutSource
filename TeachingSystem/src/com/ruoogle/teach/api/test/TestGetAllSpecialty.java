package com.ruoogle.teach.api.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 
 * @ClassName: TestGetAllSpecialty
 * @Description: TODO
 * @author yunshang_734@163.com
 * @date 2013-7-18 上午12:00:35
 */
public class TestGetAllSpecialty {
	public static void main(String[] args) {
		String strURL = "http://teach.zys-wings.com/teach/api/specialty/list/?token=vzMiFI7h8eqZFW0HriUdwzztTYVkMXM0";
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
