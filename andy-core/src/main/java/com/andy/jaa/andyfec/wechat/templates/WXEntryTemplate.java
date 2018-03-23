package com.andy.jaa.andyfec.wechat.templates;

import com.andy.jaa.andyfec.wechat.BaseWXEntryActivity;
import com.andy.jaa.andyfec.wechat.LatteWeChat;

/**
 * Created by quanxi on 2018/3/23.
 */

public class WXEntryTemplate extends BaseWXEntryActivity{

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getmWeChatSignInCallback().onSignInSuccess(userInfo);
    }
}
