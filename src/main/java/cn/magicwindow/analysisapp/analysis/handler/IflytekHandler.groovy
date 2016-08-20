package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * 讯飞sdk
 * Created by tony on 16/8/12.
 */
@Service
class IflytekHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name == "IFLYTEK_APPKEY") {

            def sdk = new SDK("讯飞统计sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.metadata?.name == "IFLYTEK_APPKEY_AD") {

            def sdk = new SDK("讯飞移动广告sdk",true);
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
