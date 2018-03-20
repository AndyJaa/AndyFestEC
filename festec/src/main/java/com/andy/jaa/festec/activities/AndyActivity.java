package com.andy.jaa.festec.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.andy.jaa.andyfec.activities.ProxyActivity;
import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.ec.launcher.LauncherDelegate;
import com.andy.jaa.ec.launcher.LauncherScrollDelegate;
import com.andy.jaa.ec.sign.SignUpDelegate;
import com.andy.jaa.festec.fragment_delegate.AndyDelegate;

public class AndyActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
//        return new LauncherDelegate();
        return new SignUpDelegate();
    }
}
