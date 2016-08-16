package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/12.
 */
@Service
class IgexinHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.service?.name.equals("com.igexin.sdk.PushService")) {
            def sdk = new SDK("个推sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false;
    }
}
