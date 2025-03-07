package com.wd.yh.complete;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiFile;
import com.intellij.ui.JBColor;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * 代码补全
 */
public class YamlCompletionContributor extends CompletionContributor {

    public YamlCompletionContributor() {
    }

    @Override
    public void fillCompletionVariants(@NotNull CompletionParameters parameters, @NotNull CompletionResultSet result) {
        if (parameters.getCompletionType() != CompletionType.BASIC) {
            return;
        }

        // 检查当前文件类型是否为YAML
        PsiFile psiFile = parameters.getOriginalFile();
        if (!"YAML".equalsIgnoreCase(psiFile.getFileType().getName())) {
            return;
        }
        List<String> keywords = YamlKeywordGenerate.getKeywords();
        for (String keyword : keywords) {
            String[] split = keyword.split("-#-");
            result.addElement(LookupElementBuilder.create(split[0])
                    .withCaseSensitivity(true)
                    .withItemTextItalic(true)
                    //一级提示文本颜色
                    .withItemTextForeground(JBColor.BLUE)
                    .withTypeText(split[1]));
        }
    }
}
