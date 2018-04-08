package com.andy.jaa.ec.main.sort.content;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.andyfec.net.RestClient;
import com.andy.jaa.andyfec.net.callback.ISuccess;
import com.andy.jaa.ec.R;
import com.andy.jaa.ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by quanxi on 2018/3/28.
 */

public class ContentDelegate extends LatteDelegate {
    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView;
    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle arg = getArguments();
        if (arg!=null){
            mContentId = arg.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate getInstance(int contentId){
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID,contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }

    private void initData(){
        RestClient.builder()
                .url("sort_content_list.php?contentId="+mContentId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mData = new SectionDataConverter().convert(response);
                        final SectionAdapter adapter =
                                new SectionAdapter(R.layout.item_section_content,R.layout.item_section_header,mData);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }

    @Override
    public void post(Runnable runnable) {
        getSupportDelegate().post(runnable);
    }
}
