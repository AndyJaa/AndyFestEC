package com.andy.jaa.andyfec.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.andy.jaa.andyfec.R;
import com.andy.jaa.andyfec.ui.banner.BannerCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quanxi on 2018/3/28.
 */

public class MultipleRecyclerAdapter extends
        BaseMultiItemQuickAdapter<MultipleItemEntity,MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup, OnItemClickListener {

    private boolean mInitBanner = false;

    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static MultipleRecyclerAdapter create (List<MultipleItemEntity> data){
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create (DataConverter converter){
        return new MultipleRecyclerAdapter(converter.convert());
    }

    private void init(){
        //设置不同的布局
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        //设置宽度的监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {
        final String text;
        final String imgUrl;
        final ArrayList<String> bannerImages;
        switch (helper.getItemViewType()){
            case ItemType.TEXT:
                text = item.getFiled(MultipleFields.TEXT);
                helper.setText(R.id.text_single,text);
                break;
            case ItemType.IMAGE:
                imgUrl = item.getFiled(MultipleFields.IMAGE_URL);
                Glide.with(mContext).load(imgUrl).into((ImageView) helper.getView(R.id.img_single));
                break;
            case ItemType.TEXT_IMAGE:
                text = item.getFiled(MultipleFields.TEXT);
                imgUrl = item.getFiled(MultipleFields.IMAGE_URL);
                Glide.with(mContext).load(imgUrl).into((ImageView) helper.getView(R.id.iv_multiple));
                helper.setText(R.id.tv_multiple,text);
                break;
            case ItemType.BANNER:
                if(!mInitBanner){
                    bannerImages = item.getFiled(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = helper.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner,bannerImages,this);
                    mInitBanner = true;
                }
                break;
        }
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getFiled(MultipleFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int position) {

    }
}
