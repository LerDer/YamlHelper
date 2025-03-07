package com.wd.yh.wrapper;

import com.wd.yh.util.JsonUtil;

public interface JsonWrapper {

    boolean isObject();

    boolean isArray();

    default String toJsonString() {
        return JsonUtil.formatJson(this);
    }

}
