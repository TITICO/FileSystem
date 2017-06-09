package com.example.sf.filemanager.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.sf.filemanager.services.AppListLoader;

/**
 * Created by 89003337 on 2017/6/9.
 */

public class AppReceiver extends BroadcastReceiver{
    private AppListLoader mLoader;
    public AppReceiver(AppListLoader loader){
        this.mLoader=loader;
        IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addDataScheme("package");
        loader.getContext().registerReceiver(this, filter);

        // Register for events related to SD card installation
        IntentFilter sdcardFilter = new IntentFilter(
                Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE);
        sdcardFilter.addAction(Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE);
        loader.getContext().registerReceiver(this, sdcardFilter);
        mLoader.getContext().registerReceiver(this,filter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mLoader.takeContentChanged();
    }
}
