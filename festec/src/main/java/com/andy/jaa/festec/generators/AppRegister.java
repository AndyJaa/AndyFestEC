package com.andy.jaa.festec.generators;

import com.andy.jaa.andyfec.annotations.AppRegisterGenerator;
import com.andy.jaa.andyfec.wechat.templates.AppRegisterTemplate;

/**
 * Created by quanxi on 2018/3/23.
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.andy.jaa.festec",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
