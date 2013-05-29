package com.ruoogle.teach.meta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CoursePercentTypeDemo implements Serializable {
	private static final long serialVersionUID = 6L;
	private long id;
	private String name;
	private String demoJson;

	private List<CoursePercentType> coursePercentTypes;

	private List<Double> percents;

	public enum CoursePercentType {
		/**
		 * 平时成绩
		 */
		Grading {
			@Override
			public int getValue() {
				return 0;
			}

			public String getName() {
				return "平时成绩";
			}

			public String getDesc() {
				return "平时成绩的描述";
			}

			public int getObjectCount() {
				return 0;
			}
		},
		/**
		 * 考试成绩
		 */
		TestResult {
			@Override
			public int getValue() {
				return 1;
			}

			public String getName() {
				return "考试成绩";
			}

			public String getDesc() {
				return "考试成绩描述";
			}

			public int getObjectCount() {
				return 0;
			}
		},
		/**
		 * 多次平时考试成绩
		 */
		AvgGrading {
			@Override
			public int getValue() {
				return 2;
			}

			public String getName() {
				return "多次平时考试成绩";
			}

			public String getDesc() {
				return "多次平时考试成绩描述";
			}

			public int getObjectCount() {
				return 8;
			}
		},
		/**
		 * 创意成绩
		 */
		CreativeResult {
			@Override
			public int getValue() {
				return 3;
			}

			public String getName() {
				return "创意成绩成绩";
			}

			public String getDesc() {
				return "创意成绩描述";
			}

			public int getObjectCount() {
				return 0;
			}
		},
		/**
		 * 团队互评成绩
		 */
		EachStudent {
			@Override
			public int getValue() {
				return 4;
			}

			public String getName() {
				return "团队互评成绩";
			}

			public String getDesc() {
				return "团队互评描述";
			}

			public int getObjectCount() {
				return 0;
			}
		};
		public abstract int getValue();

		public abstract String getName();

		public abstract String getDesc();

		public abstract int getObjectCount();

		public static CoursePercentType genCoursePercentType(int t) {
			for (CoursePercentType type : CoursePercentType.values()) {
				if (type.getValue() == t)
					return type;
			}
			return null;
		}
	}

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
		this.genCoursePercentTypeList(demoJson);
	}

	public List<CoursePercentType> getCoursePercentTypes() {
		return coursePercentTypes;
	}

	public void setCoursePercentTypes(List<CoursePercentType> coursePercentTypes) {
		this.coursePercentTypes = coursePercentTypes;
	}

	public List<Double> getPercents() {
		return percents;
	}

	public void setPercents(List<Double> percents) {
		this.percents = percents;
	}

	public static String getCoursePercentTypeList(List<Integer> coursePercentTypeId, List<Double> percents) {
		JSONArray jsonArray = new JSONArray();
		int index = 0;
		for (int typeId : coursePercentTypeId) {
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("typeId", typeId);
			jsonObject2.put("percent", percents.get(index));
			jsonArray.add(jsonObject2);
			index++;
		}
		return jsonArray.toString();
	}

	/**
	 * 生成列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param json
	 */
	public void genCoursePercentTypeList(String json) {
		JSONArray jsonArray = JSONArray.fromObject(json);
		this.coursePercentTypes = new ArrayList<CoursePercentType>();
		this.percents = new ArrayList<Double>();
		for (Object object : jsonArray.toArray()) {
			JSONObject object2 = (JSONObject) object;
			int typeId = object2.getInt("typeId");
			CoursePercentType coursePercentType = CoursePercentType.genCoursePercentType(typeId);
			
			coursePercentTypes.add(coursePercentType);
			this.percents.add(object2.getDouble("percent"));
		}
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		List<Double> alist = new ArrayList<Double>();
		alist.add(0.2);
		alist.add(0.8);
		CoursePercentTypeDemo.getCoursePercentTypeList(list, alist);
	}
}