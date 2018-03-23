package com.andy.jaa.festec.generators;

import com.andy.jaa.andyfec.annotations.PayEntryGenerator;
import com.andy.jaa.andyfec.wechat.templates.WXPayEntryTemplate;

/**
 * Created by quanxi on 2018/3/23.
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.andy.jaa.festec",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
