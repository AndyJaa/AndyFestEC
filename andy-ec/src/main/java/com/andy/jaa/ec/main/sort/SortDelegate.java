package com.andy.jaa.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.andy.jaa.andyfec.delegates.bottom.BottomItemDelegate;
import com.andy.jaa.ec.R;

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
}
