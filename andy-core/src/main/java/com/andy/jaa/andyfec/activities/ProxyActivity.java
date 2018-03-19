package com.andy.jaa.andyfec.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.andy.jaa.andyfec.R;
import com.andy.jaa.andyfec.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by quanxi on 2018/3/9.
 */

public abstract class ProxyActivity extends SupportActivity {
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.container_delegate_id);
        setContentView(container);
        if (savedInstanceState==null){
            loadRootFragment(R.id.container_delegate_id,setRootDelegate());
        }
    }

    /**
     * 此应用只有一个Activity，所以在onDestroy()执行的时候，意味着退出此应用。
     * 在此方法中执行垃圾回收机制（不一定执行）
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
