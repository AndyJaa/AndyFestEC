package com.andy.jaa.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.andy.jaa.andyfec.delegates.bottom.BottomItemDelegate;
import com.andy.jaa.andyfec.delegates.web.WebDelegateIml;
import com.andy.jaa.ec.R;

/**
 * Created by quanxi on 2018/3/29.
 */

public class DiscoverDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateIml delegateIml = WebDelegateIml.create("index.html");
        delegateIml.setTopDelegate(this.getParentDelegate());
        loadRootFragment(R.id.web_discovery_container,delegateIml);
    }
}
