package com.andy.jaa.andyfec.net.callback;

import android.os.Handler;

import com.andy.jaa.andyfec.ui.loader.LatteLoader;
import com.andy.jaa.andyfec.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by quanxi on 2018/3/12.
 */

public class RequestCallbacks implements Callback<String> {
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final LoaderStyle LOADERSTYLE;
    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error,LoaderStyle style) {
        this.IREQUEST = request;
        this.ISUCCESS = success;
        this.IFAILURE = failure;
        this.IERROR = error;
        this.LOADERSTYLE = style;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            //判断call是否执行
            if (call.isExecuted()){
                if (ISUCCESS!=null){
                    ISUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if (IERROR!=null){
                IERROR.error(response.code(),response.message());
            }
        }
        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (IFAILURE!=null){
            IFAILURE.failure();
        }
        if (IREQUEST!=null){
            IREQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading(){
        if (LOADERSTYLE!=null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);
        }
    }
}
