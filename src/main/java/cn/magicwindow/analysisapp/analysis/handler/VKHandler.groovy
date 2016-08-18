package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * vk 为俄罗斯知名在线社交网络服务网站
 * Created by tony on 16/8/18.
 */
@Service
class VKHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.activity?.name == "com.vk.sdk.VKOpenAuthActivity") {
            def sdk = new SDK("VK sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false;
    }
}
