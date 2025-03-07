package com.wd.yh.complete;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.wd.yh.gui.YamlGui;
import com.wd.yh.persistent.YamlPersistent;
import com.wd.yh.util.JsonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class YamlKeywordGenerate {

    public static List<String> getKeywords() {
        List<String> keys = new ArrayList<>();
        YamlPersistent instance = YamlPersistent.getInstance();
        String yamlFilePath = instance.getYamlFilePath();
        if (StrUtil.isNotBlank(yamlFilePath)) {
            JSONObject object = JsonUtil.toObject(YamlGui.getJson(yamlFilePath), JSONObject.class);
            for (Entry<String, Object> entry : object) {
                keys.add(entry.getKey() + "-#-" + entry.getValue());
            }
        }
        return keys;
    }
   
}