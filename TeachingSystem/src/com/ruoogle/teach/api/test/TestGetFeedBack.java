package com.ruoogle.teach.api.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestGetFeedBack {
	public static void main(String[] args) {
		String strURL = "http://teach.zys-wings.com/teach/api/feedback/back/?id=17&token=dnpNaUZJN2g4ZXJyYVRXd1laeWJuVFcyZ3pIWk9UYXI=";
		// Get file to be posted
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(strURL);
		// post.setRequestHeader(
		// "Authorization",
		// "token=\"U7elprSC7LZEc2ibVuappkxmFwPMkzImN.a0RtmqkwPmt.nVltzn6daMtalk_Wp7uiHM._jH_NNPZAlDqlcNKu5XWm0aokHOCMRfNVgz.jvq5CYW86MsP7qUHHEuWlfkp\"");

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