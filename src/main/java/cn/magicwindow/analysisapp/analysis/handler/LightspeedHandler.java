package cn.magicwindow.analysisapp.analysis.handler;

import cn.magicwindow.analysisapp.AppInfo;
import cn.magicwindow.analysisapp.SDK;
import cn.magicwindow.analysisapp.analysis.ActivityRequest;
import org.springframework.stereotype.Service;

/**
 * Lightspeed Push
 * Created by tony on 16/8/10.
 */
@Service
public class LightspeedHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request.getService()!=null
                && request.getService().name!=null
                && request.getService().name.equals("com.arrownock.push.PushService")) {
            SDK sdk = new SDK("Lightspeed Push sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false;
    }
}
