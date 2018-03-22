package com.andy.jaa.andyfec.app;

import com.andy.jaa.andyfec.utils.storage.LattePreference;

/**
 * Created by quanxi on 2018/3/22.
 */

public class AccountManager {
    private enum SignTag{
        SIGN_TAG
    }

    //保存用户登录状态
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    public static boolean getSignState(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker userChecker){
        if (getSignState()){
            userChecker.onSignIn();
        }else {
            userChecker.onNotSignIn();
        }
    }
}
