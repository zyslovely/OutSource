package com.ruoogle.teach.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.eason.web.util.ExcelTemplate;
import com.eason.web.util.ListUtils;
import com.ruoogle.teach.mapper.ProfileMapper;
import com.ruoogle.teach.meta.Course;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.CoursePercentTypeDemo.CoursePercentType;
import com.ruoogle.teach.meta.Profile.ProfileLevel;
import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.ClassService;
import com.ruoogle.teach.service.CourseService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-22 下午08:45:02
 * @see Class Description
 */
@Controller("webExcelController")
public class WebExcelController extends AbstractBaseController {
	private static final Logger logger = Logger.getLogger(WebExcelController.class);
	@Resource
	private ClassService classService;
	@Resource
	private ProfileMapper profileMapper;
	@Resource
	private CourseService courseService;

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

		long classId = ServletRequestUtils.getLongParameter(request, "classId", -1L);
		if (classId < 0) {
			logger.error("没有班级id");
			return null;
		}
		ExcelTemplate template = ExcelTemplate.newInstance("excelTemp/excel.xls");
		template.createRow(0);

		template.createCell("姓名");
		template.createCell("用户名");
		template.createCell("密码");
		List<Profile> profileList = classService.getProfilesByClassId(classId, ProfileLevel.Student.getValue());
		if (!ListUtils.isEmptyList(profileList)) {
			for (int i = 0; i < profileList.size(); i++) {
				template.createRow(i + 1);
				Profile profile = profileList.get(i);

				template.createCell(profile.getName());
				template.createCell(profile.getUserName());
				template.createCell(profile.getPassword());
			}
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
	 * 上传学生信息
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView upLoadStudentProfileExcel(HttpServletRequest request, HttpServletResponse response) {

		long classId = ServletRequestUtils.getLongParameter(request, "classId", -1L);
		if (classId < 0) {
			return null;
		}

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
					com.ruoogle.teach.meta.Class class1 = classService.getClassById(classId);
					if (class1 == null) {
						logger.error("WebExcelController upLoadStudentProfileExcel error where class==null where id=" + classId);
						throw new Exception();
					}
					Profile maxProfile = profileMapper.getMaxProfileByNumber(classId);
					long maxNumber = 0;
					if (maxProfile != null) {
						maxNumber = maxProfile.getNumber();
					}
					for (int i = 0; i <= totalRow; i++) {
						HSSFRow row = sheet.getRow(i);
						HSSFCell cell1 = row.getCell(0);
						HSSFCell cell2 = row.getCell(1);
						HSSFCell cell3 = row.getCell(2);
						if (cell1.getRichStringCellValue().getString().trim().isEmpty()
								&& cell2.getRichStringCellValue().getString().trim().isEmpty()
								&& cell3.getRichStringCellValue().getString().trim().isEmpty()) {
							break;
						}
						if (cell1.getRichStringCellValue().getString().trim().isEmpty()) {
							throw new java.lang.Exception();

						}
						if (cell2 == null || cell2.getRichStringCellValue().getString().trim().isEmpty()) {

							classService.addStudentProfile(classId, maxNumber++, cell1.getRichStringCellValue().getString().trim());

						}

					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 下载与课程相关的excel
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView downLoadCourseStudentExcel(HttpServletRequest request, HttpServletResponse response) {

		long classId = ServletRequestUtils.getLongParameter(request, "classId", -1L);
		if (classId < 0) {
			logger.error("没有班级id");
			return null;
		}
		ExcelTemplate template = ExcelTemplate.newInstance("excelTemp/excel.xls");
		template.createRow(0);

		template.createCell("id");
		template.createCell("姓名");
		template.createCell("成绩");
		List<Profile> profileList = classService.getProfilesByClassId(classId, ProfileLevel.Student.getValue());
		if (!ListUtils.isEmptyList(profileList)) {
			for (int i = 0; i < profileList.size(); i++) {
				template.createRow(i + 1);
				Profile profile = profileList.get(i);

				template.createCell(String.valueOf(profile.getUserId()));
				template.createCell(profile.getName());
			}
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
	 * 上传excel
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView upLoadCourseStudentExcel(HttpServletRequest request, HttpServletResponse response) {

		long courseId = ServletRequestUtils.getLongParameter(request, "courseId", -1L);
		int percentType = ServletRequestUtils.getIntParameter(request, "percentType", -1);
		int stage = ServletRequestUtils.getIntParameter(request, "stage", -1);
		if (courseId < 0 || percentType < 0) {
			return null;
		}

		long teacherId = MyUser.getMyUser(request);

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
					Course course = courseService.getCourseById(courseId);
					if (course == null) {
						logger.error("WebExcelController upLoadStudentProfileExcel error where course==null where id=" + courseId);
						throw new Exception();
					}
					for (int i = 0; i <= totalRow; i++) {
						HSSFRow row = sheet.getRow(i);
						HSSFCell cell1 = row.getCell(0);
						HSSFCell cell3 = row.getCell(2);
						if (cell1.getRichStringCellValue().getString().trim().isEmpty()) {
							break;
						}
						long studentId = Long.valueOf(cell1.getRichStringCellValue().getString());
						if (!cell3.getRichStringCellValue().getString().trim().isEmpty()) {
							double score = Double.valueOf(cell3.getRichStringCellValue().getString());
							if (CoursePercentType.AvgGrading.getValue() == percentType) {
								if (stage < 0) {
									logger.error("分期给分的stage为空");
									break;
								}
								courseService.insertCourseStageScore(courseId, stage, score, studentId, teacherId);
							} else {
								courseService.insertCourseScore(courseId, studentId, percentType, score, teacherId);
							}
						}

					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 下载学生实习日志
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView downLoadStudentJournal(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

}
