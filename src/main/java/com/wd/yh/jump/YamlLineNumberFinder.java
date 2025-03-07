package com.wd.yh.jump;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.wd.yh.node.JsonTreeNode;

public class YamlLineNumberFinder {

	public static int getLineNumber(AnActionEvent event, JsonTreeNode node) {
		PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);
		if (psiFile == null) {
			return -1; // 如果文件为 null，返回 -1
		}
		VirtualFile virtualFile = psiFile.getVirtualFile();
		if (virtualFile == null || !virtualFile.exists()) {
			return -1; // 如果文件不存在，返回 -1
		}
		FileDocumentManager documentManager = FileDocumentManager.getInstance();
		Document document = documentManager.getDocument(virtualFile);
		if (node.getValue() == null) {
			String s = node.getUserObject() + ":";
			int offset = document.getText().indexOf(s);
			int lineNumber = document.getLineNumber(offset);
			System.out.println("lineNumber = " + lineNumber);
			return lineNumber;
		} else {
			String s = node.getUserObject() + ": " + node.getValue();
			int offset = document.getText().indexOf(s);
			if (offset == -1) {
				s = node.getUserObject() + ": \"" + node.getValue() + "\"";
				offset = document.getText().indexOf(s);
				if (offset == -1) {
					return -1; // 未找到内容
				}
			}
			int lineNumber = document.getLineNumber(offset);
			System.out.println("lineNumber = " + lineNumber);
			return lineNumber;
		}
	}

	public static int getObjectLineNumber(AnActionEvent event, JsonTreeNode node) {
		PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);
		if (psiFile == null) {
			return -1; // 如果文件为 null，返回 -1
		}
		VirtualFile virtualFile = psiFile.getVirtualFile();
		if (virtualFile == null || !virtualFile.exists()) {
			return -1; // 如果文件不存在，返回 -1
		}
		FileDocumentManager documentManager = FileDocumentManager.getInstance();
		Document document = documentManager.getDocument(virtualFile);
		String s = node.getUserObject() + ":";
		int offset = document.getText().indexOf(s);
		if (offset == -1) {
			s = node.getUserObject() + ": \"" + node.getValue() + "\"";
			offset = document.getText().indexOf(s);
			if (offset == -1) {
				return -1; // 未找到内容
			}
		}
		int lineNumber = document.getLineNumber(offset);
		System.out.println("lineNumber = " + lineNumber);
		return lineNumber;
	}

}
