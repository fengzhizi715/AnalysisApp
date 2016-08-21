package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.utils.SDKUtils
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/16.
 */
@Service
class XiaomiHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.service?.name == "com.xiaomi.push.service.XMPushService" ||
                request?.service?.name == "com.xiaomi.mipush.sdk.MessageHandleService") {
            SDK sdk = new SDK("小米推送sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.activity?.name == "com.xiaomi.account.openauth.AuthorizeActivity") {
            SDK sdk = new SDK("小米帐号开放平台sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.activity?.name == "com.xiaomi.ad.puppet.AdPuppetActivity") {
            SDK sdk = new SDK("小米广告sdk", true);
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.receiver?.name == "com.xiaomi.market.sdk.DownloadCompleteReceiver") {
            SDKUtils.addSDK("小米应用商店检查更新sdk");
            return true;
        }
        return false
    }
}
