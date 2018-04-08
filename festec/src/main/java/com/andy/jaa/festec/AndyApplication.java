package com.andy.jaa.festec;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.andy.jaa.andyfec.app.Latte;
import com.andy.jaa.andyfec.net.interceptors.DebugInterceptor;
import com.andy.jaa.ec.database.DatabaseManager;
import com.andy.jaa.ec.icon.FontEcModule;
import com.andy.jaa.festec.event.TestEvent;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by quanxi on 2018/3/9.
 */

public class AndyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://192.168.0.122:8080/RestServer/api/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                //过滤器
                .withInterceptor(new DebugInterceptor("test",R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withJavascriptInterface("latte")
                .withWebEvent("test",new TestEvent())
                .configure();

        DatabaseManager.getInstance().init(this);
//        Stetho.initializeWithDefaults(this);
        initStetho();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    private void initStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
