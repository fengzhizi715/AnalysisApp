package cn.magicwindow.analysisapp.analysis.handler;

import cn.magicwindow.analysisapp.AppInfo;
import cn.magicwindow.analysisapp.SDK;
import cn.magicwindow.analysisapp.analysis.ActivityRequest;
import org.springframework.stereotype.Service;

/**
 * Created by tony on 16/8/11.
 */
@Service
public class MeizuHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request!=null && request.getActivity()!=null
                && request.getActivity().getName()!=null
                && request.getActivity().getName().equals("sdk.meizu.auth.ui.AuthActivity")) {
            SDK sdk = new SDK("魅族开放平台MzOpen sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }

        return false;
    }
}
