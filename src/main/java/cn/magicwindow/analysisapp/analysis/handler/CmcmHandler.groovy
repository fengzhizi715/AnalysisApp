package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/14.
 */
@Service
class CmcmHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.activity?.name.equals("com.cmcm.adsdk.interstitial.PicksInterstitialActivity")) {

            def sdk = new SDK("猎豹移动广告sdk",true);
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
