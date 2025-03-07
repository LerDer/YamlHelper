package com.wd.yh.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertiesParser {

	public static List<ConfigEntry> parseProperties(String filePath) {
		List<ConfigEntry> entries = new ArrayList<>();
		Properties properties = new Properties();

		// 加载 properties 文件
		try (FileInputStream inputStream = new FileInputStream(filePath)) {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 遍历 properties，提取键和值
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			entries.add(new ConfigEntry(key, value));
		}

		return entries;
	}
}