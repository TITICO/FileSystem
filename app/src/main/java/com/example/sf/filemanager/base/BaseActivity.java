package com.example.sf.filemanager.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by 89003337 on 2017/4/11.
 */

public abstract class BaseActivity extends RxAppCompatActivity{
    public static String accentSkin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init();
    }

    protected abstract void init();

    protected abstract int getLayoutId();

    protected void replaceFragment(int containerId, Fragment fragment,String tag){
        FragmentManager fm=this.getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(containerId,fragment,tag);
        ft.commit();
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
