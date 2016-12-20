package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.utils.SDKUtils
import org.springframework.stereotype.Service

/**
 * Created by tony on 2016/12/20.
 */
@Service
class MagicWindowHandler extends BaseHandler{

    protected boolean handle(ActivityRequest request) {

        if(request?.metadata?.name=="MW_APPID") {
            SDKUtils.addSDK("魔窗sdk")
            return true;
        }
        return false
    }
}
