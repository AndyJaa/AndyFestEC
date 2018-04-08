package com.andy.jaa.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.ui.recycler.ItemType;
import com.andy.jaa.andyfec.ui.recycler.MultipleFields;
import com.andy.jaa.andyfec.ui.recycler.MultipleItemEntity;
import com.andy.jaa.andyfec.ui.recycler.MultipleRecyclerAdapter;
import com.andy.jaa.andyfec.ui.recycler.MultipleViewHolder;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.main.sort.SortDelegate;
import com.andy.jaa.ec.main.sort.content.ContentDelegate;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * Created by quanxi on 2018/3/29.
 */

public class SortRecyclerAdapter extends MultipleRecyclerAdapter {
    private SortDelegate DELEGATE;
    private int mPrePosition = 0;
    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE =delegate;
        //添加垂直布局
        addItemType(ItemType.VERTICAL_MEUN_LIST, R.layout.item_vertical_meun_list);
    }

    @Override
    protected void convert(final MultipleViewHolder helper, final MultipleItemEntity item) {
        super.convert(helper, item);
        switch (helper.getItemViewType()){
            case ItemType.VERTICAL_MEUN_LIST:
                final boolean isClicked = item.getFiled(MultipleFields.TAG);
                final String name = item.getFiled(MultipleFields.NAME);
                final View viewLine = helper.getView(R.id.view_line);
                final AppCompatTextView textView = helper.getView(R.id.tv_vertical_item_name);
                final View view = helper.itemView;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = helper.getAdapterPosition();
                        if (mPrePosition!=currentPosition){
                            //还原上一个
                            getData().get(mPrePosition).setFiled(MultipleFields.TAG,false);
                            notifyItemChanged(mPrePosition);

                            //更新当前
                            item.setFiled(MultipleFields.TAG,true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;

                            final int contentId = getData().get(currentPosition).getFiled(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });
                if (!isClicked){
                    viewLine.setVisibility(View.INVISIBLE);
                    textView.setTextColor(ContextCompat.getColor(mContext,R.color.we_chat_balck));
                    view.setBackgroundColor(ContextCompat.getColor(mContext,R.color.item_background));
                }else{
                    viewLine.setVisibility(View.VISIBLE);
                    textView.setTextColor(ContextCompat.getColor(mContext,R.color.app_main));
                    viewLine.setBackgroundColor(ContextCompat.getColor(mContext,R.color.app_main));
                    view.setBackgroundColor(Color.WHITE);
                }
                helper.setText(R.id.tv_vertical_item_name,name);
                break;
        }
    }

    private void showContent(int contentId){
        final ContentDelegate delegate = ContentDelegate.getInstance(contentId);
        swichContent(delegate);
    }

    private void swichContent(ContentDelegate delegate){
        final LatteDelegate contentDelegate =
                SupportHelper.findFragment(DELEGATE.getChildFragmentManager(), ContentDelegate.class);
        if (contentDelegate!=null){
            //第二个参数是 是否加入到返回栈
            contentDelegate.getSupportDelegate().replaceFragment(delegate,false);
        }
    }
}
