package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/13.
 */
@Service
class BugsnagHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name.equals("com.bugsnag.android.API_KEY")) {

            def sdk = new SDK("bugsnag sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
