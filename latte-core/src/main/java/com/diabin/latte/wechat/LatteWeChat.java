package com.diabin.latte.wechat;

import android.app.Activity;

import com.diabin.latte.app.ConfigKeys;
import com.diabin.latte.app.Latte;
import com.diabin.latte.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by 傅令杰 on 2017/4/25
 */

public final class LatteWeChat {
    private final IWXAPI WXAPI;
    static final String APP_ID = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    static final String APP_SECRET = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private IWeChatSignInCallback mSignInCallBack = null;

    private static final class Holder {
        private static final LatteWeChat LATTE_WE_CHAT = new LatteWeChat();
    }

    public static LatteWeChat getInstance() {
        return Holder.LATTE_WE_CHAT;
    }

    private LatteWeChat() {
        final Activity activity = Latte.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public LatteWeChat onSignInSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallBack = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallBack() {
        return mSignInCallBack;
    }

    public IWXAPI getIWXAPI() {
        return WXAPI;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
