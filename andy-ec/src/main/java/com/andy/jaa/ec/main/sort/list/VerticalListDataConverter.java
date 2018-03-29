package com.andy.jaa.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.andy.jaa.andyfec.ui.recycler.DataConverter;
import com.andy.jaa.andyfec.ui.recycler.ItemType;
import com.andy.jaa.andyfec.ui.recycler.MultipleFields;
import com.andy.jaa.andyfec.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by quanxi on 2018/3/29.
 */

public final class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray jsonArray = JSON.parseObject(getJsonData())
                .getJSONObject("data").getJSONArray("list");
        final int size = jsonArray.size();
        for (int i=0;i<size;i++){
            final JSONObject data = jsonArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .build().setFiled(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MEUN_LIST)
                    .setFiled(MultipleFields.ID,id).setFiled(MultipleFields.NAME,name)
                    //默认都不选中
                    .setFiled(MultipleFields.TAG,false);
            dataList.add(entity);
            //设置第一个为选中状态
            dataList.get(0).setFiled(MultipleFields.TAG,true);
        }

        return dataList;
    }
}
