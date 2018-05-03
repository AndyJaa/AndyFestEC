package com.andy.jaa.ec.main.personal.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.R2;
import com.andy.jaa.ec.main.personal.list.ListAdapter;
import com.andy.jaa.ec.main.personal.list.ListBean;
import com.andy.jaa.ec.main.personal.list.ListItemType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by quanxi on 2018/4/16.
 */

public class UserProfileDelegate extends LatteDelegate {
    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView;
    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean image = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(1)
//                .setImageUrl("https://github.com/AndyJaa/AndyFestEC/blob/master/andy-ec/src/main/res/mipmap-xhdpi/avatar.jpg")
                .setImageUrl("http://i9.qhimg.com/t017d891ca365ef60b5.jpg")
                .build();

        final ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NOMAL)
                .setId(2)
                .setText("姓名")
                .setDelegate(new NameDelegate())
                .setValue("未设置姓名")
                .build();

        final ListBean gender = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NOMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置性别")
                .build();

        final ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NOMAL)
                .setId(4)
                .setText("生日")
                .setValue("未设置生日")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new UserProfileClickListener(this));
    }
}
