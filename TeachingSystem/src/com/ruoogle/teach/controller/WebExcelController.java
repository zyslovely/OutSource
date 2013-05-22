package com.ruoogle.teach.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.eason.web.util.ExcelTemplate;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-22 下午08:45:02
 * @see Class Description
 */
@Controller("webExcelController")
public class WebExcelController extends AbstractBaseController {
	/**
	 * 下载添加分数excel表格
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView downLoadAddScoreExcel(HttpServletRequest request, HttpServletResponse response) {

		ExcelTemplate template = ExcelTemplate.newInstance("excelTemp/excel.xls");
		for (int i = 0; i < 10; i++) {
			// 创建一行

			template.createRow(i);
			// 创建列
			template.createCell("数学");
			template.setSheetWidth("数学", 1);
			template.createCell("123");
			template.setSheetWidth("123", 2);
			template.createCell("数学数学数学数学数学数学数学数学");
			template.setSheetWidth("数学数学数学数学数学数学数学数学", 3);
			template.createCell("数学数学数学数学数学数学数学数学数学数学数学数学数学数学数学数学");
			template.setSheetWidth("数学数学数学数学数学数学数学数学数学数学数学数学数学数学数学数学", 4);

		}
		response.reset();
		response.setContentType("application/x-download;charset=GBK");
		response.setHeader("Content-Disposition", "attachment;filename=Book_" + System.currentTimeMillis() + ".xls");
		try {
			template.getWorkbook().write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读成绩
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView readScoreFromExcel(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		try {
			Iterator<FileItem> it = this.getUPFiles(request);
			while (it.hasNext()) {
				FileItem item = it.next();
				if (item != null && !item.isFormField() && item.getSize() > 0) {
					InputStream inputStream = item.getInputStream();
					HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
					HSSFSheet sheet = workbook.getSheetAt(0);
					int totalRow = sheet.getLastRowNum();
					for (int i = 0; i <= totalRow; i++) {
						HSSFRow row = sheet.getRow(i);
						HSSFCell cell1 = row.getCell(0);
						HSSFCell cell2 = row.getCell(1);
						HSSFCell cell3 = row.getCell(2);
						HSSFCell cell4 = row.getCell(3);
						logger.info(cell1.getRichStringCellValue().getString());
						logger.info(cell2.getRichStringCellValue().getString());
						logger.info(cell3.getRichStringCellValue().getString());
						logger.info(cell4.getRichStringCellValue().getString());
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 下载学生信息excel
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView downLoadStudentProfileExcel(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	/**
	 * 读取文件
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @return
	 * @throws FileUploadException
	 */
	public Iterator<FileItem> getUPFiles(HttpServletRequest request) throws FileUploadException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		return upload.parseRequest(request).iterator();
	}

}
