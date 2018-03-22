package com.andy.jaa.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.andy.jaa.andyfec.app.AccountManager;
import com.andy.jaa.ec.database.DatabaseManager;
import com.andy.jaa.ec.database.UserProfile;

/**
 * Created by quanxi on 2018/3/22.
 */

public class SignHandler {
    public static void onSignUp(String response, ISignListener mISignListener){
        final JSONObject userJson = JSON.parseObject(response);
        UserProfile user = JSONObject.parseObject(userJson.toJSONString(), UserProfile.class);
        DatabaseManager.getInstance().getUserDao().insertInTx(user);
        //注册成功并登录
        AccountManager.setSignState(true);
        mISignListener.onSignUpSuccess();
    }

    public static void onSignIn(String response, ISignListener mISignListener){
        final JSONObject userJson = JSON.parseObject(response);
        UserProfile user = JSONObject.parseObject(userJson.toJSONString(), UserProfile.class);
        //注册成功并登录
        AccountManager.setSignState(true);
        mISignListener.onSignInSuccess();
    }
}
