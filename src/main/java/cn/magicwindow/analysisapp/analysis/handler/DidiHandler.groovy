package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/13.
 */
@Service
class DidiHandler extends BaseHandler{

    protected boolean handle(ActivityRequest request) {

        if (request?.activity?.name.equals("com.sdu.didi.openapi.DiDiWebActivity")) {

            def sdk = new SDK("滴滴出行sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
