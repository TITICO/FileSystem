package com.example.sf.filemanager.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sf.filemanager.R;
import com.example.sf.filemanager.adapter.AppAdapter;
import com.example.sf.filemanager.base.BaseFragment;
import com.example.sf.filemanager.entity.LayoutElement;
import com.example.sf.filemanager.services.AppListLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 89003337 on 2017/4/17.
 */

public class AppFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<List<LayoutElement>>{
    @Bind(R.id.recycleView)
    RecyclerView recycleView;
    AppAdapter appAdapter;
    private int sortby;
    private int asc;
    private SharedPreferences Sp;
    private List<LayoutElement> mApps=new ArrayList<>();

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_app;
    }

    @Override
    protected void init() {
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        appAdapter=new AppAdapter(mApps,getContext());
        recycleView.setAdapter(appAdapter);
        Sp=PreferenceManager.getDefaultSharedPreferences(getActivity());
        getSortModes();
        getLoaderManager().initLoader(1,null,this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void getSortModes() {
        int t = Integer.parseInt(Sp.getString("sortbyApps", "0"));
        if (t <= 2) {
            sortby = t;
            asc = 1;
        } else if (t > 2) {
            asc = -1;
            sortby = t - 3;
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new AppListLoader(getContext(),sortby,asc);
    }

    @Override
    public void onLoadFinished(Loader<List<LayoutElement>> loader, List<LayoutElement> data) {
        mApps.clear();
        mApps.addAll(data);
        appAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader loader) {
        mApps.clear();
        appAdapter.notifyDataSetChanged();
    }
}
