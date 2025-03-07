package com.wd.yh.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.UpdateInBackground;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.ui.treeStructure.Tree;
import com.wd.yh.util.UIManagers;
import org.jetbrains.annotations.NotNull;

import javax.swing.tree.TreePath;

public class CollapseMultiAction extends DumbAwareAction implements UpdateInBackground {
    private final Tree tree;

    public CollapseMultiAction(Tree tree) {
        super("Collapse",
                "Collapses the node and its children",
                null);
        this.tree = tree;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        TreePath[] paths = tree.getSelectionPaths();
        if (paths != null) {
            for (TreePath path : paths) {
                UIManagers.collapseAll(tree, path);
            }
        }
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public void update(@NotNull AnActionEvent event) {
        event.getPresentation().setEnabledAndVisible(ExpandMultiAction.isEnabled(tree));
    }
}
