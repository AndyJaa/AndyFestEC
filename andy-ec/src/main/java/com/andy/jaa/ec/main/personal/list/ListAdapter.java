package com.andy.jaa.ec.main.personal.list;

import com.andy.jaa.ec.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by quanxi on 2018/4/8.
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean,BaseViewHolder> {

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NOMAL,R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()){
            case 20:
                helper.setText(R.id.tv_arrow_text,item.getmText());
                helper.setText(R.id.tv_arrow_value,item.getmValue());
                break;
        }
    }
}
