package com.andy.jaa.andyfec.utils.callback;

import android.support.annotation.Nullable;

/**
 * Created by quanxi on 2018/5/2.
 */

public interface IGlobalCallback<T> {
    void executeCallback(@Nullable T args);
}
