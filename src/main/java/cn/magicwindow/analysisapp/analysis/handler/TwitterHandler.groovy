package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.utils.SDKUtils
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/21.
 */
@Service
class TwitterHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name == "io.fabric.ApiKey") {
            def sdk = new SDK("Fabric sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.metadata?.name == "com.crashlytics.ApiKey") {
            def sdk = new SDK("Crashlytics sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.activity?.name == "com.digits.sdk.android.PhoneNumberActivity") {
            SDKUtils.addSDK("Digits sdk");
            return true
        }
        return false
    }
}
