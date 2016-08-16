package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * 云涛APM
 * Created by tony on 16/8/16.
 */
@Service
class YuntaoHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name=="UED_APM_APP_ID") {
            def sdk = new SDK("云涛sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
