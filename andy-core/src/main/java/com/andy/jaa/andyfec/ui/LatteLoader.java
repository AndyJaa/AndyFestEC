package com.andy.jaa.andyfec.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.andy.jaa.andyfec.R;
import com.andy.jaa.andyfec.app.Latte;
import com.andy.jaa.andyfec.utils.DimenUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by quanxi on 2018/3/19.
 */

public class LatteLoader {
    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;
    private static final ArrayList<AppCompatDialog> LOADERLIST = new ArrayList<>();
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }

    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView loadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(loadingIndicatorView);

        int deviceWidth = DimenUtils.getScreenWidth();
        int deviceHeight = DimenUtils.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERLIST.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER);
    }

    public static void stopLoading(){
        for (AppCompatDialog dialog : LOADERLIST){
            if (dialog!=null){
                if (dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }
}
