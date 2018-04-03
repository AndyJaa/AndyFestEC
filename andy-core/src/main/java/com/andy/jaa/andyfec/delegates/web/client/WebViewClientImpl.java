package com.andy.jaa.andyfec.delegates.web.client;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.andy.jaa.andyfec.app.Latte;
import com.andy.jaa.andyfec.delegates.IPageLoadListener;
import com.andy.jaa.andyfec.delegates.web.WebDelegate;
import com.andy.jaa.andyfec.delegates.web.route.Router;
import com.andy.jaa.andyfec.ui.loader.LatteLoader;

/**
 * Created by quanxi on 2018/4/3.
 */

public class WebViewClientImpl extends WebViewClient {
    private final WebDelegate DELEGATE;
    private IPageLoadListener mIpageListener;

    public void setPageListener(IPageLoadListener listener){
        this.mIpageListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate){
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return Router.getInstance().handleWebUrl(DELEGATE,url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIpageListener!=null){
            mIpageListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIpageListener!=null){
            mIpageListener.onLoadEnd();
        }
        LatteLoader.stopLoading();
    }
}
