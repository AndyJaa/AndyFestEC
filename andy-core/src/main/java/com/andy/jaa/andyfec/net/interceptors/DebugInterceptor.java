package com.andy.jaa.andyfec.net.interceptors;

import android.support.annotation.RawRes;

import com.andy.jaa.andyfec.utils.file.FileUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by quanxi on 2018/3/20.
 */

public class DebugInterceptor extends BaseInterceptor {
    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String url, int rawId) {
        this.DEBUG_URL = url;
        this.DEBUG_RAW_ID = rawId;
    }

    @Override
    public Response intercept(Chain chain) {
        String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)){
            return debugResponse(chain,DEBUG_RAW_ID);
        }
        try {
            return chain.proceed(chain.request());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Response getResponse(Chain chain,String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("OK!")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain,@RawRes int rawId){
        final String json = FileUtils.getRawFile(rawId);
        return getResponse(chain,json);
    }
}
