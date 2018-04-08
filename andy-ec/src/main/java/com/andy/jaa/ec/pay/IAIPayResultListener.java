package com.andy.jaa.ec.pay;

/**
 * Created by quanxi on 2018/4/8.
 */

public interface IAIPayResultListener {
    void onPaySuccess();
    void onPaying();
    void onPayFail();
    void onPayCancel();
    void onPayConnectError();
}
