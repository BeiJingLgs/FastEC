package com.diabin.latte.wechat;


import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseResp;

/**
 * Created by apefu on 16/6/29
 */
public abstract class BaseWXPayEntryActivity extends BaseWXActivity {

    private static final int WX_PAY_SUCCESS = 0;
    private static final int WX_PAY_FAIL = -1;
    private static final int WX_PAY_CANCEL = -2;

    protected abstract void onPaySuccess();

    protected abstract void onPayFail();

    protected abstract void onPayCancel();

    @Override
    public void onResp(BaseResp baseResp) {

        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (baseResp.errCode) {
                //支付成功
                case WX_PAY_SUCCESS:
                    onPaySuccess();
                    break;
                //支付失败
                case WX_PAY_FAIL:
                    onPayFail();
                    break;
                //用户取消
                case WX_PAY_CANCEL:
                    onPayCancel();
                    break;
                default:
                    break;
            }
        }
    }
}
