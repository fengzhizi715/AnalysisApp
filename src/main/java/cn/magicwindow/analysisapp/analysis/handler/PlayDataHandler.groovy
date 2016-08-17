package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/17.
 */
@Service
class PlayDataHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name=="PLAYDATA_APPKEY") {

            def sdk = new SDK("PlayData sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
