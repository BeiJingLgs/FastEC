package com.diabin.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.diabin.latte.delegates.LatteDelegate;
import com.diabin.latte.net.RestClient;
import com.diabin.latte.net.callback.IError;
import com.diabin.latte.net.callback.IFailure;
import com.diabin.latte.net.callback.ISuccess;
import com.diabin.latte.wechat.LatteWeChat;
import com.diabin.latte.wechat.callbacks.IWeChatSignInCallback;

import butterknife.OnClick;

/**
 * Created by 傅令杰 on 2017/4/2
 */

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return com.diabin.fastec.example.R.layout.delegate_example;
    }

    @OnClick(com.diabin.fastec.example.R.id.btn_test)
    void onClickTest() {
        testWX();

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        testRestClient();
    }

    private void testWX() {
        LatteWeChat.getInstance().onSignInSuccess(new IWeChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {
                Toast.makeText(getContext(), userInfo, Toast.LENGTH_SHORT).show();
            }
        }).signIn();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("HAHAHAHA", response);
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();

    }
}
