package com.andy.jaa.festec;

import android.app.Application;

import com.andy.jaa.andyfec.app.Latte;
import com.andy.jaa.ec.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by quanxi on 2018/3/9.
 */

public class AndyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();
    }
}
