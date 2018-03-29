package com.andy.jaa.andyfec.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by quanxi on 2018/3/29.
 */

public abstract class WebDelegate extends LatteDelegate {
    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl =null;
    private boolean mIsWebviewAbailable = false;

    public abstract IWebViewInitializer setWebViewInitializer();

    public WebDelegate(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle!=null){
            mUrl = bundle.getString(RouteKeys.URL.name());
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
                mWebView.addJavascriptInterface(LatteWebInterface.create(this),"latte");
                mIsWebviewAbailable = true;
            }else{
                throw new NullPointerException("Initializer IS NULL");
            }
        }
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
