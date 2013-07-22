package com.ruoogle.teach.controller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestAddInteractiveBack {
	public static void main(String[] args) {
		String strURL = "http://teach.zys-wings.com/teach/api/interactive/?content=hallo&id=24&token=K4jIwMtWxQ%2FOcLVjQF6dCAn2jKI88I2T";
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
