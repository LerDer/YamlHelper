package com.wd.yh.util;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;

public class FileChooseUtil {

	public static String chooseYamlFile(Project project) {
		FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, false, false, false, false);
		descriptor.setTitle("选择 YAML 文件");
		descriptor.setDescription("请选择一个 YAML 文件");
		// 添加文件类型过滤器
		descriptor.withFileFilter(file -> file.getExtension() != null && (file.getExtension().equalsIgnoreCase("yaml") || file.getExtension().equalsIgnoreCase("yml")));

		VirtualFile file = FileChooser.chooseFile(descriptor, project, null);
		if (file != null) {
			if (file.getExtension() != null && (file.getExtension().equalsIgnoreCase("yaml") || file.getExtension().equalsIgnoreCase("yml"))) {
				return file.getPath();
			} else {
				Messages.showErrorDialog("请选择一个 Yaml 文件", "文件类型错误");
			}
		} else {
			Messages.showErrorDialog("请选择一个 Yaml 文件", "文件类型错误");
		}
		return null;
	}
}
