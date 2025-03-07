package com.wd.yh.persistent;

import cn.hutool.json.JSONObject;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

@State(name = "YamlPersistentPersistentState", storages = {@Storage(value = "YamlPersistentPersistentState.xml")})
public class YamlPersistent implements PersistentStateComponent<YamlPersistent> {

	private String yamlFilePath;

	private JSONObject jsonObject;

	public String getYamlFilePath() {
		return yamlFilePath;
	}

	public void setYamlFilePath(String yamlFilePath) {
		this.yamlFilePath = yamlFilePath;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public static YamlPersistent getInstance() {
		YamlPersistent config = ServiceManager.getService(YamlPersistent.class);
		if (config == null) {
			config = new YamlPersistent();
		}
		return config;
	}

	public static YamlPersistent getInstance1(Project project) {
		return project.getComponent(YamlPersistent.class);
	}

	@Override
	public YamlPersistent getState() {
		return this;
	}

	@Override
	public void loadState(@NotNull YamlPersistent state) {
		XmlSerializerUtil.copyBean(state, this);
	}
}
