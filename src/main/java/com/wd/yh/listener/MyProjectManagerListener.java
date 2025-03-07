package com.wd.yh.listener;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;

public class MyProjectManagerListener implements ProjectManagerListener {

	@Override
	public void projectClosed(Project project) {
		EditorFactory editorFactory = EditorFactory.getInstance();
		Editor[] editors = editorFactory.getAllEditors();
		for (Editor editor : editors) {
			editorFactory.releaseEditor(editor);
		}
	}
}
