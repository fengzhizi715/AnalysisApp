package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/12.
 */
@Service
class GoogleHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        if (request?.metadata?.name.equals("com.google.android.maps.v2.API_KEY")
                || request?.metadata?.name.equals("com.google.android.geo.API_KEY")) {
            def sdk = new SDK("Google地图sdk");
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.activity?.name.equals("com.google.android.gms.ads.AdActivity")) {
            def sdk = new SDK("Google AdMob sdk",true);
            AppInfo.getInstance().addSDK(sdk);
            return true;
        } else if (request?.activity?.name.equals("com.google.android.gms.ads.purchase.InAppPurchaseActivity")) {
            def sdk = new SDK("Firebase应用内购买广告sdk",true);
            AppInfo.getInstance().addSDK(sdk);
            return true;
        }
        return false
    }
}
