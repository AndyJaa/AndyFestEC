package com.andy.jaa.ec.main;

import android.graphics.Color;

import com.andy.jaa.andyfec.delegates.bottom.BaseBottomDelegate;
import com.andy.jaa.andyfec.delegates.bottom.BottomItemDelegate;
import com.andy.jaa.andyfec.delegates.bottom.BottomTabBean;
import com.andy.jaa.andyfec.delegates.bottom.ItemBuilder;
import com.andy.jaa.ec.main.cart.ShopCartDelegate;
import com.andy.jaa.ec.main.discover.DiscoverDelegate;
import com.andy.jaa.ec.main.index.IndexDelegate;
import com.andy.jaa.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by quanxi on 2018/3/26.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
