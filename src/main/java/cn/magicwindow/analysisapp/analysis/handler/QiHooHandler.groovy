package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/13.
 */
@Service
class QiHooHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.service?.name.equals("com.qihoo.appstore.updatelib.CheckUpdateService")) {
            def sdk = new SDK("360自更新sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.metadata?.name.equals("QHOPENSDK_APPKEY")) {
            def sdk = new SDK("360开放平台sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }

        return false;
    }
}
