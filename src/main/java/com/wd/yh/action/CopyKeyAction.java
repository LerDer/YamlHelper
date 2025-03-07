package com.wd.yh.action;

import cn.hutool.core.util.StrUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.ui.treeStructure.Tree;
import com.wd.yh.node.JsonTreeNode;
import com.wd.yh.node.JsonTreeNodeType;
import com.wd.yh.util.PlatformUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CopyKeyAction extends DumbAwareAction {

    private final Tree tree;

    public CopyKeyAction(Tree tree) {
        super("Copy Key",
                "Copy keys to clipboard",
                null);
        this.tree = tree;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        TreePath[] paths = tree.getSelectionPaths();
        if (paths != null) {
            List<String> keyList = new ArrayList<>();
            for (TreePath path : paths) {
                JsonTreeNode node = (JsonTreeNode) path.getLastPathComponent();
                // 有key
                if (!JsonTreeNodeType.JSONArrayElement.equals(node.getNodeType())) {
                    // key
                    keyList.add(node.getUserObject().toString());
                }
            }

            PlatformUtil.setClipboard(StrUtil.join(", ", keyList));
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