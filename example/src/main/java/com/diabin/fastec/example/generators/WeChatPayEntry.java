package com.diabin.fastec.example.generators;

import com.diabin.latte.annotations.PayEntryGenerator;
import com.diabin.latte.wechat.templates.WXPayEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.diabin.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
