package com.andy.jaa.andyfec.utils.callback;

import java.util.WeakHashMap;

/**
 * Created by quanxi on 2018/5/2.
 */

public class CallbackManager {
    private static final WeakHashMap<Object,IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    public static class Holder{
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance(){
        return Holder.INSTANCE;
    }

    public CallbackManager addCallback(Object obj,IGlobalCallback callback){
        CALLBACKS.put(obj,callback);
        return this;
    }

    public IGlobalCallback getCallback(Object obj){
        return CALLBACKS.get(obj);
    }
}
