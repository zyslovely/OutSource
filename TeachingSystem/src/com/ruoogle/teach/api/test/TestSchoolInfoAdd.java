package com.ruoogle.teach.api.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
/**
 * 加入校园活动
 * @author zhengeason
 *
 */
public class TestSchoolInfoAdd {
	public static void main(String[] args) {
		String strURL = "http://teach.zys-wings.com/teach/api/schoolInfo/join/?infoId=2&token=XPZnlwnzVXx9VH%2FSxet1i9ivgtOyv%2FrL";
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
