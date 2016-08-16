package cn.magicwindow.analysisapp.analysis.handler;

import cn.magicwindow.analysisapp.AppInfo;
import cn.magicwindow.analysisapp.SDK;
import cn.magicwindow.analysisapp.analysis.ActivityRequest;
import org.springframework.stereotype.Service;

/**
 * Created by tony on 16/8/10.
 */
@Service
public class LineHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request!=null && request.getMetadata()!=null
                && request.getMetadata().getName()!=null
                && request.getMetadata().getName().equals("jp.line.sdk.ChannelId")) {
            SDK sdk = new SDK("Line sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }

        return false;
    }
}
