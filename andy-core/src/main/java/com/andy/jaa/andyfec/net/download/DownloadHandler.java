package com.andy.jaa.andyfec.net.download;

import android.os.AsyncTask;

import com.andy.jaa.andyfec.net.RestCreator;
import com.andy.jaa.andyfec.net.callback.IError;
import com.andy.jaa.andyfec.net.callback.IFailure;
import com.andy.jaa.andyfec.net.callback.IRequest;
import com.andy.jaa.andyfec.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by quanxi on 2018/3/19.
 */

public class DownloadHandler {
    private final String mUrl;
    private static final WeakHashMap<String,Object> PARAM = RestCreator.getParams();
    private final IRequest mRequest;
    private final ISuccess mSuccess;
    private final IFailure mFailure;
    private final IError mError;
    private final String mDownloadDir;
    private final String mExtension;
    private final String mName;

    public DownloadHandler(String mUrl, IRequest mRequest,
                           ISuccess mSuccess, IFailure mFailure,
                           IError mError, String mDownloadDir,
                           String mExtension, String mName) {
        this.mUrl = mUrl;
        this.mRequest = mRequest;
        this.mSuccess = mSuccess;
        this.mFailure = mFailure;
        this.mError = mError;
        this.mDownloadDir = mDownloadDir;
        this.mExtension = mExtension;
        this.mName = mName;
    }

    public final void handleDownload(){
        if (mRequest!=null){
            mRequest.onRequestStart();
        }
        RestCreator.getRestService().download(mUrl,PARAM)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody body = response.body();
                            final SaveFileTask task = new SaveFileTask(mRequest, mSuccess);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mDownloadDir, mExtension, mName, body);
                            //一定要判断，否则文件下载不全
                            if (task.isCancelled()) {
                                if (mRequest != null) {
                                    mRequest.onRequestEnd();
                                }
                            }
                        }else {
                            if (mError!=null){
                                mError.error(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (mFailure!=null){
                            mFailure.failure();
                        }
                    }
                });
    }
}
