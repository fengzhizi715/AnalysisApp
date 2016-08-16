package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/12.
 */
@Service
class YouzanHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name.equals("YZ_APP_ID")) {

            def sdk = new SDK("有赞sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
