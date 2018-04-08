package com.andy.jaa.andyfec.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.delegates.web.WebDelegate;
import com.andy.jaa.andyfec.delegates.web.WebDelegateIml;

/**
 * Created by quanxi on 2018/4/3.
 */

public class Router {
    private Router(){}

    private static class Holder{
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance(){
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate delegate,String url){
        //如果是电话协议
        if (url.contains("tel:")){
            callPhone(delegate.getContext(),url);
            return true;
        }
        final LatteDelegate topDelegate = delegate.getTopDelegate();
        final WebDelegateIml delegateIml = WebDelegateIml.create(url);
        topDelegate.getSupportDelegate().start(delegateIml);
        return true;
    }

    private void loadWebUrl(WebView webView,String url){
        if (webView!=null){
            webView.loadUrl(url);
        }else{
            throw new NullPointerException("WebView is null");
        }
    }

    private void loadLocalUrl(WebView webView,String url){
        loadWebUrl(webView,"file:///android_asset/"+url);
    }

    private void loadPage(WebView webView,String url){
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)){
            loadWebUrl(webView,url);
        }else{
            loadLocalUrl(webView,url);
        }
    }

    public void loadPage(WebDelegate delegate,String url){
        loadPage(delegate.getWebView(),url);
    }

    private void callPhone(Context context,String url){
        final Intent intent = new Intent(Intent.ACTION_CALL);
        final Uri data = Uri.parse(url);
        intent.setData(data);
        ContextCompat.startActivity(context,intent,null);
    }
}
