package com.ruoogle.teach.api.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestJoinSchoolInfo {
	public static void main(String[] args) {
		String strURL = "http://teach.zys-wings.com/teach/api/schoolInfo/join/?infoId=1&name=goubida&origin=zhanjiang&phoneNum=693678&graduateSch=shabixuexiao&token=vzMiFI7h8eqP5zfWqAgOQqM%2FyJZcg3HT";
		// Get file to be posted
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
