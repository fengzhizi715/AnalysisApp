package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.utils.SDKUtils
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/21.
 */
@Service
class EvernoteHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.activity?.name == "com.evernote.client.android.EvernoteOAuthActivity") {

            SDKUtils.addSDK("Evernote sdk");
            return true
        }
        return false
    }
}
