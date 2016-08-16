package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/15.
 */
@Service
class InmobiHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.activity?.name=="com.inmobi.rendering.InMobiAdActivity") {

            def sdk = new SDK("InMobi广告sdk",true);
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
