package com.andy.jaa.andyfec.delegates.web;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.andy.jaa.andyfec.delegates.IPageLoadListener;
import com.andy.jaa.andyfec.delegates.web.client.WebViewClientImpl;
import com.andy.jaa.andyfec.delegates.web.route.RouteKeys;
import com.andy.jaa.andyfec.delegates.web.route.Router;

/**
 * Created by quanxi on 2018/4/3.
 */

public class WebDelegateIml extends WebDelegate {
    private IPageLoadListener mPageLoadListener;

    public static WebDelegateIml create(String url){
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebDelegateIml delegateIml = new WebDelegateIml();
        delegateIml.setArguments(args);
        return delegateIml;
    }

    public void setPageLoadListener(IPageLoadListener loadListener){
        this.mPageLoadListener = loadListener;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl()!=null){
            //用原生的方式模拟
            Router.getInstance().loadPage(this,getUrl());
        }
    }

    @Override
    public IWebViewInitializer setWebViewInitializer() {
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageListener(mPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClient();
    }
}
