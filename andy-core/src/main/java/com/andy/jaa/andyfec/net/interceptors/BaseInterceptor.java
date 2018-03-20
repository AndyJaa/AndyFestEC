package com.andy.jaa.andyfec.net.interceptors;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by quanxi on 2018/3/20.
 */

public abstract class BaseInterceptor implements Interceptor {
    @Override
    public abstract Response intercept(Chain chain);

    //LinkedHashMap是有序的
    //Get请求 url上的参数
    protected LinkedHashMap<String,String> getUrlParameters(Chain chain){
        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        final LinkedHashMap<String,String> params = new LinkedHashMap<>();
        for (int i=0;i<size;i++){
            params.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return params;
    }

    protected String getUrlParameters(Chain chain,String key){
        Request request = chain.request();
        return request.url().queryParameter(key);
    }

    protected LinkedHashMap<String,String> getBodyParameters(Chain chain){
        FormBody body = (FormBody) chain.request().body();
        int size = body.size();
        final LinkedHashMap<String,String> params = new LinkedHashMap<>();
        for (int i=0;i<size;i++){
            params.put(body.name(i),body.value(i));
        }
        return params;
    }

    protected String getBodyParameters(Chain chain,String key){
        return getBodyParameters(chain).get(key);
    }
}
