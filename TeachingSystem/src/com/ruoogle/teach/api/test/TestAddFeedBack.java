package com.ruoogle.teach.api.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class TestAddFeedBack {
	public static void main(String[] args) throws UnsupportedEncodingException {

		String tagString = URLEncoder.encode("哈哈哈");
		System.err.println(tagString);
		String strURL = "http://teach.zys-wings.com/teach/api/feedback/add/?toUserId=15&feedbackId=86&content="
				+ tagString
				+ "&courseId=10022&token=dmklMkJtbHd5b0VIUTg2aFNtM1Z1UnB0Tlp2ZklmNmhKQg==";

		System.err.println(strURL);
		// Get file to be posted
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(strURL);
		post.addRequestHeader("Content-Type", "text/html;charset=UTF-8");

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
