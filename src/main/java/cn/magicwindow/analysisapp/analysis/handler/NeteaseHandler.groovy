package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/12.
 */
@Service
class NeteaseHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name == "BUGRPT_APPID") {
            def sdk = new SDK("网易云捕sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.receiver?.name?.contains("yxapi.AppRegister")
                || request?.activity?.name?.contains("yxapi.YXEntryActivity")) {
            def sdk = new SDK("易信sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.activity?.name == "com.youdao.sdk.common.YouDaoBrowser") {

            def sdk = new SDK("有道广告sdk",true);
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false;
    }
}
