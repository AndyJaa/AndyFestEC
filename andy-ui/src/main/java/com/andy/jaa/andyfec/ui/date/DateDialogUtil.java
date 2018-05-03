package com.andy.jaa.andyfec.ui.date;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by quanxi on 2018/4/16.
 */

public class DateDialogUtil {
    public interface IDateListenr {
        void onDateChange(String date);
    }

    private IDateListenr mDateListenr = null;

    public void setDateListenr(IDateListenr listenr) {
        this.mDateListenr = listenr;
    }

    public void showDialog(Context context) {
        final LinearLayout layout = new LinearLayout(context);
        final DatePicker picker = new DatePicker(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        picker.setLayoutParams(params);
        picker.init(1990, 1, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                final Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                final String date = sdf.format(calendar.getTime());
                if (mDateListenr != null) {
                    mDateListenr.onDateChange(date);
                }
            }
        });
        layout.addView(picker);
        new AlertDialog.Builder(context).setView(layout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
    }
}
