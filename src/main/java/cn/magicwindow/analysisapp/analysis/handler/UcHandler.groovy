package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.utils.SDKUtils
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/21.
 */
@Service
class UcHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.service?.name == "com.uc.addon.sdk.remote.AddonService") {

            SDKUtils.addSDK("UC插件开发sdk")
            return true
        }
        return false
    }
}
