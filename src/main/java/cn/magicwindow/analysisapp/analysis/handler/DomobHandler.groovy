package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/12.
 */
@Service
class DomobHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.activity?.name.equals("cn.domob.android.ads.DomobActivity")) {
            def sdk = new SDK("Domob 广告 sdk", true);
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.service?.name.equals("cn.dow.android.DService")) {

            def sdk = new SDK("Domob 积分墙 sdk", true);
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }

        return false;
    }
}
