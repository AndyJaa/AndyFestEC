package com.andy.jaa.andyfec.delegates.web.event;

import android.util.Log;

/**
 * Created by quanxi on 2018/4/3.
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        Log.e(UndefineEvent.class.getSimpleName()+">>>","undefine");
        return null;
    }
}
