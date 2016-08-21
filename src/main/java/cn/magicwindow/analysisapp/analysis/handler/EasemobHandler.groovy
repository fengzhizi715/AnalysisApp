package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.utils.SDKUtils
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/21.
 */
@Service
class EasemobHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name == "EASEMOB_APPKEY") {

            SDKUtils.addSDK("环信sdk")
            return true
        }
        return false
    }
}
