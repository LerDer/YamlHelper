package com.wd.yh.analyzer;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.fileEditor.TextEditorWithPreview;
import com.intellij.openapi.fileEditor.impl.text.TextEditorProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

public class YmlFileEditorProvider implements FileEditorProvider, DumbAware {

	private static final Logger LOG = Logger.getInstance("com.wd.yh.analyzer.YmlFileEditorProvider");

	public static final String[] YML_ENDS = new String[]{".yml", ".yaml"};

	public static UIFormEditor uiFormEditor;
	
	public static TextEditor editor;

	@Override
	public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
		return isYmlFile(project, file);
	}

	private boolean isYmlFile(Project project, VirtualFile file) {
		String name = file.getName();
		for (String ymlEnd : YML_ENDS) {
			if (name.endsWith(ymlEnd)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public @NotNull FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
		editor = (TextEditor) TextEditorProvider.getInstance().createEditor(project, file);
		LOG.assertTrue(accept(project, file));
		uiFormEditor = new UIFormEditor(project, file);
		editor.getEditor().getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void documentChanged(@NotNull DocumentEvent event) {
				//DocumentListener.super.documentChanged(event);
				//uiFormEditor.update();
			}
		});
		return new TextEditorWithPreview(editor, uiFormEditor, "YamlEditor");
	}

	@Override
	public void disposeEditor(@NotNull FileEditor editor) {
		Disposer.dispose(editor);
	}

	@Override
	public @NotNull FileEditorState readState(@NotNull Element sourceElement, @NotNull Project project, @NotNull VirtualFile file) {
		return UIFormEditor.MY_EDITOR_STATE;
	}

	@Override
	public void writeState(@NotNull FileEditorState state, @NotNull Project project, @NotNull Element targetElement) {
	}

	@Override
	public @NotNull String getEditorTypeId() {
		return "YamlHelperPluginAnalyzer";
	}

	@Override
	public @NotNull FileEditorPolicy getPolicy() {
		return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
	}

}
