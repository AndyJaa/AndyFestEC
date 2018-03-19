package com.andy.jaa.andyfec.net;

import android.content.Context;

import com.andy.jaa.andyfec.net.callback.IError;
import com.andy.jaa.andyfec.net.callback.IFailure;
import com.andy.jaa.andyfec.net.callback.IRequest;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.andyfec.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

/**
 * Created by quanxi on 2018/3/11.
 */

public class RestClientBuilder {
    private String mUrl;
    private static final WeakHashMap<String,Object> PARAM = RestCreator.getParams();
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private ResponseBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;

    RestClientBuilder(){}

    public final RestClientBuilder url(String mUrl){
        this.mUrl = mUrl;
        return this;
    }

    public final RestClientBuilder param(Map<String,Object> param){
        PARAM.putAll(param);
        return this;
    }

    public final RestClientBuilder param(String key,String value){
        PARAM.put(key,value);
        return this;
    }

    public final RestClientBuilder raw(String raw){
        this.mBody = ResponseBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder request(IRequest iRequest){
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError){
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder loader(Context context,LoaderStyle style){
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }


    public final RestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public RestClient build(){
        return new RestClient(mUrl,PARAM,mIRequest,mISuccess,mIFailure,mIError,mBody,mContext,mLoaderStyle);
    }

}
