package com.andy.jaa.andyfec.utils.timer;

import java.util.TimerTask;

/**
 * Created by quanxi on 2018/3/20.
 */

public class BaseTimerTask extends TimerTask {
    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}
