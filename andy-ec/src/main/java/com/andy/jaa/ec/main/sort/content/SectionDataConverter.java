package com.andy.jaa.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quanxi on 2018/3/29.
 */

public class SectionDataConverter {

    final List<SectionBean> convert(String json) {
        final ArrayList<SectionBean> dataList = new ArrayList<>();
        final JSONArray jsonArray = JSON.parseObject(json).getJSONArray("data");
        final int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            final String title = jsonObject.getString("section");
            final int id = jsonObject.getInteger("id");
            //添加title
            final SectionBean bean = new SectionBean(true, title);
            bean.setId(id);
            bean.setIsMore(true);
            dataList.add(bean);

            final JSONArray contArray = jsonObject.getJSONArray("goods");
            //商品循环
            final int goodsSize = contArray.size();
            for (int j = 0; j < goodsSize; j++) {
                final JSONObject goodObj = contArray.getJSONObject(j);
                final int goodsId = goodObj.getInteger("goods_id");
                final String goodsName = goodObj.getString("goods_name");
                final String goodsThumb = goodObj.getString("goods_thumb");
                //添加内容
                final SectionContentItemEntity entity = new SectionContentItemEntity();
                entity.setGoodsId(goodsId);
                entity.setGoodsName(goodsName);
                entity.setGoodsThumb(goodsThumb);
                dataList.add(new SectionBean(entity));
            }
        }
        return dataList;
    }
}
