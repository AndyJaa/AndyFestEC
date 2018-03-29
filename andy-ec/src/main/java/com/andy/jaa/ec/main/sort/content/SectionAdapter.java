package com.andy.jaa.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.andy.jaa.andyfec.ui.image.GlideApp;
import com.andy.jaa.ec.R;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by quanxi on 2018/3/29.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean,BaseViewHolder> {
    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.tv_header,item.header);
        helper.setVisible(R.id.tv_more,item.isIsMore());
        helper.addOnClickListener(R.id.tv_more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        //item.t 返回SectionBean的类型
        final String goodsThumb = item.t.getGoodsThumb();
        final String goodsName = item.t.getGoodsName();
        final int goodsId = item.t.getGoodsId();
        final SectionContentItemEntity entity = item.t;
        helper.setText(R.id.tv_content,goodsName);
        final AppCompatImageView imageView = helper.getView(R.id.iv_content);
        GlideApp.with(mContext).load(goodsThumb).into(imageView);
    }
}
