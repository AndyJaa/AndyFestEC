package com.andy.jaa.andyfec.web;

import com.alibaba.fastjson.JSON;

/**
 * Created by quanxi on 2018/3/29.
 */

public class LatteWebInterface {
    private WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate delegate){
        this.DELEGATE = delegate;
    }

    public static LatteWebInterface create(WebDelegate delegate){
        return new LatteWebInterface(delegate);
    }

    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
