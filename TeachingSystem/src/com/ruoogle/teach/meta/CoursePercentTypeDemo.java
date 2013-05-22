package com.ruoogle.teach.meta;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CoursePercentTypeDemo implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private String name;
	private String demoJson;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDemoJson() {
		return demoJson;
	}

	public void setDemoJson(String demoJson) {
		this.demoJson = demoJson;
	}

	public static String getCoursePercentTypeList(List<CoursePercentType> coursePercentTypes) {
		JSONArray jsonArray = new JSONArray();
		for (CoursePercentType coursePercentType : coursePercentTypes) {
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("name", coursePercentType.getName());
			jsonObject2.put("desc", coursePercentType.getDesc());
			jsonObject2.put("objectCount", coursePercentType.getObjectCount());
			jsonArray.add(jsonObject2);
		}
		return jsonArray.toString();
	}
}