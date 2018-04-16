package com.andy.jaa.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.net.RestClient;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.andyfec.ui.recycler.MultipleItemEntity;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.R2;
import com.andy.jaa.ec.main.personal.PersonalDelegate;

import java.util.List;

import butterknife.BindView;

/**
 * Created by quanxi on 2018/4/16.
 */

public class OrderListDelegate extends LatteDelegate{
    String orderType = null;
    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle arg = getArguments();
        if (arg!=null){
            orderType = arg.getString(PersonalDelegate.ORDER_TYPE,"");
        }
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("order_list.php")
                .param("type",orderType)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data = new OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build().get();
    }
}
