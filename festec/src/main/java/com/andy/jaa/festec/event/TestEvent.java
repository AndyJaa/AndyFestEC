package com.andy.jaa.festec.event;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebView;
import android.widget.Toast;

import com.andy.jaa.andyfec.delegates.web.event.Event;

/**
 * Created by quanxi on 2018/4/3.
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(),getAction(),Toast.LENGTH_SHORT).show();
        if (getAction().equalsIgnoreCase("test")){
            final WebView mWebView = getWebView();
            mWebView.post(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void run() {
                    mWebView.evaluateJavascript("nativeCall();",null);
                }
            });
        }
        return null;
    }
}
