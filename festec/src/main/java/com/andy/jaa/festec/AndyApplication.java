package com.andy.jaa.festec;

import android.app.Application;

import com.andy.jaa.andyfec.app.Latte;
import com.andy.jaa.andyfec.net.interceptors.DebugInterceptor;
import com.andy.jaa.ec.database.DatabaseManager;
import com.andy.jaa.ec.icon.FontEcModule;
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
                .withApiHost("http://www.baidu.com/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);
//        Stetho.initializeWithDefaults(this);
        initStetho();
    }

    private void initStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
