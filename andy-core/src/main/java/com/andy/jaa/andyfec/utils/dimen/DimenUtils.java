package com.andy.jaa.andyfec.utils.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.andy.jaa.andyfec.app.Latte;

/**
 * Created by quanxi on 2018/3/19.
 */

public class DimenUtils {
    public static int getScreenWidth(){
        Resources resources = Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
    public static int getScreenHeight(){
        Resources resources = Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
