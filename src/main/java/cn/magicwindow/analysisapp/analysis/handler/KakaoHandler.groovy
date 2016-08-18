package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/18.
 */
@Service
class KakaoHandler extends BaseHandler{

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name=="com.kakao.sdk.AppKey") {

            def sdk = new SDK("Kakao Talk sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }

        return false;
    }
}
