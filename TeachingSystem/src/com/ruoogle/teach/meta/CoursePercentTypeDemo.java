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

	private CoursePercentType percentType;

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

	public CoursePercentType getPercentType() {
		return percentType;
	}

	public void setPercentType(CoursePercentType percentType) {
		this.percentType = percentType;
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