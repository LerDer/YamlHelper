package com.wd.yh.util;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.ui.TextTransferable;
import com.wd.yh.constant.FileTypes;

public class PlatformUtil {
    private static final Logger LOG = Logger.getInstance(PlatformUtil.class);

    public static FileType getFileType(FileTypes fileTypes) {
        return getFileType(fileTypes, PlainTextFileType.INSTANCE);
    }

    public static FileType getFileType(FileTypes fileTypes, FileType defaultFileType) {
        Class<?> clz = JsonAssistantUtil.getClassByName(fileTypes.getFileTypeQualifiedName());

        if (clz != null) {
            Object instance = JsonAssistantUtil.readStaticFinalFieldValue(clz, fileTypes.getFileTypeInstanceFieldName());
            if (instance instanceof FileType) {
                return (FileType) instance;
            }
        }

        return defaultFileType;
    }

    /**
     * 设置剪贴板内容
     *
     * @param content 要设置到剪贴板中的字符串内容
     */
    public static void setClipboard(String content) {
        CopyPasteManager.getInstance().setContents(new TextTransferable(content));
        // CopyPasteManager.getInstance().setContents(new SimpleTransferable(content, DataFlavor.stringFlavor));
    }
}
