package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.utils.SDKUtils
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/21.
 */
@Service
class FeixinHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.service?.name == "com.feinno.sdk.service.CoreService") {

            SDKUtils.addSDK("飞信BOP平台sdk")
            return true
        }
        return false
    }
}
