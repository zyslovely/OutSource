package com.ruoogle.teach.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2012-12-7 下午10:02:19 Class Description
 */
public class HttpServletUtil {

	private static Logger logger = Logger.getLogger(HttpServletUtil.class);
	/**
	 * xml的最大长度
	 */
	public static final int MAX_LENGTH_XML = 1024;

	/**
	 * 解析请求
	 * 
	 * @param request
	 * @param encoding
	 * @return
	 */
	public static String parseRequestAsString(HttpServletRequest request,
			String encoding) {
		InputStream in = null;
		BufferedInputStream bufInput = null;
		ByteArrayOutputStream out = null;
		try {
			in = request.getInputStream();
			bufInput = new BufferedInputStream(in);
			out = new ByteArrayOutputStream();
			byte[] b = new byte[MAX_LENGTH_XML];
			int c = 0;
			while ((c = bufInput.read(b)) != -1) {
				out.write(b, 0, c);
			}
			return (encoding == null) ? out.toString() : out.toString(encoding);
		} catch (IOException e) {
			logger.error("HttpServletUtil parseRequestAsString io error!");
			return null;
		} finally {
			IOUtils.closeQuietly(bufInput);
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}

}
