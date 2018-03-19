package com.andy.jaa.andyfec.net;

import android.content.Context;

import com.andy.jaa.andyfec.net.callback.IError;
import com.andy.jaa.andyfec.net.callback.IFailure;
import com.andy.jaa.andyfec.net.callback.IRequest;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.andyfec.net.callback.RequestCallbacks;
import com.andy.jaa.andyfec.ui.LatteLoader;
import com.andy.jaa.andyfec.ui.LoaderStyle;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by quanxi on 2018/3/11.
 */

public class RestClient {
    private final String URL;
    private static final WeakHashMap<String,Object> PARAM = RestCreator.getParams();
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final ResponseBody BODY;
    private final LoaderStyle LOADERSTYLE;
    private final Context context;

    public RestClient(String url,WeakHashMap<String,Object> param,
                      IRequest request,
                      ISuccess success, IFailure failure,
                      IError error, ResponseBody body,Context context,
                      LoaderStyle style) {
        this.URL = url;
        PARAM.putAll(param);
        this.IREQUEST = request;
        this.ISUCCESS = success;
        this.IFAILURE = failure;
        this.IERROR = error;
        this.BODY = body;
        this.context = context;
        this.LOADERSTYLE = style;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    public void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (IREQUEST !=null){
            IREQUEST.onRequestStart();
        }
        if (LOADERSTYLE!=null){
            LatteLoader.showLoading(context,LOADERSTYLE);
        }

        switch (method){
            case GET:
                call = service.get(URL,PARAM);
                break;
            case POST:
                call = service.post(URL,PARAM);
                break;
            case PUT:
                call = service.put(URL,PARAM);
                break;
            case DELETE:
                call = service.delete(URL,PARAM);
                break;
            default:
                break;
        }
        if (call!=null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(IREQUEST,ISUCCESS,IFAILURE,IERROR,LOADERSTYLE);
    }

    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
