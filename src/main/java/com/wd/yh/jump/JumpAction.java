package com.wd.yh.jump;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.ui.treeStructure.Tree;
import com.wd.yh.node.JsonTreeNode;
import com.wd.yh.node.JsonTreeNodeType;
import java.util.Objects;
import javax.swing.tree.TreePath;
import org.jetbrains.annotations.NotNull;

public class JumpAction extends DumbAwareAction {

	private final Tree tree;

	public JumpAction(Tree tree) {
		super("Jump2Source",
				"Jump to the source file",
				null);
		this.tree = tree;
	}

	@Override
	public void actionPerformed(@NotNull AnActionEvent event) {
		TreePath[] paths = tree.getSelectionPaths();
		if (paths != null) {
			for (TreePath path : paths) {
				JsonTreeNode node = (JsonTreeNode) path.getLastPathComponent();
				int lineNumber;
				if (JsonTreeNodeType.JSONObjectProperty.equals(node.getNodeType())) {
					lineNumber = YamlLineNumberFinder.getLineNumber(event, node);
				} else {
					lineNumber = YamlLineNumberFinder.getObjectLineNumber(event, node);
				}
				if (lineNumber > 0) {
					JumpToLineUtil.jumpToLine(event, lineNumber);
				}
			}
		}
	}

	@Override
	public void update(@NotNull AnActionEvent event) {
		event.getPresentation().setEnabledAndVisible(isVisible(tree));
	}

	public static boolean isVisible(Tree tree) {
		TreePath[] paths = tree.getSelectionPaths();
		if (Objects.nonNull(paths) && paths.length == 1) {
			TreePath path = paths[0];
			JsonTreeNode node = (JsonTreeNode) path.getLastPathComponent();
			JsonTreeNodeType nodeType = node.getNodeType();
			return !Objects.equals(JsonTreeNodeType.JSONArrayElement, nodeType);
		}
		return true;
	}
}
