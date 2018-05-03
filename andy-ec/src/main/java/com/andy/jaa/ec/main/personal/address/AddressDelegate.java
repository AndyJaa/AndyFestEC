package com.andy.jaa.ec.main.personal.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.net.RestClient;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.andyfec.ui.recycler.MultipleItemEntity;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by quanxi on 2018/5/2.
 */

public class AddressDelegate extends LatteDelegate implements ISuccess {
    @BindView(R2.id.rv_address)
    RecyclerView mRvAddress;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url("address.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvAddress.setLayoutManager(manager);
        final List<MultipleItemEntity> list = new AddressDataConverter().setJsonData(response).convert();
        final AddressAdapter adapter = new AddressAdapter(list);
        mRvAddress.setAdapter(adapter);
    }
}
