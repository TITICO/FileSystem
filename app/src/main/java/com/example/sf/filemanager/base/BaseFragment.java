package com.example.sf.filemanager.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * Created by 89003337 on 2017/4/11.
 */

public abstract class BaseFragment extends RxFragment{
    private View mRootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView==null){
            mRootView=inflater.inflate(attachLayoutId(),null);
            ButterKnife.bind(this,mRootView);
            init();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    protected abstract int attachLayoutId();

    protected abstract void init();
}
