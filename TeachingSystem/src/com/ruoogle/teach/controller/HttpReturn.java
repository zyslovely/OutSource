package com.ruoogle.teach.controller;

import java.util.List;

import org.json.simple.JSONArray;

import com.eason.web.util.ListUtils;
import com.ruoogle.teach.meta.CourseProperty;
import com.ruoogle.teach.meta.CourseStudentPropertySemesterScore;
import com.ruoogle.teach.meta.CourseVO;

import net.sf.json.JSONObject;

public class HttpReturn {
	/**
	 * 课程列表返回json
	 * 
	 * @param courseVOList
	 * @param courseStudentPropertySemesterScores
	 * @param coursePropertieList
	 * @return
	 */
	public static void returnShowCourseList(
			List<CourseVO> courseList,
			List<CourseStudentPropertySemesterScore> courseStudentPropertySemesterScores,
			List<CourseProperty> coursePropertieList, JSONObject dataObject) {
		JSONArray courseArray = new JSONArray();
		if (!ListUtils.isEmptyList(courseList)) {
			for (CourseVO course : courseList) {
				JSONObject courseObject = new JSONObject();
				courseObject.put(CourseVO.KCourse_title, course.getCourse()
						.getName());
				courseObject.put(CourseVO.KCoursec_className, course
						.getClass1().getName());
				courseObject.put(CourseVO.KCoursec_courseId, course.getCourse()
						.getId());
				courseObject.put(CourseVO.KCoursec_score, course.getScore());
				courseArray.add(courseObject);
			}
		}
		dataObject.put("courseList", courseArray.toString());
		JSONArray scoreArray = new JSONArray();
		if (!ListUtils.isEmptyList(courseStudentPropertySemesterScores)) {
			for (CourseStudentPropertySemesterScore score : courseStudentPropertySemesterScores) {
				for (CourseProperty courseProperty : coursePropertieList) {
					if (courseProperty.getId() == score.getPropertyId()) {
						JSONObject scoreObject = new JSONObject();
						scoreObject.put(CourseProperty.KCourseProperty_name,
								courseProperty.getName());
						scoreObject.put(CourseProperty.KCourseProperty_id,
								courseProperty.getId());
						scoreObject.put(CourseProperty.KCourseProperty_Score,
								score.getScore());
						scoreArray.add(scoreObject);
						break;
					}
				}
			}
		}
		dataObject.put("propertyList", scoreArray.toString());
	}
}
