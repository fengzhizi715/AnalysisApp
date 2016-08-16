package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Cobub Razor是一款开源的移动应用统计分析系统
 * Created by tony on 16/8/12.
 */
@Service
class CobubRazorHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.getMetadata()?.getName().equals("UMS_APPKEY")) {
            def sdk = new SDK("Cobub Razor sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }

        return false;
    }
}
