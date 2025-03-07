package com.wd.yh.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.wd.yh.constant.FileTypeHolder;
import com.wd.yh.editor.MyEditorFactory;
import com.wd.yh.icon.PluginIcons;
import com.wd.yh.util.JsonUtil;
import com.wd.yh.util.NotificationUtil;
import com.wd.yh.wrapper.JsonStructureDialog;
import com.wd.yh.wrapper.JsonWrapper;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import org.yaml.snakeyaml.Yaml;

public class YamlGui implements Disposable {

	private static final Logger LOG = Logger.getInstance(YamlGui.class);

	private JPanel rootPanel;
	private JPanel editPanel;

	private Editor jsonEditor;

	private JButton refresh;
	private JButton yamlSetting;
	private JRadioButton showTree;
	private JRadioButton showJson;

	public static JPanel static_panel;
	private VirtualFile file;
	private JsonStructureDialog dialog;
	private Project project;

	public YamlGui(Project project, VirtualFile file) {
		static_panel = rootPanel;
		this.project = project;
		this.file = file;
		refresh.setIcon(PluginIcons.INTELLIJ_REFRESH);
		yamlSetting.setIcon(PluginIcons.SETTING);
		String json = getJson(file);

		MyEditorFactory factory = new MyEditorFactory(project);
		jsonEditor = factory.createEditor("result.json5", FileTypeHolder.JSON5, json);

		JsonWrapper jsonWrapper = JsonUtil.parse(JsonUtil.ensureJson(json));
		dialog = new JsonStructureDialog(jsonWrapper);
		this.editPanel.add(dialog.createCenterPanel(), BorderLayout.CENTER);

		Application application = ApplicationManager.getApplication();

		refresh.addActionListener(e -> {
			FileDocumentManager.getInstance().saveAllDocuments();
			if (showJson.isSelected()) {
				application.runWriteAction(() -> {
					jsonEditor.getDocument().setText(getJson(file));
				});
			} else {
				JsonWrapper jsonWrapper1 = JsonUtil.parse(JsonUtil.ensureJson(getJson(file)));
				dialog.rebuild(jsonWrapper1);
			}
		});
		showJson.addActionListener(e -> {
			application.runWriteAction(() -> {
				editPanel.removeAll();
				jsonEditor.getDocument().setText(getJson(file));
				editPanel.add(jsonEditor.getComponent(), BorderLayout.CENTER);
				editPanel.revalidate();
				editPanel.repaint();
			});
		});
		showTree.addActionListener(e -> {
			editPanel.removeAll();
			JsonWrapper jsonWrapper1 = JsonUtil.parse(JsonUtil.ensureJson(getJson(file)));
			dialog = new JsonStructureDialog(jsonWrapper1);
			editPanel.add(dialog.createCenterPanel(), BorderLayout.CENTER);
			editPanel.revalidate();
			editPanel.repaint();
		});
		yamlSetting.addActionListener(e -> ShowSettingsUtil.getInstance().showSettingsDialog(project, "Yaml Helper"));
	}

	public void init() {
		JsonWrapper jsonWrapper1 = JsonUtil.parse(JsonUtil.ensureJson(getJson(file)));
		dialog.rebuild(jsonWrapper1);
	}

	public String getJson(VirtualFile file) {
		String filePath = file.getPath();
		Yaml yaml = new Yaml();
		try {
			Iterable<Object> objects = yaml.loadAll(new FileInputStream(new File(filePath)));
			StringBuilder sb = new StringBuilder();
			for (Object object : objects) {
				ObjectMapper mapper = new ObjectMapper();
				sb.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
			}
			return sb.toString().replaceAll("\r\n", "\n");
		} catch (Exception e) {
			//LOG.error(e);
			//System.out.println("e = " + e);
			NotificationUtil.showErrorNotification(
					this.project,
					"Parse Error",
					"解析错误！" + e.getMessage()
			);
		}
		return "";
	}

	public static String getJson(String filePath) {
		Yaml yaml = new Yaml();
		try {
			Iterable<Object> objects = yaml.loadAll(new FileInputStream(new File(filePath)));
			StringBuilder sb = new StringBuilder();
			for (Object object : objects) {
				ObjectMapper mapper = new ObjectMapper();
				sb.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
			}
			return sb.toString().replaceAll("\r\n", "\n");
		} catch (Exception e) {
			NotificationUtil.showErrorNotification(
					"Parse Error",
					"解析错误！" + e.getMessage()
			);
			return "";
		}
	}

	public JComponent getRootComponent() {
		return rootPanel;
	}

	public JComponent getPreferredFocusedComponent() {
		return rootPanel;
	}

	@Override
	public void dispose() {
		// 释放 jsonEditor
		if (jsonEditor != null) {
			EditorFactory.getInstance().releaseEditor(jsonEditor);
			// 置空引用，便于垃圾回收
			jsonEditor = null;
		}
	}

	public void selectNotify() {
		init();
	}
}
