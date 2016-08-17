package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/17.
 */
@Service
class VitamioHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.activity?.name=="io.vov.vitamio.activity.InitActivity") {

            def sdk = new SDK("Vitamio sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
