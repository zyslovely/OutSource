package com.ruoogle.teach.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.eason.web.util.ExcelTemplate;
import com.eason.web.util.ListUtils;
import com.eason.web.util.TimeUtil;
import com.ruoogle.teach.mapper.ProfileMapper;
import com.ruoogle.teach.meta.Course;
import com.ruoogle.teach.meta.CoursePercentTypeDemo.CoursePercentType;
import com.ruoogle.teach.meta.CourseScorePercent;
import com.ruoogle.teach.meta.CourseStudent;
import com.ruoogle.teach.meta.CourseStudentScore;
import com.ruoogle.teach.meta.CourseStudentScoreVO;
import com.ruoogle.teach.meta.CourseStudentVO;
import com.ruoogle.teach.meta.Interactive;
import com.ruoogle.teach.meta.Profile;
import com.ruoogle.teach.meta.Profile.ProfileLevel;
import com.ruoogle.teach.security.MySecurityDelegatingFilter;
import com.ruoogle.teach.security.MyUser;
import com.ruoogle.teach.service.ClassService;
import com.ruoogle.teach.service.CourseService;
import com.ruoogle.teach.service.InteractiveService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-22 下午08:45:02
 * @see Class Description
 */
@Controller("webExcelController")
public class WebExcelController extends AbstractBaseController {
	private static final Logger logger = Logger
			.getLogger(WebExcelController.class);
	@Resource
	private ClassService classService;
	@Resource
	private ProfileMapper profileMapper;
	@Resource
	private CourseService courseService;

	@Resource
	private InteractiveService interactiveService;

