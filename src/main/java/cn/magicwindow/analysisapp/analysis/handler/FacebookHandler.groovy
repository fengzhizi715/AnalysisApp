package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/14.
 */
@Service
class FacebookHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name=="com.facebook.sdk.ApplicationId") {

            def sdk = new SDK("Facebook sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.activity?.name == "com.facebook.ads.InterstitialAdActivity") {

            def sdk = new SDK("Facebook ad sdk",true);
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false;
    }
}
