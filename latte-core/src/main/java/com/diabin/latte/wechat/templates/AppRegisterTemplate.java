package com.diabin.latte.wechat.templates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.diabin.latte.app.ConfigKeys;
import com.diabin.latte.app.Latte;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class AppRegisterTemplate extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        final IWXAPI api = WXAPIFactory.createWXAPI(context, null);

        api.registerApp((String) Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID));
    }
}