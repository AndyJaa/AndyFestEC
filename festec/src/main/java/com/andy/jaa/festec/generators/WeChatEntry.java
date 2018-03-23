package com.andy.jaa.festec.generators;

import com.andy.jaa.andyfec.annotations.EntryGenerator;
import com.andy.jaa.andyfec.wechat.templates.WXEntryTemplate;

/**
 * Created by quanxi on 2018/3/23.
 */
@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.andy.jaa.festec",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
