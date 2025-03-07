package com.wd.yh.action;

import cn.hutool.core.util.ArrayUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.ui.treeStructure.Tree;
import com.wd.yh.node.JsonTreeNode;
import com.wd.yh.node.JsonTreeNodeType;
import com.wd.yh.util.UIManagers;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.TreePath;
import java.util.Objects;

public class ExpandMultiAction extends DumbAwareAction {

    private final Tree tree;

    public ExpandMultiAction(Tree tree) {
        super("Expand",
                "Expand the node and its children",
                null);
        this.tree = tree;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        TreePath[] paths = tree.getSelectionPaths();
        if (paths != null) {
            for (TreePath path : paths) {
                UIManagers.expandAll(tree, path);
            }
        }
    }


    @Override
    public void update(@NotNull AnActionEvent event) {
        event.getPresentation().setEnabledAndVisible(isEnabled(tree));
    }


    public static boolean isEnabled(Tree tree) {
        TreePath[] paths = tree.getSelectionPaths();
        if (ArrayUtil.isNotEmpty(paths)) {
            for (TreePath path : paths) {
                JsonTreeNode node = (JsonTreeNode) path.getLastPathComponent();
                JsonTreeNodeType nodeType = node.getNodeType();
                if (Objects.equals(nodeType, JsonTreeNodeType.JSONObject)
                        || Objects.equals(nodeType, JsonTreeNodeType.JSONArray)
                        || Objects.equals(nodeType, JsonTreeNodeType.JSONObjectElement))
                    return true;
            }
        }

        return false;
    }
}