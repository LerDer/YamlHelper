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

public class CopyKeyValueAction extends DumbAwareAction {

    private final Tree tree;

    public CopyKeyValueAction(Tree tree) {
        super("Copy Key-Value",
                "Copy key-value to the clipboard",
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

                String userObject = node.getUserObject().toString();
                // 只有JSONArrayElement是没有Value的
                if (Objects.equals(JsonTreeNodeType.JSONArrayElement, nodeType)) {
                    valueList.add(userObject);
                } else if (Objects.equals(JsonTreeNodeType.JSONObjectProperty, nodeType)) {
                    valueList.add(userObject + ": " + (Objects.nonNull(value) ? value.toString() : "null"));
                } else {
                    JsonWrapper json = (JsonWrapper) value;
                    String item = Objects.nonNull(json) ? JsonUtil.formatJson(json) : "null";
                    valueList.add(userObject + ": " + item);
                }
            }

            PlatformUtil.setClipboard(StrUtil.join(", \n", valueList));
        }
    }

    @Override
    public void update(@NotNull AnActionEvent event) {
        event.getPresentation().setEnabledAndVisible(CopyKeyAction.isVisible(tree));
    }
}