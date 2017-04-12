package com.example.sf.filemanager.activitys;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.example.sf.filemanager.R;
import com.example.sf.filemanager.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 89003337 on 2017/4/12.
 */

public class SplashActivity extends BaseActivity {

    @Bind(R.id.skip)
    TextView skip;
    private final int TIME = 5;
    private int mTime = TIME;

    @Override
    protected void init() {
        Log.e("init","init");
        skip.setText("跳过 "+TIME);
        countDown();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    private void skip() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        finish();
    }

    @OnClick(R.id.skip)
    public void onViewClicked() {
        Log.e("onViewClicked","onViewClicked");
        skip();
    }

    private void countDown() {
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                mTime--;
                skip.setText("跳过 " + mTime);

            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError","");
                skip();
            }

            @Override
            public void onComplete() {
                Log.e("onComplete","");
                skip();
            }
        };
        Observable.interval(1, TimeUnit.SECONDS).take(TIME).compose(this.<Long>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


    }

    @Override
    public void onBackPressed() {
        return;
    }
}
