package com.andy.jaa.andyfec;

import java.util.WeakHashMap;

/**
 * Created by quanxi on 2018/3/9.
 */

public class Configurator {
    private static final WeakHashMap<String,Object> LATTE_CONFIGS = new WeakHashMap<>();
    final WeakHashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    /**
     * 线程安全的懒汉模式
     * 1、静态内部类，2、然后getInstance()
     */
    //1
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    //2
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    public final void configure(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    public void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
