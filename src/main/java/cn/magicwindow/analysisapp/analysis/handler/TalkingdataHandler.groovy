package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/17.
 */
@Service
class TalkingdataHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name=="TD_APP_ID") {

            def sdk = new SDK("TalkingData sdk");
            AppInfo.getInstance().addSDK(sdk);
            return sdk
        }
        return false
    }
}
