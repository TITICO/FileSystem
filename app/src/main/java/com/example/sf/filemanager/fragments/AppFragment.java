package com.example.sf.filemanager.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sf.filemanager.R;
import com.example.sf.filemanager.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 89003337 on 2017/4/17.
 */

public class AppFragment extends BaseFragment {
    @Bind(R.id.recycleView)
    RecyclerView recycleView;

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_app;
    }

    @Override
    protected void init() {
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
