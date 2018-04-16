package com.andy.jaa.ec.main.personal.list;

import android.widget.CompoundButton;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by quanxi on 2018/4/8.
 */

public class ListBean implements MultiItemEntity {
    private int mItemType = 0;
    private String mImageUrl = null;
    private String mText = null;
    private String mValue = null;
    private int mId = 0;
    private LatteDelegate mDelegate = null;
    private CompoundButton.OnCheckedChangeListener mOnCheckChangeListener = null;

    public ListBean(int mItemType, String mImageUrl,
                    String mText, String mValue,
                    int mId, LatteDelegate mDelegate,
                    CompoundButton.OnCheckedChangeListener mOnCheckChangeListener) {
        this.mItemType = mItemType;
        this.mImageUrl = mImageUrl;
        this.mText = mText;
        this.mValue = mValue;
        this.mId = mId;
        this.mDelegate = mDelegate;
        this.mOnCheckChangeListener = mOnCheckChangeListener;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmText() {
        if (mText == null) return "";
        return mText;
    }

    public String getmValue() {
        if (mValue == null) return "";
        return mValue;
    }

    public int getmId() {
        return mId;
    }

    public LatteDelegate getmDelegate() {
        return mDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getmOnCheckChangeListener() {
        return mOnCheckChangeListener;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public static final class Builder {
        private int id = 0;
        private int itemType = 0;
        private String imageUrl = null;
        private String text = null;
        private String value = null;
        private CompoundButton.OnCheckedChangeListener listener = null;
        private LatteDelegate delegate = null;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setListener(CompoundButton.OnCheckedChangeListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setDelegate(LatteDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public ListBean build() {
            return new ListBean(itemType, imageUrl, text, value, id, delegate, listener);
        }
    }
}
