package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/12.
 */
@Service
class ShareSDKHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if(request?.activity?.name.equals("com.mob.tools.MobUIShell")
        || request?.activity?.name.equals("cn.sharesdk.framework.ShareSDKUIShell")) {

            def sdk = new SDK("Share sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
