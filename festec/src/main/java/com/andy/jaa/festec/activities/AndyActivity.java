package com.andy.jaa.festec.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.andy.jaa.andyfec.activities.ProxyActivity;
import com.andy.jaa.andyfec.app.Latte;
import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.ui.launcher.ILauncherListener;
import com.andy.jaa.andyfec.ui.launcher.OnLauncherFinishTag;
import com.andy.jaa.ec.launcher.LauncherDelegate;
import com.andy.jaa.ec.launcher.LauncherScrollDelegate;
import com.andy.jaa.ec.main.EcBottomDelegate;
import com.andy.jaa.ec.sign.ISignListener;
import com.andy.jaa.ec.sign.SignInDelegate;
import com.andy.jaa.ec.sign.SignUpDelegate;
import com.andy.jaa.festec.fragment_delegate.AndyDelegate;

import cn.jpush.android.api.JPushInterface;
import qiu.niorgai.StatusBarCompat;

public class AndyActivity extends ProxyActivity implements ISignListener ,ILauncherListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this,true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
//        return new SignUpDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(AndyActivity.this,"登录成功了",Toast.LENGTH_SHORT).show();
        getSupportDelegate().startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(AndyActivity.this,"注册成功了",Toast.LENGTH_SHORT).show();
        getSupportDelegate().startWithPop(new SignInDelegate());
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case NOT_SIGNED:
//                Toast.makeText(AndyActivity.this,"启动结束，用户未登录",Toast.LENGTH_SHORT).show();
                getSupportDelegate().startWithPop(new SignUpDelegate());
                break;
            case SIGNED:
//                Toast.makeText(AndyActivity.this,"启动结束，用户已登录",Toast.LENGTH_SHORT).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
        }
    }

    @Override
    public void post(Runnable runnable) {
        getSupportDelegate().post(runnable);
    }
}
