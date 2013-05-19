package com.ruoogle.teach.dwr;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruoogle.teach.service.CourseService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-20 上午12:41:48
 * @see Class Description
 */
@Service("dwrTeachSysBean")
public class DwrTeachSysBean {
	@Resource
	private CourseService courseService;

	/**
	 * 添加课程评分类型
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param desc
	 * @return
	 */
	public boolean addNewCoursePercentType(String name, String desc) {
		return courseService.addNewCoursePercentType(name, desc);
	}
}
