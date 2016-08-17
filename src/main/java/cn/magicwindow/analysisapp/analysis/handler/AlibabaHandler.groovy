package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/12.
 */
@Service
class AlibabaHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if  (request?.service?.name=="com.taobao.munion.base.download.DownloadingService") {

            def sdk = new SDK("Alimama Tanx sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.service?.name=="com.alibaba.sdk.android.push.ChannelService") {

            def sdk = new SDK("阿里移动推送sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.metadata?.name=="com.alibaba.app.appkey") {

            def sdk = new SDK("阿里巴巴Mobile Analytics sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
