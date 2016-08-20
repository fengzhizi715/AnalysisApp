package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/12.
 */
@Service
class AliPayHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.activity?.name.equals("com.alipay.sdk.auth.AuthActivity")
                || request?.activity?.name.equals("com.alipay.sdk.app.H5PayActivity")
                || request?.activity?.name.equals("com.alipay.android.mini.window.sdk.MiniLaucherActivity")
                || request?.activity?.name == "com.alipay.android.app.ui.quickpay.window.MiniPayActivity") {

            def sdk = new SDK("支付宝sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }

        return false;
    }
}
