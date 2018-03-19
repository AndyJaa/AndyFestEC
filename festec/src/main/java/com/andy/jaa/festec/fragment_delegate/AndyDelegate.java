package com.andy.jaa.festec.fragment_delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.andy.jaa.andyfec.net.callback.IError;
import com.andy.jaa.andyfec.net.callback.IFailure;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.net.RestClient;
import com.andy.jaa.festec.R;

import okhttp3.ResponseBody;

/**
 * Created by quanxi on 2018/3/11.
 */

public class AndyDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_andy;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    public void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getContext())
                .param("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void failure() {
                        Log.e(AndyDelegate.class.getSimpleName()+">>>","failure");
                    }
                })
                .error(new IError() {
                    @Override
                    public void error(int code, String msg) {
                        Log.e(AndyDelegate.class.getSimpleName()+">>>","error"+msg);
                    }
                }).build().get();
    }
}
