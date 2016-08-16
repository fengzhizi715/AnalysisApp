package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/16.
 */
@Service
class HuaweiHandler extends BaseHandler{

    protected boolean handle(ActivityRequest request) {

        if (request?.receiver?.name == "com.huawei.android.pushagent.PushEventReceiver") {
            SDK sdk = new SDK("华为推送sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
