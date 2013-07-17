package com.ruoogle.teach.api.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestAddFeedBack {
	public static void main(String[] args) {
		String strURL = "http://teach.zys-wings.com/teach/api/feedback/add/?toUserId=1&feedbackId=1&content=hello&courseId=1&token=vzMiFI7h8eqfJXbNvkIrmItyagxMQloT";
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
