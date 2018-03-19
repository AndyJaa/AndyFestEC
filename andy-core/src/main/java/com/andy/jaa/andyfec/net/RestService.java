package com.andy.jaa.andyfec.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by quanxi on 2018/3/11.
 */

public interface RestService {
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String,Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String,Object> params);

    @POST
    Call<String> post_raw(@Url String url, @Body RequestBody params);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String,Object> params);

    @PUT
    Call<String> put_raw(@Url String url, @Body RequestBody params);

    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String,Object> params);

    //Streaming目的是防止下载文件过大，一次性放在内存，导致内存溢出的问题。
    //同样需要开启子线程去写入文件
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String,Object> params);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);
}
