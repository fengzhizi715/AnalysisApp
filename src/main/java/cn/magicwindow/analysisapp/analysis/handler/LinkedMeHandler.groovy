package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.utils.SDKUtils
import org.springframework.stereotype.Service

/**
 * Created by tony on 2017/4/20.
 */
@Service
class LinkedMeHandler extends BaseHandler {

    @Override
    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name == "linkedme.sdk.key") {

            SDKUtils.addSDK("LinkedMe sdk");
            return true;
        }
        return false
    }
}
