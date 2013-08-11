package com.ruoogle.teach.api.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

public class TestAuthUpload {
	public static void main(String[] args) {
		try {
			String strURL = "http://teach.zys-wings.com/teach/api/authUpload/?token=dmklMkJtbHd5b0VIVG83ZzlCV3V3N0RFcXdwV0J0NmhOZA==&type=1";

			File f = new File("/Users/zhengeason/Desktop/1.jpg");
			PostMethod filePost = new PostMethod(strURL);
			Part[] parts = { new FilePart("filename", f) };
			filePost.setRequestEntity(new MultipartRequestEntity(parts,
					filePost.getParams()));
			HttpClient clients = new HttpClient();
			int status = clients.executeMethod(filePost);

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					filePost.getResponseBodyAsStream(), "UTF-8"));
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			System.out.println("接受到的流是：" + stringBuffer + "—-" + status);
		} catch (Exception e) {
			throw new RuntimeException("error", e);
		}

	}
}
