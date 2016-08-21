package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.utils.SDKUtils
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/21.
 */
@Service
class SnailHandler extends BaseHandler{

    protected boolean handle(ActivityRequest request) {

        if (request?.activity?.name == "com.umpay.creditcard.android.UmpayActivity") {

            SDKUtils.addSDK("蜗牛游戏sdk")
            return true
        }
        return false
    }
}
