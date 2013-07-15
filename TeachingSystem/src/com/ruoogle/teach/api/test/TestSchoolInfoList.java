package com.ruoogle.teach.api.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
/**
 * 校园信息列表
 * @author zhengeason
 *
 */
public class TestSchoolInfoList {
	public static void main(String[] args) {
		String strURL = "http://teach.zys-wings.com/teach/api/schoolInfo/list/?type=0&limit=10&offset=0&token=XPZnlwnzVXzyDXBb3%2BTTogIW3iJTiZWd";
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
