package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/20.
 */
@Service
class UnionpayHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.activity?.name == "com.unionpay.uppay.PayActivity") {

            def sdk = new SDK("银联支付sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true
        }
        return false
    }
}
