package com.andy.jaa.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.andy.jaa.andyfec.app.AccountManager;
import com.andy.jaa.andyfec.app.IUserChecker;
import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.ui.launcher.ILauncherListener;
import com.andy.jaa.andyfec.ui.launcher.OnLauncherFinishTag;
import com.andy.jaa.andyfec.ui.launcher.ScrollLauncherTag;
import com.andy.jaa.andyfec.utils.storage.LattePreference;
import com.andy.jaa.andyfec.utils.timer.BaseTimerTask;
import com.andy.jaa.andyfec.utils.timer.ITimerListener;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by quanxi on 2018/3/20.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    @BindView(R2.id.tv_timer)
    AppCompatTextView tv_timer;

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener;

    @OnClick(R2.id.tv_timer)
    void onClickTimer(){
        if (mTimer!=null){
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener)activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    //判断是否显示滑动启动页
    public void checkIsShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else{
            //检查用户是否登录了app
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener!=null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener!=null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tv_timer!=null){
                    tv_timer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if (mCount<0){
                        if (mTimer!=null){
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
