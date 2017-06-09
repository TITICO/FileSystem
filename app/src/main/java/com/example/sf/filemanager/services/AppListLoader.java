package com.example.sf.filemanager.services;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.AsyncTaskLoader;
import android.text.format.Formatter;

import com.example.sf.filemanager.R;
import com.example.sf.filemanager.entity.LayoutElement;
import com.example.sf.filemanager.receiver.AppReceiver;
import com.example.sf.filemanager.utils.provider.FileListSorter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 89003337 on 2017/4/20.
 */

public class AppListLoader extends AsyncTaskLoader<List<LayoutElement>> {
    private final PackageManager packageManager;
    private int sortBy;
    private int asc;
    private List<LayoutElement> mApps;
    private AppReceiver receiver;

    public AppListLoader(Context context,int sortBy,int asc) {
        super(context);
        this.sortBy=sortBy;
        this.asc=asc;
        packageManager = getContext().getPackageManager();
    }

    private   List<LayoutElement> loadAppList(Context context){
        List<ApplicationInfo> apps = packageManager.getInstalledApplications(
                PackageManager.MATCH_UNINSTALLED_PACKAGES |
                        PackageManager.MATCH_DISABLED_UNTIL_USED_COMPONENTS);
        if (apps == null)
            apps = new ArrayList<>();
        mApps = new ArrayList<>(apps.size());
        for (ApplicationInfo object : apps) {
            File sourceDir = new File(object.sourceDir);
            String label = object.loadLabel(packageManager).toString();
            PackageInfo info;
            try {
                info = packageManager.getPackageInfo(object.packageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                info = null;
            }

            Drawable drawable=object.loadIcon(packageManager);
            if(drawable==null){
                drawable=new BitmapDrawable(context.getResources(),
                        BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_doc_apk_grid));
            }
            mApps.add(new LayoutElement(drawable,
                    label == null ? object.packageName : label, object.sourceDir,
                    object.packageName, object.flags + "_" + (info!=null ? info.versionName:""),
                    Formatter.formatFileSize(getContext(), sourceDir.length()),
                     false, sourceDir.lastModified()+"", sourceDir.length(),false));
        }
        Collections.sort(mApps, new FileListSorter(0, sortBy, asc, false));
        return mApps;
    }

    @Override
    public List<LayoutElement> loadInBackground() {
        return loadAppList(getContext());
    }

    @Override
    protected void onStartLoading() {
        if(mApps!=null){
            deliverResult(mApps);
        }
        if(receiver==null){
            receiver=new AppReceiver(this);
        }
        if(takeContentChanged()||mApps==null){
            forceLoad();
        }
    }

    @Override
    protected void onReset() {
        stopLoading();
        if(mApps!=null){
            releaseRes(mApps);
        }
        if(receiver!=null){
            getContext().unregisterReceiver(receiver);
            receiver=null;
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void deliverResult(List<LayoutElement> data) {
        if(isReset()){
            if(mApps!=null){
                releaseRes(mApps);
                return;
            }
        }
        List<LayoutElement> olderApps=mApps;
        mApps=data;
        if(isStarted()) {
            super.deliverResult(mApps);
        }
        if(olderApps!=null&&mApps!=olderApps){
            releaseRes(olderApps);
        }
    }

    @Override
    public void onCanceled(List<LayoutElement> data) {
        super.onCanceled(data);
        releaseRes(data);
    }

    private void releaseRes(List<LayoutElement> mApps){
        mApps=null;
    }
}
