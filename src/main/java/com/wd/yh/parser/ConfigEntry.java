package com.wd.yh.parser;

import java.util.ArrayList;
import java.util.List;

public class ConfigEntry {

	private String key;
	private Object value;

	public ConfigEntry(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	public String getYamlKeyValue() {
		return key + ": " + value;
	}

	public List<String> getPropertiesList() {
		List<String> propertiesList = new ArrayList<>();
		propertiesList.add(getPropertiesKeyValue1());
		propertiesList.add(getPropertiesKeyValue2());
		propertiesList.add(getPropertiesKeyValue3());
		propertiesList.add(getPropertiesKeyValue4());
		return propertiesList;
	}

	public String getPropertiesKeyValue1() {
		return key + "=" + value;
	}

	public String getPropertiesKeyValue2() {
		return key + " =" + value;
	}

	public String getPropertiesKeyValue3() {
		return key + "= " + value;
	}

	public String getPropertiesKeyValue4() {
		return key + " = " + value;
	}

	@Override
	public String toString() {
		return "YamlEntry{" +
				"key='" + key + '\'' +
				", value=" + value +
				'}';
	}
}