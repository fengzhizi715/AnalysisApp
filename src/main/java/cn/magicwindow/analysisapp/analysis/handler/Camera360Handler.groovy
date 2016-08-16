package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/14.
 */
@Service
class Camera360Handler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name.equals("EDIT_SDK_KEY")) {

            def sdk = new SDK("Camera360编辑sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
