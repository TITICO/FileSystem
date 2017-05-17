package com.example.sf.filemanager.injections.component;

import com.example.sf.filemanager.fragments.AppFragment;
import com.example.sf.filemanager.injections.module.APPModule;

import dagger.Component;

/**
 * Created by 89003337 on 2017/4/17.
 */
@Component(modules ={APPModule.class})
public interface APPComponent {
    void inject(AppFragment fragment);
}
