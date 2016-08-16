package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/16.
 */
@Service
class WemartHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.activity?.name=="cn.wemart.sdk.v2.activity.MallActivity") {

            def sdk = new SDK("微猫sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
