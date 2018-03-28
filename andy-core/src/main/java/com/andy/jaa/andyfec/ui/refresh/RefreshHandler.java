package com.andy.jaa.andyfec.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.andy.jaa.andyfec.app.Latte;
import com.andy.jaa.andyfec.net.RestClient;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.andyfec.ui.recycler.DataConverter;
import com.andy.jaa.andyfec.ui.recycler.MultipleRecyclerAdapter;
import com.andy.jaa.andyfec.ui.recycler.PagingBean;
import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * Created by quanxi on 2018/3/27.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener {
    private final SwipeRefreshLayout REFRESHLAOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter adapter;
    private final DataConverter CONVERT;


    private RefreshHandler(SwipeRefreshLayout refreshLayout
            ,RecyclerView recyclerView,DataConverter converter,PagingBean bean) {
        this.REFRESHLAOUT = refreshLayout;
        this.REFRESHLAOUT.setOnRefreshListener(this);
        this.RECYCLERVIEW = recyclerView;
        this.CONVERT = converter;
        this.BEAN = bean;
    }

    public static RefreshHandler create(SwipeRefreshLayout refreshLayout
            ,RecyclerView recyclerView,DataConverter converter){
        return new RefreshHandler(refreshLayout,recyclerView,converter,new PagingBean());
    }

    @Override
    public void onRefresh() {

        this.REFRESHLAOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行网络请求，请求结束之后，执行下面这句
                REFRESHLAOUT.setRefreshing(false);
            }
        },2000);
    }

    public void firstPage(String url){
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置adapter
                        adapter = MultipleRecyclerAdapter.create(CONVERT.setJsonData(response));
                        adapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(adapter);
                        BEAN.addIndex();
                    }
                }).build().get();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
