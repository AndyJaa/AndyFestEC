package com.andy.jaa.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.utils.callback.CallbackManager;
import com.andy.jaa.andyfec.utils.callback.CallbackType;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.R2;
import com.andy.jaa.ec.main.personal.PersionalClickListener;
import com.andy.jaa.ec.main.personal.address.AddressDelegate;
import com.andy.jaa.ec.main.personal.list.ListAdapter;
import com.andy.jaa.ec.main.personal.list.ListBean;
import com.andy.jaa.ec.main.personal.list.ListItemType;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by quanxi on 2018/5/3.
 */

public class SettingsDelegate extends LatteDelegate {
    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerViewSetting;
    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean push = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_SWTICH)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                            Toast.makeText(getContext(),"推送打开",Toast.LENGTH_SHORT).show();
                        }else {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_STOP_PUSH).executeCallback(null);
                            Toast.makeText(getContext(),"推送关闭",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setText("推送设置")
                .build();
        final ListBean about = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NOMAL)
                .setId(2)
                .setDelegate(new AboutDelegate())
                .setText("关于")
                .build();

        final ArrayList<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(about);
        //设置Recyclerview
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerViewSetting.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerViewSetting.setAdapter(adapter);
        mRecyclerViewSetting.addOnItemTouchListener(new SettingsClickListenter(this));
    }
}
