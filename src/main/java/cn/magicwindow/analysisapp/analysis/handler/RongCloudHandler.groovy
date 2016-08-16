package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/13.
 */
@Service
class RongCloudHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name.equals("RONG_CLOUD_APP_KEY")) {

            def sdk = new SDK("融云sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }

        return false;
    }
}
