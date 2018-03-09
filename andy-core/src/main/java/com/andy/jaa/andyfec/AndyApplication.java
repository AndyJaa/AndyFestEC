package com.andy.jaa.andyfec;

import android.app.Application;

/**
 * Created by quanxi on 2018/3/9.
 */

public class AndyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).configure();
    }
}
