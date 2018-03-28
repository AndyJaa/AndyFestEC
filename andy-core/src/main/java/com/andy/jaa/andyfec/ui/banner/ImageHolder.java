package com.andy.jaa.andyfec.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;

import com.andy.jaa.andyfec.R;
import com.andy.jaa.andyfec.ui.image.GlideApp;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

/**
 * Created by quanxi on 2018/3/28.
 */

public class ImageHolder implements Holder<String> {
    private AppCompatImageView mImageView = null;
    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
//        Glide.with(context).load(data).into(mImageView);
        GlideApp.with(context).load(data).centerCrop().into(mImageView);
    }
}
