package com.andy.jaa.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.andy.jaa.andyfec.delegates.bottom.BottomItemDelegate;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.main.sort.content.ContentDelegate;
import com.andy.jaa.ec.main.sort.list.VerticalListDelegate;

/**
 * Created by quanxi on 2018/3/26.
 */

public class SortDelegate extends BottomItemDelegate  {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void post(Runnable runnable) {
        getSupportDelegate().post(runnable);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container,listDelegate);
        getSupportDelegate().loadRootFragment
                (R.id.sort_content_container, ContentDelegate.getInstance(1),true,true);
    }
}
