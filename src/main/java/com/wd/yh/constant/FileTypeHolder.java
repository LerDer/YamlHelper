package com.wd.yh.constant;

import com.intellij.openapi.fileTypes.FileType;
import com.wd.yh.util.PlatformUtil;

public interface FileTypeHolder {

    FileType JSON = PlatformUtil.getFileType(FileTypes.JSON);

    FileType JSON5 = PlatformUtil.getFileType(FileTypes.JSON5);

    FileType XML = PlatformUtil.getFileType(FileTypes.XML);

    FileType YAML = PlatformUtil.getFileType(FileTypes.YAML);

    FileType TOML = PlatformUtil.getFileType(FileTypes.TOML);

    FileType PROPERTIES = PlatformUtil.getFileType(FileTypes.PROPERTIES);

}
