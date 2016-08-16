package cn.magicwindow.analysisapp.analysis.handler;

import cn.magicwindow.analysisapp.AppInfo;
import cn.magicwindow.analysisapp.SDK;
import cn.magicwindow.analysisapp.analysis.ActivityRequest;
import org.springframework.stereotype.Service;

/**
 * Created by tony on 16/8/12.
 */
@Service
class UmengHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name=="UMENG_APPKEY") {
            def sdk = new SDK("友盟sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.service?.name=="com.umeng.message.UmengService") {
            def sdk = new SDK("友盟推送sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }

        return false;
    }
}