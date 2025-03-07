package com.wd.yh.jump;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.wd.yh.analyzer.YmlFileEditorProvider;

public class JumpToLineUtil {

    public static void jumpToLine(AnActionEvent event, int lineNumber) {
        Project project = event.getProject();
        if (project == null) {
            return;
        }
        PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);
        if (psiFile == null) {
            return;
        }

        VirtualFile virtualFile = psiFile.getVirtualFile();
        if (virtualFile == null || !virtualFile.exists()) {
            return;
        }
        TextEditor textEditor = YmlFileEditorProvider.editor;
        if (textEditor != null) {
            // 从 TextEditor 中获取 Editor 对象
            Editor editor = textEditor.getEditor();
            int offset = editor.getDocument().getLineStartOffset(lineNumber);
            editor.getCaretModel().moveToOffset(offset);
            editor.getScrollingModel().scrollToCaret(ScrollType.CENTER);
        }
    }
}
