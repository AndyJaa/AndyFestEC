package com.andy.jaa.ec.main.personal.list;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.SwitchCompat;

import com.andy.jaa.ec.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by quanxi on 2018/4/8.
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean,BaseViewHolder> {
    private static final RequestOptions OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop().dontAnimate();

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NOMAL,R.layout.arrow_item_layout);
        addItemType(ListItemType.ITEM_AVATAR,R.layout.arrow_item_avatar);
        addItemType(ListItemType.ITEM_SWTICH,R.layout.arrow_switch_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()){
            case ListItemType.ITEM_NOMAL:
                helper.setText(R.id.tv_arrow_text,item.getText());
                helper.setText(R.id.tv_arrow_value,item.getValue());
                break;
            case ListItemType.ITEM_AVATAR:
                Glide.with(mContext).load(item.getImageUrl())
                        .apply(OPTIONS)
                        .into((CircleImageView)helper.getView(R.id.img_arrow_avatar));
                break;
            case ListItemType.ITEM_SWTICH:
                helper.setText(R.id.tv_arrow_switch_text,item.getText());
                final SwitchCompat switchCompat = helper.getView(R.id.list_item_switch);
                switchCompat.setOnCheckedChangeListener(item.getmOnCheckChangeListener());
                break;
        }
    }
}
