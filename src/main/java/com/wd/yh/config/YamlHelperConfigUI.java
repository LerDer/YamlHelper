package com.wd.yh.config;

import cn.hutool.json.JSONObject;
import com.wd.yh.gui.YamlGui;
import com.wd.yh.icon.PluginIcons;
import com.wd.yh.persistent.YamlPersistent;
import com.wd.yh.util.FileChooseUtil;
import com.wd.yh.util.JsonUtil;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public class YamlHelperConfigUI {

	private JPanel rootPanel;
	private JTextField transPath;
	private JButton configFile;

	YamlPersistent instance = YamlPersistent.getInstance();

	public YamlHelperConfigUI() {
		transPath.setText(instance.getYamlFilePath());
		configFile.addActionListener(e -> choseYamlFile());
	}

	private void choseYamlFile() {
		String path = FileChooseUtil.chooseYamlFile(null);
		if (path != null) {
			transPath.setText(path);
		}
	}

	public JPanel createComponent() {
		configFile.setIcon(PluginIcons.INTELLIJ_CONFIG_FILE);
		// 初始化
		reset();
		return rootPanel;
	}

	public boolean isModified() {
		if (instance.getYamlFilePath() == null && transPath.getText() != null) {
			return true;
		}
		if (!instance.getYamlFilePath().equals(transPath.getText())) {
			return true;
		}
		return false;
	}

	public void apply() {
		instance.setYamlFilePath(transPath.getText());
		JSONObject object = JsonUtil.toObject(YamlGui.getJson(transPath.getText()), JSONObject.class);
		instance.setJsonObject(object);
	}

	public void reset() {
	}

}
