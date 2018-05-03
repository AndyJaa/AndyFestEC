package com.andy.jaa.ec.main.personal.address;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.andy.jaa.andyfec.net.RestClient;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.andyfec.ui.recycler.MultipleFields;
import com.andy.jaa.andyfec.ui.recycler.MultipleItemEntity;
import com.andy.jaa.andyfec.ui.recycler.MultipleRecyclerAdapter;
import com.andy.jaa.andyfec.ui.recycler.MultipleViewHolder;
import com.andy.jaa.ec.R;

import java.util.List;

/**
 * Created by quanxi on 2018/5/2.
 */

public class AddressAdapter extends MultipleRecyclerAdapter {
    protected AddressAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(final MultipleViewHolder helper, MultipleItemEntity entity) {
        super.convert(helper, entity);
        switch (helper.getItemViewType()){
            case AddressItemType.ITEM_ADDRESS:
                final String name = entity.getFiled(MultipleFields.NAME);
                final String phone = entity.getFiled(AddressItemFields.PHONE);
                final String address = entity.getFiled(AddressItemFields.ADDRESS);
                final boolean isDefault = entity.getFiled(MultipleFields.TAG);
                final int id = entity.getFiled(MultipleFields.ID);

                final AppCompatTextView nameText = helper.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = helper.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = helper.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = helper.getView(R.id.tv_address_delete);
                deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RestClient.builder()
                                .url("address.php")
                                .param("id", id)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        remove(helper.getLayoutPosition());
                                    }
                                })
                                .build()
                                .post();
                    }
                });

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                break;
        }
    }
}
