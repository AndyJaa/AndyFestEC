package com.andy.jaa.andyfec.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * Created by quanxi on 2018/3/28.
 */
@AutoValue
public abstract class RgbValue {
    public abstract int red();
    public abstract int green();
    public abstract int blue();

    public static RgbValue create(int red,int green,int blue){
        return new AutoValue_RgbValue(red,green,blue);
    }
}
