package com.wd.yh.analyzer;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.fileEditor.FileEditorStateLevel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.wd.yh.gui.YamlGui;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UIFormEditor extends UserDataHolderBase implements FileEditor {

	private static final Logger log = LoggerFactory.getLogger(UIFormEditor.class);
	public static final FileEditorState MY_EDITOR_STATE = (otherState, level) -> false;
	private final VirtualFile file;
	private YamlGui yamlEditor;

	public YamlGui getYamlEditor() {
		return yamlEditor;
	}

	public UIFormEditor(Project project, VirtualFile file) {
		this.file = file;
		yamlEditor = new YamlGui(project, file);
	}

	@Override
	public @NotNull JComponent getComponent() {
		if (yamlEditor != null) {
			return yamlEditor.getRootComponent();
		}
		return new JLabel("Unexpected error. Try it again.");
	}

	@Override
	public void dispose() {
		if (yamlEditor != null) {
			yamlEditor.dispose();
		}
	}

	@Override
	public VirtualFile getFile() {
		return file;
	}

	@Override
	public JComponent getPreferredFocusedComponent() {
		if (yamlEditor != null) {
			return yamlEditor.getPreferredFocusedComponent();
		}
		return null;
	}

	@Override
	@NotNull
	public String getName() {
		return "Yaml Table";
	}

	@Override
	public boolean isModified() {
		return false;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void selectNotify() {
		if (yamlEditor != null) {
			yamlEditor.selectNotify();
		}
	}

	@Override
	public void deselectNotify() {
	}

	@Override
	public void addPropertyChangeListener(@NotNull final PropertyChangeListener listener) {
	}

	@Override
	public void removePropertyChangeListener(@NotNull final PropertyChangeListener listener) {
	}

	@Override
	public BackgroundEditorHighlighter getBackgroundHighlighter() {
		return null;
	}

	@Override
	public FileEditorLocation getCurrentLocation() {
		return null;
	}

	@Override
	@NotNull
	public FileEditorState getState(@NotNull final FileEditorStateLevel ignored) {
		return MY_EDITOR_STATE;
	}

	@Override
	public void setState(@NotNull final FileEditorState state) {
	}

	@Override
	public StructureViewBuilder getStructureViewBuilder() {
		return null;
	}

}
