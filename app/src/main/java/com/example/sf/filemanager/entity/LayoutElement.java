package com.example.sf.filemanager.entity;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.sf.filemanager.utils.provider.Futils;

import java.util.Calendar;

/**
 * Created by 89003337 on 2017/4/21.
 */

public class LayoutElement implements Parcelable {
    private static final String CURRENT_YEAR = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    public LayoutElement(Drawable bitmapDrawable, String title, String des, String permissions,
                         String symlink, String size, boolean isDirectory, String date, long longSize,
                         boolean header) {
        this.bitmapDrawable = bitmapDrawable;
        this.title = title;
        this.des = des;
        this.permissions = permissions;
        this.symlink = symlink;
        this.size = size;
        this.isDirectory = isDirectory;
        this.longSize = longSize;
        this.header = header;
        if (!date.trim().equals("")) {
            this.date = Long.parseLong(date);
            this.date1 = Futils.getdate(this.date, CURRENT_YEAR);
        }
    }

    public Drawable getBitmapDrawable() {
        return bitmapDrawable;
    }

    public String getTitle() {
        return title;
    }

    public String getDes() {
        return des;
    }

    public String getPermissions() {
        return permissions;
    }

    public String getSymlink() {
        return symlink;
    }

    public String getSize() {
        return size;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public long getDate() {
        return date;
    }

    public long getLongSize() {
        return longSize;
    }

    public String getDate1() {
        return date1;
    }

    public boolean isHeader() {
        return header;
    }

    public void setBitmapDrawable(Drawable bitmapDrawable) {
        this.bitmapDrawable = bitmapDrawable;
    }

    private Drawable bitmapDrawable;
    private String title;
    private String des;
    private String permissions;
    private String symlink;
    private String size;
    private boolean isDirectory;
    private long date = 0, longSize = 0;
    private String date1 = "";
    private boolean header;

    public void setMode(OpenMode mode) {
        this.mode = mode;
    }

    public OpenMode getMode() {
        return mode;
    }

    private OpenMode mode = OpenMode.FILE;

    protected LayoutElement(Parcel in) {
        title = in.readString();
        des = in.readString();
        permissions = in.readString();
        symlink = in.readString();
        size = in.readString();
        int j = in.readInt();
        date = in.readLong();
        int k = in.readInt();
        header = j == 0 ? true : false;
        isDirectory = k == 0 ? true : false;
        date1 = in.readString();
        longSize = in.readLong();
    }

    public static final Creator<LayoutElement> CREATOR = new Creator<LayoutElement>() {
        @Override
        public LayoutElement createFromParcel(Parcel in) {
            return new LayoutElement(in);
        }

        @Override
        public LayoutElement[] newArray(int size) {
            return new LayoutElement[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(des);
        dest.writeString(permissions);
        dest.writeString(symlink);
        dest.writeString(size);
        dest.writeInt(header?0:1);
        dest.writeLong(date);
        dest.writeInt(isDirectory?0:1);
        dest.writeString(date1);
        dest.writeLong(longSize);
    }
}
