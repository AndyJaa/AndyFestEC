package com.andy.jaa.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;

import com.andy.jaa.andyfec.app.AccountManager;
import com.andy.jaa.andyfec.app.IUserChecker;
import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.ui.launcher.ILauncherListener;
import com.andy.jaa.andyfec.ui.launcher.LauncherHolderCreator;
import com.andy.jaa.andyfec.ui.launcher.OnLauncherFinishTag;
import com.andy.jaa.andyfec.ui.launcher.ScrollLauncherTag;
import com.andy.jaa.andyfec.utils.storage.LattePreference;
import com.andy.jaa.ec.R;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by quanxi on 2018/3/20.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {
    private ConvenientBanner<Integer> mConvenBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener;

    void initBanner() {
        INTEGERS.add(R.mipmap.launcher05);
        INTEGERS.add(R.mipmap.launcher02);
        INTEGERS.add(R.mipmap.launcher03);
        INTEGERS.add(R.mipmap.launcher01);
        INTEGERS.add(R.mipmap.launcher04);

        mConvenBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_nomal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .startTurning(1000).setCanLoop(false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener)activity;
        }
    }


    @Override
    public Object setLayout() {
        mConvenBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenBanner;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //判断是否点击的是最后一张图片
        if (position == INTEGERS.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUCHER_APP.name(),true);
            //检查用户是否登录了app
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener!=null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener!=null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }

    }
}
