package com.andy.jaa.ec.main.index;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.andy.jaa.andyfec.ui.recycler.DataConverter;
import com.andy.jaa.andyfec.ui.recycler.ItemType;
import com.andy.jaa.andyfec.ui.recycler.MultipleFields;
import com.andy.jaa.andyfec.ui.recycler.MultipleItemEntity;
import com.andy.jaa.andyfec.ui.recycler.MultipleItemEntityBuilder;

import java.util.ArrayList;

/**
 * Created by quanxi on 2018/3/28.
 */

public class IndexDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i=0;i<size;i++){
            final JSONObject jsonObject = dataArray.getJSONObject(i);
            final String imgUrl = jsonObject.getString("imageUrl");
            final String text = jsonObject.getString("text");
            final int spanSize = jsonObject.getInteger("spanSize");
            final int id = jsonObject.getInteger("goodsId");
            final JSONArray banners = jsonObject.getJSONArray("banners");

            final ArrayList<String> bannerImgs = new ArrayList<>();
            int type = 0;
            if (TextUtils.isEmpty(imgUrl) && !TextUtils.isEmpty(text)){
                type = ItemType.TEXT;
            }else if (!TextUtils.isEmpty(imgUrl) && TextUtils.isEmpty(text)){
                type = ItemType.IMAGE;
            }else if (!TextUtils.isEmpty(imgUrl) && !TextUtils.isEmpty(text)){
                type = ItemType.TEXT_IMAGE;
            }else if (banners!=null){
                type = ItemType.BANNER;
                //Banner初始化
                final int bannSize = banners.size();
                for (int j=0;j<bannSize;j++){
                    bannerImgs.add(banners.getString(j));
                }
            }
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE,type)
                    .setField(MultipleFields.SPAN_SIZE,spanSize)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.TEXT,text)
                    .setField(MultipleFields.IMAGE_URL,imgUrl)
                    .setField(MultipleFields.BANNERS,bannerImgs)
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
