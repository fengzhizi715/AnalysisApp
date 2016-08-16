package cn.magicwindow.analysisapp.analysis.handler;

import cn.magicwindow.analysisapp.AppInfo;
import cn.magicwindow.analysisapp.SDK;
import cn.magicwindow.analysisapp.analysis.ActivityRequest;
import org.springframework.stereotype.Service;

/**
 * Created by tony on 16/8/9.
 */
@Service
public class MediaVHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request.getService()!=null && request.getService().getName()!=null &&
                request.getService().getName().equals("com.mediav.ads.sdk.service.MvService")) {
            SDK sdk = new SDK("MediaV广告sdk",true);
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false;
    }
}
