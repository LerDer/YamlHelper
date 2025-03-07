package com.wd.yh.parser;

import cn.hutool.core.util.StrUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class YamlParser {

	public static List<ConfigEntry> parseYaml(String filePath) {
		List<ConfigEntry> entries = new ArrayList<>();
		Yaml yaml = new Yaml();

		FileInputStream inputStream = null;
		Map<String, Object> yamlData = null;
		try {
			inputStream = new FileInputStream(filePath);
			yamlData = (Map<String, Object>) yaml.load(inputStream);
		} catch (FileNotFoundException e) {
			System.out.println(filePath + ": File not found");
			return entries;
		}
		// 解析 YAML 文件为 Map
		// 递归遍历 Map，提取完整的键和值
		flattenYaml("", yamlData, entries);
		return entries;
	}

	private static void flattenYaml(String parentKey, Map<String, Object> yamlData, List<ConfigEntry> entries) {
		for (Map.Entry<String, Object> entry : yamlData.entrySet()) {
			String fullKey = null;
			try {
				fullKey = parentKey.isEmpty() ? entry.getKey() : parentKey + "." + entry.getKey();
			} catch (Exception e) {
				System.err.println(e);
			}
			Object value = entry.getValue();
			if (StrUtil.isBlank(fullKey)) {
				continue;
			}

			if (value instanceof Map) {
				// 如果值是嵌套的 Map，递归处理
				flattenYaml(fullKey, (Map<String, Object>) value, entries);
			} else {
				// 如果值是普通类型，添加到 List
				entries.add(new ConfigEntry(fullKey, value));
			}
		}
	}
}