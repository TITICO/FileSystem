package com.example.sf.filemanager.entity;

import android.graphics.drawable.Drawable;

/**
 * app
 * Created by 89003337 on 2017/4/17.
 */

public class AppBean {
    private String name;
    private String size;
    private Drawable icon;
    private String entryActivity;
    private String packageName;

    public String getEntryActivity() {
        return entryActivity;
    }

    public void setEntryActivity(String entryActivity) {
        this.entryActivity = entryActivity;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
