package com.wd.yh.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CustomShortcutSet;
import com.intellij.ui.DumbAwareActionButton;
import com.intellij.ui.treeStructure.Tree;
import com.wd.yh.icon.PluginIcons;
import com.wd.yh.util.UIManagers;
import javax.swing.JComponent;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.jetbrains.annotations.NotNull;

public class CollapseAllAction extends DumbAwareActionButton {

	/**
	 * 树
	 */
	private final Tree tree;

	/**
	 * 是否包含根节点，如果为 true，则折叠所有节点，否则只折叠二级及以下节点
	 */
	private final boolean includeRoot;

	public CollapseAllAction(Tree tree, JComponent component, boolean includeRoot) {
		super("Collapse All",
				"Collapse all nodes",
				PluginIcons.INTELLIJ_COLLAPSE_ALL);
		this.tree = tree;
		this.includeRoot = includeRoot;

		if (component != null) {
			registerCustomShortcutSet(CustomShortcutSet.fromString("alt UP"), component);
		}
	}

	@Override
	public void actionPerformed(@NotNull AnActionEvent event) {
		TreeNode root = (TreeNode) tree.getModel().getRoot();
		if (includeRoot) {
			UIManagers.collapseAll(tree, new TreePath(root));
		} else {
			UIManagers.collapseSecondaryNode(tree, root);
		}
	}
}