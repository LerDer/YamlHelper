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

public class ExpandAllAction extends DumbAwareActionButton {

	/**
	 * 树
	 */
	private final Tree tree;

	/**
	 * 是否包含根节点，如果为 true，则展开所有节点，否则只展开二级及以下节点
	 */
	private final boolean includeRoot;

	public ExpandAllAction(Tree tree, JComponent component, boolean includeRoot) {
		super("Expand All",
				"Expand all nodes",
				PluginIcons.INTELLIJ_EXPAND_ALL);
		this.tree = tree;
		this.includeRoot = includeRoot;

		if (component != null) {
			registerCustomShortcutSet(CustomShortcutSet.fromString("alt DOWN"), component);
		}
	}

	@Override
	public void actionPerformed(@NotNull AnActionEvent event) {
		TreeNode root = (TreeNode) tree.getModel().getRoot();
		if (includeRoot) {
			UIManagers.expandAll(tree, new TreePath(root));
		} else {
			UIManagers.expandSecondaryNode(tree, root);
		}
	}
}