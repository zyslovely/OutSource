import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengyisheng E-mail:zhengyisheng@corp.netease.com
 * @version CreateTime锛�012-3-9 涓嬪崍10:03:24 Class Description
 */
public class Test {

	public static void main(String[] args) throws Exception {

		String metaJavaPath = "/Users/apple/Downloads/code/meta/";
		String mapperJavaPath = "/Users/apple/Downloads/code/mapper/";
		String mapperXmlPath = "/Users/apple/Downloads/code/mapperxml/";

		Test.CreateDir(metaJavaPath);
		Test.CreateDir(mapperJavaPath);
		Test.CreateDir(mapperXmlPath);

		String metaJavaPackage = "com.ruoogle.teach.meta";
		String mapperJavaPackage = "com.ruoogle.teach.mapper";

		String functionName = "Semester";
		String firstLowerCaseName = "semester";
		String tableName = "TB_Semester";

		List<String> metaNameList = new ArrayList<String>();
		metaNameList.add("id");
		metaNameList.add("name");

		List<String> metaTypeList = new ArrayList<String>();
		metaTypeList.add("long");
		metaTypeList.add("String");

		String code = "package " + metaJavaPackage + "; import java.io.Serializable; public class " + functionName + " implements Serializable {"
				+ " private static final long serialVersionUID = 6L;";
		int index = 0;
		for (String str : metaNameList) {
			code = code + " private " + " " + metaTypeList.get(index) + " " + str + " ;";
			index++;
		}
		code = code + " }";
		// 生成java文件
		File file = new File(metaJavaPath + functionName + ".java");
		try {
			if (!file.exists()) {
				FileOutputStream fop = new FileOutputStream(file);
				fop.write(code.getBytes());
				fop.flush();
				fop.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		code = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>  <!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\"> "
				+ "<mapper namespace=\""
				+ mapperJavaPackage
				+ "."
				+ functionName
				+ "Mapper\"> <resultMap type=\""
				+ functionName
				+ "\" id=\""
				+ firstLowerCaseName + "Map \"> ";
		for (String str : metaNameList) {
			code = code + " <result property=\"" + str + "\" column=\"" + str + "\" /> ";
		}
		code = code + " </resultMap>";
		code = code + " <insert id=\"add" + functionName + "\" parameterType=\"" + functionName + "\">";
		code = code + " INSERT INTO " + tableName + " (";
		index = 0;
		for (String str : metaNameList) {
			code = code + str;
			index++;
			if (index != metaNameList.size()) {
				code += ",";
			}
		}
		code += " )VALUES( ";
		index = 0;
		for (String str : metaNameList) {
			code = code + "#{" + str + "}";
			index++;
			if (index != metaNameList.size()) {
				code += ",";
			}
		}
		code += ")";
		code += "</insert>";
		code += "</mapper>";
		// 生成java文件
		File file1 = new File(mapperXmlPath + functionName + "Mapper.xml");
		try {
			if (!file1.exists()) {
				FileOutputStream fop = new FileOutputStream(file1);
				fop.write(code.getBytes());
				fop.flush();
				fop.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		code = "package " + mapperJavaPackage + ";" + " import " + metaJavaPackage + "." + functionName + "; public interface " + functionName
				+ "Mapper { " + "public int add" + functionName + "(" + functionName + " " + firstLowerCaseName + ");";

		code += " }";

		// 生成java文件
		File file2 = new File(mapperJavaPath + functionName + "Mapper.java");
		try {
			if (!file2.exists()) {
				FileOutputStream fop = new FileOutputStream(file2);
				fop.write(code.getBytes());
				fop.flush();
				fop.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void CreateDir(String distPath) throws Exception {
		File file = new File(distPath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

}
