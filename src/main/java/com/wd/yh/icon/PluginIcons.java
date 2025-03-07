package com.wd.yh.icon;

import com.intellij.openapi.util.IconLoader;
import javax.swing.Icon;

public class PluginIcons {

	public static final Icon JSON_KEY = load("/icons/json_key.svg");
	public static final Icon JSON_ARRAY = load("/icons/json_array.svg");
	public static final Icon JSON_ITEM = load("/icons/json_item.svg");
	public static final Icon JSON_OBJECT = load("/icons/json_object.svg");
	public static final Icon JSON_OBJECT_ITEM = load("/icons/json_object_item.svg");
	public static final Icon INTELLIJ_COLLAPSE_ALL = load("/icons/intellij_collapseAll.svg");
	public static final Icon INTELLIJ_EXPAND_ALL = load("/icons/intellij_expandAll.svg");
	public static final Icon INTELLIJ_REFRESH = load("/icons/refresh.svg");
	public static final Icon INTELLIJ_REFRESH_DARK = load("/icons/refresh_dark.svg");
	public static final Icon INTELLIJ_CONFIG_FILE = load("/icons/configFile.svg");
	public static final Icon INTELLIJ_CONFIG_FILE_DARK = load("/icons/configFile_dark.svg");
	public static final Icon SETTING =  load("/icons/settings.svg");
	public static final Icon SETTING_DARK =  load("/icons/settings_dark.svg");

	public static final Icon YAML =  load("/icons/yaml.svg");
	public static final Icon YAML_DARK =  load("/icons/yaml_dark.svg");
	public static final Icon JAVA =  load("/icons/java.svg");
	public static final Icon JAVA_DARK = load("/icons/java_dark.svg");
	public static final Icon PROPERTIES = load("/icons/properties.svg");
	public static final Icon PROPERTIES_DARK = load("/icons/properties_dark.svg");
	
	public static Icon load(String iconPath) {
		return IconLoader.getIcon(iconPath, PluginIcons.class);
	}

}
