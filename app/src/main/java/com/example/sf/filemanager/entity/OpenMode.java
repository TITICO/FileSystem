package com.example.sf.filemanager.entity;

/**
 * Created by 89003337 on 2017/4/21.
 */

enum OpenMode {
    FILE,
    UNKNOW,
    SMB,
    CUSTOM,
    ROOT,
    OTG;

    public static OpenMode getOpenMode(int ordinal) {
        for (OpenMode openMode : OpenMode.values()) {
            if (openMode.ordinal()==ordinal) return openMode;
        }
        return null;
    }
}
