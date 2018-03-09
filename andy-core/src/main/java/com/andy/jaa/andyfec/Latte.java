package com.andy.jaa.andyfec;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by quanxi on 2018/3/9.
 */

public class Latte {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTENT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static WeakHashMap<String ,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }
}
