package com.andy.jaa.andyfec.net;

import android.content.Context;

import com.andy.jaa.andyfec.net.callback.IError;
import com.andy.jaa.andyfec.net.callback.IFailure;
import com.andy.jaa.andyfec.net.callback.IRequest;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.andyfec.net.callback.RequestCallbacks;
import com.andy.jaa.andyfec.net.download.DownloadHandler;
import com.andy.jaa.andyfec.ui.LatteLoader;
import com.andy.jaa.andyfec.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    private final RequestBody BODY;
    private final File FILE;
    private final LoaderStyle LOADERSTYLE;
    private final Context context;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public RestClient(String url,WeakHashMap<String,Object> param,
                      String downloadDir,String extension,
                      String name,IRequest request,
                      ISuccess success, IFailure failure,
                      IError error, RequestBody body,
                      File file,Context context,
                      LoaderStyle style) {
        this.URL = url;
        PARAM.putAll(param);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.IREQUEST = request;
        this.ISUCCESS = success;
        this.IFAILURE = failure;
        this.IERROR = error;
        this.BODY = body;
        this.FILE = file;
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
            case POST_RAW:
                call = service.post_raw(URL,BODY);
                break;
            case PUT:
                call = service.put(URL,PARAM);
                break;
            case PUT_RAW:
                call = service.put_raw(URL,BODY);
                break;
            case DELETE:
                call = service.delete(URL,PARAM);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create
                        (MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = service.upload(URL,body);
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
        if (BODY==null) {
            request(HttpMethod.POST);
        }else {
            if (!PARAM.isEmpty()){
                throw new RuntimeException("param must be null !");
            }
            request(HttpMethod.POST_RAW);
        }
    }
    public final void put(){
        if (BODY==null) {
            request(HttpMethod.PUT);
        }else {
            if (!PARAM.isEmpty()){
                throw new RuntimeException("param must be null !");
            }
            request(HttpMethod.PUT_RAW);
        }
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public final void upload(){
        request(HttpMethod.UPLOAD);
    }

    public final void download(){
        new DownloadHandler(URL,IREQUEST,ISUCCESS,IFAILURE,IERROR,DOWNLOAD_DIR,EXTENSION,NAME).handleDownload();
    }
}
