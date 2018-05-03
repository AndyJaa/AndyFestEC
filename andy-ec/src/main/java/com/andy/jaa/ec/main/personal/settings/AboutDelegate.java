package com.andy.jaa.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.net.RestClient;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.R2;

import butterknife.BindView;

/**
 * Created by quanxi on 2018/5/3.
 */

public class AboutDelegate extends LatteDelegate {
    @BindView(R2.id.tv_info)
    AppCompatTextView tv_info;
    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url("about.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String info = JSON.parseObject(response).getString("data");
                        tv_info.setText(info);
                    }
                })
                .build()
                .get();
    }
}
