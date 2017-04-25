package com.diabin.latte.wechat.templates;


import com.diabin.latte.wechat.BaseWXEntryActivity;
import com.diabin.latte.wechat.LatteWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void handleOnLogin(String userInfo) {
        LatteWeChat.getInstance().getSignInCallBack().onSignInSuccess(userInfo);
    }
}
