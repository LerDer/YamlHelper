package com.wd.yh.wrapper;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.treeStructure.Tree;
import com.wd.yh.util.UIManagers;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JsonStructureDialog extends DialogWrapper {

	private Tree tree;
	private final JsonWrapper wrapper;
	private JsonStructureComponentProvider componentProvider;

	public JsonStructureDialog(JsonWrapper wrapper) {
		super((Project) null, true);
		this.wrapper = wrapper;

		setModal(false);
		init();
	}

	@Override
	public @Nullable JComponent createCenterPanel() {
		componentProvider = new JsonStructureComponentProvider(wrapper, getRootPane(), true);
		tree = componentProvider.getTree();
		TreeNode root = (TreeNode) tree.getModel().getRoot();
		UIManagers.expandAll(tree, new TreePath(root));
		JPanel rootPanel = componentProvider.getTreeComponent();
		rootPanel.setPreferredSize(new Dimension(400, 470));
		return rootPanel;
	}

	public void rebuild(JsonWrapper wrapper) {
		componentProvider.rebuildTree(wrapper);
	}

	@Override
	protected Action @NotNull [] createActions() {
		List<Action> actions = new ArrayList<>();
		actions.add(getOKAction());
		actions.add(getHelpAction());
		return actions.toArray(new Action[0]);
	}

	@Override
	public @Nullable JComponent getPreferredFocusedComponent() {
		return tree;
	}

	@Override
	protected @NonNls @Nullable String getHelpId() {
		return "";
	}

	@Override
	public void show() {
		ApplicationManager.getApplication().invokeLater(super::show);
	}

}
