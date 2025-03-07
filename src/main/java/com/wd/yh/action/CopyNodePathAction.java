package com.wd.yh.action;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.ui.treeStructure.Tree;
import com.wd.yh.node.JsonTreeNode;
import com.wd.yh.node.JsonTreeNodeType;
import com.wd.yh.util.PlatformUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class CopyNodePathAction extends DumbAwareAction {

    private final Tree tree;

    public CopyNodePathAction(Tree tree) {
        super("Copy Node Path",
                "Copy the path of the current node",
                null);
        this.tree = tree;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        TreePath[] paths = tree.getSelectionPaths();
        if (ArrayUtil.isNotEmpty(paths)) {
            List<String> pathList = new ArrayList<>();
            for (TreePath path : paths) {
                StringBuilder pathString = new StringBuilder();
                for (Object element : path.getPath()) {
                    JsonTreeNode node = (JsonTreeNode) element;
                    JsonTreeNodeType nodeType = node.getNodeType();
                    if (JsonTreeNodeType.JSONArrayElement == nodeType) {
                        // 获取父节点，并得知当前节点在父节点的索引位置
                        TreeNode parent = node.getParent();
                        int index = parent.getIndex(node);
                        pathString.append("[").append(index).append("]");
                    } else {
                        pathString.append(pathString.length() > 0 ? " -> " : "").append(node.getUserObject());
                    }
                }

                pathList.add(pathString.toString());
            }

            PlatformUtil.setClipboard(StrUtil.join(", \n", pathList));
        }
    }

}
