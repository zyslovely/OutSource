package com.ruoogle.teach.api.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestShowSearch {
	public static void main(String[] args) {
		String strURL = "http://teach.zys-wings.com/teach/api/search/?specialtyId=1&classId=1&semesterId=10000&properties=10000,0;10001,;10002,;10003,;10004,;&token=XPZnlwnzVXxUufgEpTBj7EOrUoU96ZQX";
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
