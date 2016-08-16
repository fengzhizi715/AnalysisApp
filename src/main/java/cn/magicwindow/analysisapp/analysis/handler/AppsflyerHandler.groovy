package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * AppsFlyer 广告
 * Created by tony on 16/8/12.
 */
@Service
class AppsflyerHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.receiver?.name.equals("com.appsflyer.MultipleInstallBroadcastReceiver")) {
            def sdk = new SDK("AppsFlyer sdk",true);
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false;
    }
}
