package cn.magicwindow.analysisapp.utils;

import cn.magicwindow.analysisapp.AppInfo;
import cn.magicwindow.analysisapp.SDK;

/**
 * Created by tony on 16/8/20.
 */
public class SDKUtils {

    public static void addSDK(String sdkName) {

        addSDK(sdkName,false);
    }

    public static void addSDK(String sdkName,boolean isAd) {

        SDK sdk = new SDK(sdkName,isAd);
        AppInfo.getInstance().addSDK(sdk);
    }
}
