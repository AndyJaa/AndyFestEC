package com.andy.jaa.andyfec.ui.recycler;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by quanxi on 2018/3/28.
 */

public abstract class DataConverter {
    protected final ArrayList<com.andy.jaa.andyfec.ui.recycler.MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json){
        this.mJsonData = json;
        return this;
    }

    public String getJsonData(){
        if (TextUtils.isEmpty(mJsonData)){
            throw new NullPointerException("JsonData IS NULL");
        }
        return mJsonData;
    }
}
