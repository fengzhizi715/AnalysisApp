package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.utils.SDKUtils
import org.springframework.stereotype.Service

/**
 * 中国移动
 * Created by tony on 16/8/21.
 */
@Service
class CmccHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.service?.name == "com.cmcc.aoe.AoeService") {

            SDKUtils.addSDK("AOE sdk")
            return true
        }
        return false
    }
}
