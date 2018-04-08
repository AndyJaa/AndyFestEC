package com.andy.jaa.ec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.andy.jaa.andyfec.delegates.bottom.BottomItemDelegate;
import com.andy.jaa.andyfec.net.RestClient;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.andyfec.ui.recycler.MultipleItemEntity;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.R2;
import com.andy.jaa.ec.pay.FastPay;
import com.andy.jaa.ec.pay.IAIPayResultListener;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by quanxi on 2018/4/4.
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, ICartItemListener, IAIPayResultListener {
    @BindView(R2.id.rv_shop_cart)
    RecyclerView recyclerView;

    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubView;

    //标记是否全选状态（是否勾选全选）
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll;
    @BindView(R2.id.tv_total_price)
    AppCompatTextView tv_total_price;

    private boolean isFilate = false;
    private double mTotalPrice = 0.00;

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0) {
            mIconSelectAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新recyclerview的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
        checkItemCount();
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRmoveSelectedItem() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        List<MultipleItemEntity> list = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getFiled(ShopCartItemFields.IS_SELECTED);
            if (isSelected) {
                list.add(entity);
            }
        }
        /**
         先获取要删除的position。然后通过mAdapter.remove把数据删除。注意这里不需要调用
         mAdapter.notifyItemRangeChanged.因为你在调用mAdapter.remove的时候，remove方法内部已经帮你调用了。
         然后通过for循环把删除item数据后面的item里面的position数据进行更新。
         也就是把后面的item的position数据值减一。
         */
        for (int i=0;i<list.size();i++) {
//            int removePosition;
            int dataCount = data.size();
            int entityPosition = list.get(i).getFiled(ShopCartItemFields.POSITION);
            if (entityPosition < dataCount){
                mAdapter.remove(entityPosition);
                for (;entityPosition<dataCount-1;entityPosition++){
                    int remItemPosition = data.get(entityPosition).getFiled(ShopCartItemFields.POSITION);
                    data.get(entityPosition).setFiled(ShopCartItemFields.POSITION,remItemPosition-1);
                }
            }
            //mCurrentCount-1，角标从0开始
//            if (entityPosition > mCurrentCount - 1) {
//                removePosition = entityPosition - (mTotalCount - mCurrentCount);
//            } else {
//                removePosition = entityPosition;
//            }
//            if (removePosition <= mAdapter.getItemCount()) {
//                mAdapter.remove(removePosition);
//                mCurrentCount = mAdapter.getItemCount();
//                //更新
//                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
//            }
        }
    }

    @OnClick(R2.id.tv_top_shop_car_clear)
    void onClickClear(){
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay(){
        createOrder();
    }

    //创建订单，跟支付没有关系
    private void createOrder(){
        final String orderUrl = "";
        final WeakHashMap<String,Object> orderParam = new WeakHashMap<>();
        orderParam.put("userid","");
        orderParam.put("amount",0.01);
        orderParam.put("comment","测试支付");
        orderParam.put("type",1);
        orderParam.put("ordertype",0);
        orderParam.put("isanonymous",true);
        orderParam.put("followeduser",0);
        RestClient.builder().url(orderUrl)
                .param(orderParam)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //具体的支付
//                        final int orderId = JSON.parseObject(response).getInteger("result");
                        int orderId = 10000;
                        FastPay.create(ShopCartDelegate.this)
                                .setPayResultListener(ShopCartDelegate.this)
                                .setPayOrderId(orderId)
                                .beginPayDialog();
                    }
                })
                .build().post();
    }

    private void checkItemCount(){
        final int count = mAdapter.getItemCount();
        if (count==0){
            View stubView = null;
            if (!isFilate) {
                stubView = mStubView.inflate();
                isFilate = true;
                final AppCompatTextView tvToBuy =
                        (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
                tvToBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),"您该购物了",Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerView.setVisibility(View.GONE);
            }
        }else {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    //购物车数量标记
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private ShopCartAdapter mAdapter = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);
    }

    @Override
    public void post(Runnable runnable) {
        getSupportDelegate().post(runnable);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_cart.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final ArrayList<MultipleItemEntity> data = new ShopCartDataConverter()
                .setJsonData(response).convert();
        mAdapter = new ShopCartAdapter(data);
        mAdapter.setICartItemListener(this);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);
        mTotalPrice = mAdapter.getTotalPrice();
        tv_total_price.setText(String.valueOf(mTotalPrice));
        checkItemCount();
    }

    //itemTotalPrice 每一条总价格
    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        tv_total_price.setText(String.valueOf(price));
    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}
