package com.wd.yh.action;

import cn.hutool.core.util.StrUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.ui.treeStructure.Tree;
import com.wd.yh.node.JsonTreeNode;
import com.wd.yh.node.JsonTreeNodeType;
import com.wd.yh.util.JsonUtil;
import com.wd.yh.util.PlatformUtil;
import com.wd.yh.wrapper.JsonWrapper;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CopyValueAction extends DumbAwareAction {
    private final Tree tree;

    public CopyValueAction(Tree tree) {
        super("Copy Value",
                "Copy values to the clipboard",
                null);
        this.tree = tree;
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public void actionPerformed(@NotNull AnActionEvent event) {
        TreePath[] paths = tree.getSelectionPaths();
        if (paths != null) {
            List<String> valueList = new ArrayList<>();
            for (TreePath path : paths) {
                JsonTreeNode node = (JsonTreeNode) path.getLastPathComponent();
                // 获取value值，多个的话用其他处理方式
                Object value = node.getValue();
                JsonTreeNodeType nodeType = node.getNodeType();
                // JSONArrayElement及JSONObjectProperty都是普通类型
                if (Objects.equals(JsonTreeNodeType.JSONArrayElement, nodeType)
                        || Objects.equals(JsonTreeNodeType.JSONObjectProperty, nodeType)) {
                    valueList.add(Objects.nonNull(value) ? value.toString() : "null");
                } else {
                    JsonWrapper json = (JsonWrapper) value;
                    String item = Objects.nonNull(json) ? JsonUtil.formatJson(json) : "null";
                    valueList.add(item);
                }
            }

            PlatformUtil.setClipboard(StrUtil.join(", \n", valueList));
        }
    }
}