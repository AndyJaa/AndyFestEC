package com.andy.jaa.ec.main.personal.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.ec.R;

/**
 * Created by quanxi on 2018/4/16.
 */

public class NameDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
