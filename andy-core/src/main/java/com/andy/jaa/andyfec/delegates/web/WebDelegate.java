package com.andy.jaa.andyfec.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.andy.jaa.andyfec.app.ConfigKeys;
import com.andy.jaa.andyfec.app.Latte;
import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by quanxi on 2018/3/29.
 */

public abstract class WebDelegate extends LatteDelegate implements IWebViewInitializer{
    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl =null;
    private boolean mIsWebviewAbailable = false;
    private LatteDelegate mTopDelegate = null;

    public abstract IWebViewInitializer setWebViewInitializer();

    public WebDelegate(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle!=null){
            mUrl = bundle.getString(RouteKeys.URL.name());
            initWebView();
        }
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView(){
        if (mWebView!=null){
            mWebView.removeAllViews();
            mWebView.destroy();
        }else{
            final IWebViewInitializer initializer = setWebViewInitializer();
            if (initializer!=null){
                final WeakReference<WebView> weakReference =
                        new WeakReference<WebView>(new WebView(getContext()),WEB_VIEW_QUEUE);
                mWebView = weakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                final String name = Latte.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE);
                mWebView.addJavascriptInterface(LatteWebInterface.create(this),name);
                mIsWebviewAbailable = true;
            }else{
                throw new NullPointerException("Initializer IS NULL");
            }
        }
    }

    public void setTopDelegate(LatteDelegate delegate){
        this.mTopDelegate = delegate;
    }

    public LatteDelegate getTopDelegate(){
        if (mTopDelegate==null){
            mTopDelegate = this;
        }
        return mTopDelegate;
    }

    public WebView getWebView(){
        if (mWebView==null){
            throw new NullPointerException("WebView is null");
        }
        return mIsWebviewAbailable?mWebView:null;
    }

    public String getUrl(){
        if (mUrl==null){
            throw new NullPointerException("URL is null");
        }
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView!=null){
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView!=null){
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebviewAbailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView!=null){
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
