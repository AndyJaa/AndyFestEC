package com.andy.jaa.andyfec.ui.recycler;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Created by quanxi on 2018/3/28.
 */

public class DividerLookupImpl implements DividerItemDecoration.DividerLookup {
    private final int COLOR;
    private final int SIZE;
    public DividerLookupImpl(int color,int size){
        this.COLOR = color;
        this.SIZE = size;
    }
    @Override
    public Divider getVerticalDivider(int position) {
        return new Divider.Builder().color(COLOR).size(SIZE).build();
    }

    @Override
    public Divider getHorizontalDivider(int position) {
        return new Divider.Builder().color(COLOR).size(SIZE).build();
    }
}
