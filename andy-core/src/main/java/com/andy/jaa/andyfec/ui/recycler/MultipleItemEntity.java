package com.andy.jaa.andyfec.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Created by quanxi on 2018/3/28.
 */

public class MultipleItemEntity implements MultiItemEntity {
    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUENE = new ReferenceQueue<>();
    private final LinkedHashMap<Object,Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object,Object>> FIELDS_REFERNCE =
            new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELDS,ITEM_QUENE);

    MultipleItemEntity (LinkedHashMap<Object,Object> fields){
        FIELDS_REFERNCE.get().putAll(fields);
    }

    public static MultipleItemEntityBuilder builder(){
        return new MultipleItemEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FIELDS_REFERNCE.get().get(MultipleFields.ITEM_TYPE);
    }

    public final <T> T getFiled(Object key){
        return (T) FIELDS_REFERNCE.get().get(key);
    }

    public final LinkedHashMap<?,?> getFileds(){
        return FIELDS_REFERNCE.get();
    }

    public final MultipleItemEntity setFiled(Object key,Object value){
        FIELDS_REFERNCE.get().put(key,value);
        return this;
    }
}
