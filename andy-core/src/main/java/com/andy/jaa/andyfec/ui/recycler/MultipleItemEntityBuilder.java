package com.andy.jaa.andyfec.ui.recycler;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by quanxi on 2018/3/28.
 */

public class MultipleItemEntityBuilder {
    private static final LinkedHashMap<Object,Object> FIELDS = new LinkedHashMap<>();

    public MultipleItemEntityBuilder(){
        //先清空之前的 数据
        FIELDS.clear();
    }

    public final MultipleItemEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleFields.ITEM_TYPE,itemType);
        return this;
    }

    public final MultipleItemEntityBuilder setField(Object key, Object value){
        FIELDS.put(key,value);
        return this;
    }

    public final MultipleItemEntityBuilder setFields(WeakHashMap<Object,Object> items){
        FIELDS.putAll(items);
        return this;
    }

    public final MultipleItemEntity build(){
        return new MultipleItemEntity(FIELDS);
    }

}
