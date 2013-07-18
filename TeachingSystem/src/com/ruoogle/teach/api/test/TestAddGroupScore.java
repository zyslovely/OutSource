package com.ruoogle.teach.api.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestAddGroupScore {
	public static void main(String[] args) {
		String strURL = "http://teach.zys-wings.com/teach/api/group/score/?toUserId=264&groupId=10002&courseId=10018&score=80&token=aERxZzhLVmtKbHdlaGYxUXJ0dE01cWlRNzROWHNJeCUyRg==";
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
