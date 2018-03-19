package com.andy.jaa.andyfec.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by quanxi on 2018/3/9.
 */

public abstract class BaseDelegateFragment extends SwipeBackFragment {
    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;
    public abstract Object setLayout();
    public abstract void onBinderView( @Nullable Bundle savedInstanceState,View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (setLayout() instanceof Integer) {
            view = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            view = (View) setLayout();
        }

        if (view!=null){
            mUnbinder = ButterKnife.bind(this,view);
            onBinderView(savedInstanceState,view);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder!=null)mUnbinder.unbind();
    }
}
