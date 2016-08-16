package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * 点石创新
 * Created by tony on 16/8/16.
 */
@Service
class IntowowHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name=="CRYSTAL_ID") {

            def sdk = new SDK("Intowow sdk",true);
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
