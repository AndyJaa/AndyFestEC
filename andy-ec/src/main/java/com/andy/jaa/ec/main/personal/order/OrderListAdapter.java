package com.andy.jaa.ec.main.personal.order;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.andy.jaa.andyfec.ui.recycler.MultipleFields;
import com.andy.jaa.andyfec.ui.recycler.MultipleItemEntity;
import com.andy.jaa.andyfec.ui.recycler.MultipleRecyclerAdapter;
import com.andy.jaa.andyfec.ui.recycler.MultipleViewHolder;
import com.andy.jaa.ec.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


/**
 * Created by quanxi on 2018/4/16.
 */

public class OrderListAdapter extends MultipleRecyclerAdapter {
    private RequestOptions OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().dontAnimate();

    protected OrderListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list);
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {
        super.convert(helper, item);
        switch (helper.getItemViewType()){
            case OrderListItemType.ITEM_ORDER_LIST:
                final AppCompatImageView imageView = helper.getView(R.id.image_order_list);
                final AppCompatTextView title = helper.getView(R.id.tv_order_list_title);
                final AppCompatTextView price = helper.getView(R.id.tv_order_list_price);
                final AppCompatTextView time = helper.getView(R.id.tv_order_list_time);

                final String thumb = item.getFiled(MultipleFields.IMAGE_URL);
                final String titValue = item.getFiled(MultipleFields.TITLE);
                final double priceValue = item.getFiled(OrderItemFields.PRICE);
                final String timeValue = item.getFiled(OrderItemFields.TIME);

                Glide.with(mContext).load(thumb).apply(OPTIONS).into(imageView);
                title.setText(titValue);
                price.setText("价格："+String.valueOf(priceValue));
                time.setText("时间："+timeValue);
                break;
        }
    }
}
