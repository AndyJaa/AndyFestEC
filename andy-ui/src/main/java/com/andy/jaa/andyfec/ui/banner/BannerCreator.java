package com.andy.jaa.andyfec.ui.banner;

import com.andy.jaa.andyfec.R;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by quanxi on 2018/3/28.
 */

public class BannerCreator {
    public static void setDefault(ConvenientBanner<String> convenientBanner
            , ArrayList<String> banners, OnItemClickListener onItemClickListener){
        convenientBanner
                .setPages(new HolderCreator(),banners)
                .setPageIndicator(new int[]{R.drawable.dot_nomal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(onItemClickListener)
//                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }
}
