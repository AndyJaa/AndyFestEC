package com.andy.jaa.ec.main.personal.settings;

import android.view.View;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.ec.main.personal.list.ListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by quanxi on 2018/5/3.
 */

public class SettingsClickListenter extends SimpleClickListener {
    private final LatteDelegate DELEGETE;

    public SettingsClickListenter(LatteDelegate DELEGATE) {
        this.DELEGETE = DELEGATE;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id){
            case 1:
                //设置消息推送的开关
                break;
            case 2:
                DELEGETE.getSupportDelegate().start(bean.getDelegate());
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
