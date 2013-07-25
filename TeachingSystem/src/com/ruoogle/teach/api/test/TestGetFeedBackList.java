package com.ruoogle.teach.api.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestGetFeedBackList {
	public static void main(String[] args) {

		String strURL = "http://teach.zys-wings.com/teach/api/feedback/?limit=10&offset=0&token=dmklMkJtbHd5b0VIUU43MXpVOEREaW8zbjh3NThVbmYyWQ==";
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
