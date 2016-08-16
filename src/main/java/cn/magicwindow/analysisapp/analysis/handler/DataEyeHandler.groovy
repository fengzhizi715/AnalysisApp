package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/12.
 */
@Service
class DataEyeHandler extends BaseHandler{

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name.equals("DC_APPKEY")) {
            def sdk = new SDK("DataEye Game Experience sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.service?.name.equals("com.dataeye.channel.DCAppService")) {
            def sdk = new SDK("DataEye 统计分析sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }

        return false
    }
}
