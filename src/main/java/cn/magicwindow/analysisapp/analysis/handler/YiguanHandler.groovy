package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.utils.SDKUtils
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/26.
 */
@Service
class YiguanHandler extends BaseHandler{

    protected boolean handle(ActivityRequest request) {

        if (request?.service?.name == "com.eguan.monitor.service.MonitorService" ||
                request?.service?.name == "com.eguan.drivermonitor.service.MonitorService") {

            SDKUtils.addSDK("易观方舟sdk")
            return true
        }

        return false
    }
}
