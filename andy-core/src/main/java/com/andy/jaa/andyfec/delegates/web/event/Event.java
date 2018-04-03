package com.andy.jaa.andyfec.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.delegates.web.WebDelegate;

/**
 * Created by quanxi on 2018/4/3.
 */

public abstract class Event implements IEvent {
    private Context mContext;
    private String mAction;
    private WebDelegate mDelegate;
    private String mUrl;
    private WebView mWebView;

    public WebView getWebView(){
        return mDelegate.getWebView();
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    public void setmDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
