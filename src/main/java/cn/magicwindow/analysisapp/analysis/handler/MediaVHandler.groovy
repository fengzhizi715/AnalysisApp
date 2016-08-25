package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.utils.SDKUtils
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/25.
 */
@Service
class MediaVHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.service?.name == "com.mediav.ads.sdk.service.MvService" ||
                request?.activity?.name == "com.mediav.ads.sdk.adcore.MvActivity") {
            SDKUtils.addSDK("MediaV广告sdk", true)
            return true;
        }
        return false;
    }
}
