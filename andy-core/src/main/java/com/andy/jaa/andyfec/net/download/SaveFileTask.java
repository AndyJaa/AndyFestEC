package com.andy.jaa.andyfec.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.andy.jaa.andyfec.app.Latte;
import com.andy.jaa.andyfec.net.callback.IRequest;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.andyfec.utils.FileUtils;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by quanxi on 2018/3/19.
 */

public class SaveFileTask extends AsyncTask<Object,Void,File> {
    private IRequest request;
    private ISuccess success;

    public SaveFileTask(IRequest request, ISuccess success) {
        this.request = request;
        this.success = success;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        String name = (String) params[2];
        ResponseBody body = (ResponseBody) params[3];
        InputStream is = body.byteStream();
        if (TextUtils.isEmpty(downloadDir)){
            downloadDir = "download_andy";
        }
        if (TextUtils.isEmpty(extension)){
            extension = "";
        }
        if (TextUtils.isEmpty(name)){
            return FileUtils.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }else {
            return FileUtils.writeToDisk(is,downloadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (success!=null){
            success.onSuccess(file.getPath());
        }
        if (request!=null){
            request.onRequestEnd();
        }
        autoInstallAPK(file);
    }

    private void autoInstallAPK(File file){
        if (FileUtils.getExtension(file.getPath()).equalsIgnoreCase("apk")){
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(intent);
        }
    }
}
