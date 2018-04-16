package com.andy.jaa.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andy.jaa.andyfec.delegates.bottom.BottomItemDelegate;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.R2;
import com.andy.jaa.ec.main.personal.list.ListAdapter;
import com.andy.jaa.ec.main.personal.list.ListBean;
import com.andy.jaa.ec.main.personal.list.ListItemType;
import com.andy.jaa.ec.main.personal.order.OrderListDelegate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by quanxi on 2018/4/8.
 */

public class PersonalDelegate extends BottomItemDelegate {
    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArg=null;

    @BindView(R2.id.rv_personal_setting)
    RecyclerView rv_setting;

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder(){
        mArg.putString(ORDER_TYPE,"all");
        startOrderByType();
    }

    private void startOrderByType(){
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArg);
        getSupportDelegate().start(delegate);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArg = new Bundle();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NOMAL)
                .setId(1)
                .setText("收货地址")
                .build();
        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NOMAL)
                .setId(2)
                .setText("系统设置")
                .build();

        final ArrayList<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);
        //设置Recyclerview
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv_setting.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        rv_setting.setAdapter(adapter);
    }
}
