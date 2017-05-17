package com.example.sf.filemanager.utils.provider;

import java.text.SimpleDateFormat;

/**
 * Created by 89003337 on 2017/4/26.
 */

public class Futils {
    private static final SimpleDateFormat sSDF = new SimpleDateFormat("MMM dd, yyyy");
    public static String getdate(long f, String year) {
        String date = sSDF.format(f);
        if(date.substring(date.length()-4,date.length()).equals(year))
            date=date.substring(0,date.length()-6);
        return date;
    }
}
