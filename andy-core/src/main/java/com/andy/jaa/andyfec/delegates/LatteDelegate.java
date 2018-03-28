package com.andy.jaa.andyfec.delegates;

/**
 * Created by quanxi on 2018/3/11.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {

    public <T extends LatteDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }
}
