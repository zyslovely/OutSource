package com.ruoogle.teach.api.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestCourseList {
	public static void main(String[] args) {
		String strURL = "http://teach.zys-wings.com/teach/api/course/list/?semesterId=10008&limit=10&offset=0&token=ajRicDBlbmlHR1g1VEFteG1JejVpck5sWGRsaXlIZkc=";
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
