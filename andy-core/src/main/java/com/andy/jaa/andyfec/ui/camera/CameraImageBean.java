package com.andy.jaa.andyfec.ui.camera;

import android.net.Uri;

/**
 * Created by quanxi on 2018/4/16.
 * 存储一些中间值
 */

public final class CameraImageBean {
    private Uri mPath = null;

    private CameraImageBean(){}

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