	/**
	 * 下载添加分数excel表格
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView downLoadAddScoreExcel(HttpServletRequest request,
			HttpServletResponse response) {

		long courseId = ServletRequestUtils.getLongParameter(request,
				"courseId", -1L);
		long percentType = ServletRequestUtils.getLongParameter(request,
				"percentTypeId", -1L);

		if (courseId < 0 || percentType < 0) {
			return null;
		}
		Course course = courseService.getCourseById(courseId);
		if (course == null) {
			return null;
		}
		List<CourseStudentVO> courseStudentVOs = courseService
				.getCourseStudentVOsByCourseId(courseId, percentType);

		ExcelTemplate template = ExcelTemplate
				.newInstance("excelTemp/excel.xls");
		template.createRow(0);

		template.createCell("课程名称");
		template.createCell("课程类型");
		template.createCell("id");
		template.createCell("姓名");
		template.createCell("成绩");

		int index = 1;
		for (CourseStudentVO courseStudentVO : courseStudentVOs) {
			// 创建一行
			template.createRow(index);
			// 创建列
			template.createCell(course.getName());
			template.createCell(CoursePercentType.genCoursePercentType(
					percentType).getName());
			template.createCell(String.valueOf(courseStudentVO.getUserId()));
			template.createCell(courseStudentVO.getName());
			template.createCell(String.valueOf(courseStudentVO.getScore()));
			index++;
		}
		response.reset();
		response.setContentType("application/x-download;charset=GBK");
		response.setHeader("Content-Disposition", "attachment;filename=Book_"
				+ System.currentTimeMillis() + ".xls");
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
	public ModelAndView readScoreFromExcel(HttpServletRequest request,
			HttpServletResponse response) {
		long courseId = ServletRequestUtils.getLongParameter(request,
				"courseId", -1L);
		long percentType = ServletRequestUtils.getLongParameter(request,
				"percentTypeId", -1L);
		int stage = ServletRequestUtils.getIntParameter(request, "stage", -1);
		if (courseId < 0 || percentType < 0) {
			return null;
		}
		long teacher = MyUser.getMyUser(request);
		Course course = courseService.getCourseById(courseId);
		if (course == null) {
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

					for (int i = 1; i <= totalRow; i++) {
						HSSFRow row = sheet.getRow(i);

						HSSFCell cell2 = row.getCell(2);
						HSSFCell cell4 = row.getCell(4);
						if (cell2 != null) {
							cell2.setCellType(Cell.CELL_TYPE_STRING);
						}
						if (cell4 != null) {
							cell4.setCellType(Cell.CELL_TYPE_STRING);
						}
						if (cell2 == null
								|| StringUtils.isEmpty(cell2
										.getRichStringCellValue().getString())) {
							continue;
						}
						if (cell4 == null
								|| StringUtils.isEmpty(cell4
										.getRichStringCellValue().getString())) {
							continue;
						}
						long userId = Long.valueOf(cell2
								.getRichStringCellValue().getString());
						double score = Double.valueOf(cell4
								.getRichStringCellValue().getString());
						if (score < 0) {
							continue;
						}
						if (CoursePercentType.AvgGrading.getValue() == percentType) {
							if (stage < 0) {
								logger.error("分期给分的stage为空");
								break;
							}
							courseService.insertCourseStageScore(courseId,
									stage, score, userId, teacher);
						} else {
							courseService.insertCourseScore(courseId, userId,
									percentType, score, teacher);
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
	 * 下载学生信息excel
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView downLoadStudentProfileExcel(HttpServletRequest request,
			HttpServletResponse response) {

		long classId = ServletRequestUtils.getLongParameter(request, "classId",
				-1L);
		if (classId < 0) {
			logger.error("没有班级id");
			return null;
		}
		ExcelTemplate template = ExcelTemplate
				.newInstance("excelTemp/excel.xls");
		template.createRow(0);

		template.createCell("姓名");
		template.createCell("用户名");
		template.createCell("密码");
		List<Profile> profileList = classService.getProfilesByClassId(classId,
				ProfileLevel.Student.getValue());
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
		response.setHeader("Content-Disposition", "attachment;filename=Book_"
				+ System.currentTimeMillis() + ".xls");
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
	public ModelAndView upLoadStudentProfileExcel(HttpServletRequest request,
			HttpServletResponse response) {

		long classId = ServletRequestUtils.getLongParameter(request, "classId",
				-1L);
		if (classId < 0) {
			return null;
		}
		com.ruoogle.teach.meta.Class class1 = classService
				.getClassById(classId);
		if (class1 == null) {
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

					Profile maxProfile = profileMapper
							.getMaxProfileByNumber(classId);
					long maxNumber = 0;
					if (maxProfile != null) {
						maxNumber = maxProfile.getNumber() + 1;
					}
					for (int i = 1; i <= totalRow; i++) {
						HSSFRow row = sheet.getRow(i);
						HSSFCell cell1 = row.getCell(0);
						HSSFCell cell2 = row.getCell(1);
						HSSFCell cell3 = row.getCell(2);
						if (cell1 != null) {
							cell1.setCellType(Cell.CELL_TYPE_STRING);
						}
						if (cell2 != null) {
							cell2.setCellType(Cell.CELL_TYPE_STRING);
						}
						if (cell3 != null) {
							cell3.setCellType(Cell.CELL_TYPE_STRING);
						}

						if (cell1.getRichStringCellValue().getString().trim()
								.isEmpty()
								&& cell2.getRichStringCellValue().getString()
										.trim().isEmpty()
								&& cell3.getRichStringCellValue().getString()
										.trim().isEmpty()) {
							break;
						}
						if (cell1.getRichStringCellValue().getString().trim()
								.isEmpty()) {
							throw new java.lang.Exception();

						}
						if (cell2 == null
								|| cell2.getRichStringCellValue().getString()
										.trim().isEmpty()) {

							classService.addStudentProfile(classId,
									maxNumber++, cell1.getRichStringCellValue()
											.getString().trim());

						}

					}
					classService.updateClassStudentCount(classId);
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

	public ModelAndView downLoadTeacherProfileExcel(HttpServletRequest request,
			HttpServletResponse response) {

		ExcelTemplate template = ExcelTemplate
				.newInstance("excelTemp/excel.xls");
		template.createRow(0);

		template.createCell("姓名");
		template.createCell("用户名");
		template.createCell("密码");
		template.createCell("类型(0普通教师,1企业教师)");
		response.reset();
		response.setContentType("application/x-download;charset=GBK");
		response.setHeader("Content-Disposition", "attachment;filename=Book_"
				+ System.currentTimeMillis() + ".xls");
		try {
			template.getWorkbook().write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ModelAndView upLoadTeacherProfileExcel(HttpServletRequest request,
			HttpServletResponse response) {

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

					for (int i = 1; i <= totalRow; i++) {
						HSSFRow row = sheet.getRow(i);
						HSSFCell cell1 = row.getCell(0);
						HSSFCell cell2 = row.getCell(1);
						HSSFCell cell3 = row.getCell(2);
						HSSFCell cell4 = row.getCell(3);
						if (cell1 != null) {
							cell1.setCellType(Cell.CELL_TYPE_STRING);
						}
						if (cell2 != null) {
							cell2.setCellType(Cell.CELL_TYPE_STRING);
						}
						if (cell3 != null) {
							cell3.setCellType(Cell.CELL_TYPE_STRING);
						}
						if (cell4 != null) {
							cell4.setCellType(Cell.CELL_TYPE_STRING);
						}

						if (cell1.getRichStringCellValue().getString().trim()
								.isEmpty()
								|| cell2.getRichStringCellValue().getString()
										.trim().isEmpty()
								|| cell3.getRichStringCellValue().getString()
										.trim().isEmpty()
								|| cell4.getRichStringCellValue().getString()
										.trim().isEmpty()) {
							break;
						}
						int level = 0;
						if (cell4.getRichStringCellValue().getString().trim()
								.equals("1")) {
							level = ProfileLevel.CompanyLeader.getValue();
						} else if (cell4.getRichStringCellValue().getString()
								.trim().equals("0")) {
							level = ProfileLevel.Teacher.getValue();
						} else {
							break;
						}
						profileService.addProfile(cell1
								.getRichStringCellValue().getString().trim(),
								cell2.getRichStringCellValue().getString()
										.trim(), cell3.getRichStringCellValue()
										.getString().trim(), level);
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
	 * 教师下载某门课的成绩单
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView downLoadCourseStudentScoreExcel(
			HttpServletRequest request, HttpServletResponse response) {

		long courseId = ServletRequestUtils.getLongParameter(request,
				"courseId", -1L);

		if (courseId < 0) {
			logger.error("没有课程id");
			return null;
		}
		long userId = MyUser.getMyUser(request);
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		if (myUser == null) {
			logger.error("没有MyUser");
			return null;
		}
		if (myUser.getLevel() != ProfileLevel.Teacher.getValue()
				&& myUser.getLevel() != ProfileLevel.CompanyLeader.getValue()) {
			logger.error("不是教师");
			return null;
		}
		ExcelTemplate template = ExcelTemplate
				.newInstance("excelTemp/excel.xls");
		template.createRow(0);

		Course course = courseService.getCourseById(courseId);
		if (course == null) {
			logger.error("没有这门课");
			return null;
		}
		List<CourseScorePercent> courseScorePercents = courseService
				.getCourseScorePercentListByCourseId(courseId);

		template.createCell("id");
		template.createCell("姓名");
		int cellCount = courseScorePercents.size();
		for (CourseScorePercent courseScorePercent : courseScorePercents) {
			CoursePercentType coursePercentType = CoursePercentType
					.genCoursePercentType(courseScorePercent.getPercentType());
			template.createCell(coursePercentType.getName());
		}

		template.createCell("总成绩");
		List<CourseStudentScoreVO> courseStudentScoreVOList = courseService
				.getCourseStudentScoreVOsByCourseId(courseId);
		if (!ListUtils.isEmptyList(courseStudentScoreVOList)) {
			for (int i = 0; i < courseStudentScoreVOList.size(); i++) {
				template.createRow(i + 1);
				CourseStudentScoreVO courseStudentScoreVO = courseStudentScoreVOList
						.get(i);

				template.createCell(String.valueOf(courseStudentScoreVO
						.getUserId()));
				template.createCell(courseStudentScoreVO.getName());
				if (!ListUtils.isEmptyList(courseStudentScoreVO.getScoreList())) {
					for (CourseScorePercent courseScorePercent : courseScorePercents) {
						boolean succ = false;
						for (CourseStudentScore courseStudentScore : courseStudentScoreVO
								.getScoreList()) {
							if (courseScorePercent.getPercentType() == courseStudentScore
									.getPercentType()) {

								BigDecimal b = new BigDecimal(
										courseStudentScore.getScore());
								double f1 = b.setScale(2,
										BigDecimal.ROUND_HALF_UP).doubleValue();
								template.createCell(String.valueOf(f1));
								succ = true;
							}
						}
						if (!succ) {
							template.createCell("");
						}
					}
				} else {
					for (int j = 0; j < cellCount; j++) {
						template.createCell("");
					}
				}
				if (courseStudentScoreVO.getTotalScore() != null) {
					template.createCell(String.valueOf(courseStudentScoreVO
							.getTotalScore().getScore()));
				}

			}
		}
		response.reset();
		response.setContentType("application/x-download;charset=GBK");
		response.setHeader("Content-Disposition",
				"attachment;filename=totalScore_student.xls");
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
	public ModelAndView upLoadCourseStudentExcel(HttpServletRequest request,
			HttpServletResponse response) {

		long courseId = ServletRequestUtils.getLongParameter(request,
				"courseId", -1L);
		int percentType = ServletRequestUtils.getIntParameter(request,
				"percentType", -1);
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
						logger.error("WebExcelController upLoadStudentProfileExcel error where course==null where id="
								+ courseId);
						throw new Exception();
					}
					for (int i = 0; i <= totalRow; i++) {
						HSSFRow row = sheet.getRow(i);
						HSSFCell cell1 = row.getCell(0);
						HSSFCell cell3 = row.getCell(2);
						if (cell1.getRichStringCellValue().getString().trim()
								.isEmpty()) {
							break;
						}
						long studentId = Long.valueOf(cell1
								.getRichStringCellValue().getString());
						if (!cell3.getRichStringCellValue().getString().trim()
								.isEmpty()) {
							double score = Double.valueOf(cell3
									.getRichStringCellValue().getString());
							if (CoursePercentType.AvgGrading.getValue() == percentType) {
								if (stage < 0) {
									logger.error("分期给分的stage为空");
									break;
								}
								courseService.insertCourseStageScore(courseId,
										stage, score, studentId, teacherId);
							} else {
								courseService.insertCourseScore(courseId,
										studentId, percentType, score,
										teacherId);
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
	public ModelAndView downLoadStudentJournal(HttpServletRequest request,
			HttpServletResponse response) {

		long courseId = ServletRequestUtils.getLongParameter(request,
				"courseId", -1L);

		if (courseId < 0) {
			logger.error("没有课程id");
			return null;
		}
		long userId = MyUser.getMyUser(request);
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		if (myUser == null) {
			logger.error("没有MyUser");
			return null;
		}
		ExcelTemplate template = ExcelTemplate
				.newInstance("excelTemp/excel.xls");
		template.createRow(0);

		Course course = courseService.getCourseById(courseId);
		if (course == null) {
			logger.error("没有这门课");
			return null;
		}
		template.createCell("姓名");
		template.createCell("内容");
		template.createCell("发布日期");
		List<CourseStudent> courseStudents = courseService
				.getCourseStudentByCourseId(courseId);
		if (!ListUtils.isEmptyList(courseStudents)) {
			int index = 1;
			for (CourseStudent courseStudent : courseStudents) {
				List<Interactive> interactives = interactiveService
						.getInteractiveByCourseId(courseId,
								courseStudent.getUserId());
				if (!ListUtils.isEmptyList(interactives)) {
					Profile profile = profileService.getProfile(courseStudent
							.getUserId());
					template.createRow(index++);
					template.createCell(profile.getName());
					for (Interactive interactive : interactives) {
						template.createRow(index++);
						template.createCell("");
						template.createCell(interactive.getContent());
						template.createCell(TimeUtil.getFormatTime(interactive
								.getCreateTime()));
					}
				}
			}
		}
		response.reset();
		response.setContentType("application/x-download;charset=GBK");
		response.setHeader("Content-Disposition",
				"attachment;filename=interactive.xls");
		try {
			template.getWorkbook().write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
