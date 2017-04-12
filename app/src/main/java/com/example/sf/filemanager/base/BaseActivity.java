package com.example.sf.filemanager.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by 89003337 on 2017/4/11.
 */

public abstract class BaseActivity extends RxAppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init();
    }

    protected abstract void init();

    protected abstract int getLayoutId();

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
