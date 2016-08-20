package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.utils.SDKUtils
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/21.
 */
@Service
class LetvHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.activity?.name == "com.letv.lepaysdk.activity.CashierAcitivity") {

            SDKUtils.addSDK("LePay sdk");
            return true;
        } else if (request?.service?.name == "com.letvcloud.cmf.MediaService") {

            SDKUtils.addSDK("乐视云sdk");
            return true;
        }
        return false
    }
}
