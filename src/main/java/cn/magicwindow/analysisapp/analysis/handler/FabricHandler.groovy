package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/12.
 */
@Service
class FabricHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name.equals("io.fabric.ApiKey")) {
            def sdk = new SDK("Fabric sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.metadata?.name.equals("com.crashlytics.ApiKey")) {
            def sdk = new SDK("Crashlytics sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }

        return false;
    }
}
