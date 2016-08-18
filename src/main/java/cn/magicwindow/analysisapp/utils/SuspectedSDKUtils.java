package cn.magicwindow.analysisapp.utils;

import cn.magicwindow.analysisapp.AppInfo;
import cn.magicwindow.analysisapp.analysis.ActivityRequest;

/**
 * Created by tony on 16/8/19.
 */
public class SuspectedSDKUtils {

    public static void saveRequest(ActivityRequest request) {

        if (request.getMetadata()!=null) {
            AppInfo.getInstance().addSuspectedSDK(request.getMetadata());
        } else if (request.getActivity()!=null && request.getActivity().getName()!=null) {
            if (!(request.getActivity().getName().startsWith(".")
                    || request.getActivity().getName().startsWith(AppInfo.getInstance().getPackageName()))) {

                AppInfo.getInstance().addSuspectedSDK(request.getActivity());
            }
        }
    }
}
