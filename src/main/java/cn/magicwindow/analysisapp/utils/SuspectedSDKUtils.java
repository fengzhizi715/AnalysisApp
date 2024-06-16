package cn.magicwindow.analysisapp.utils;

import cn.magicwindow.analysisapp.AppInfo;
import cn.magicwindow.analysisapp.analysis.ActivityRequest;

/**
 * Created by tony on 16/8/19.
 */
public class SuspectedSDKUtils {

    public static void saveRequest(ActivityRequest request) {

        if (request.getMetadata() != null) {

            AppInfo.getInstance().addSuspectedSDK(request.getMetadata());
        } else if (request.getActivity() != null && request.getActivity().getName() != null) {
            if (!(request.getActivity().getName().startsWith(".")
                    || request.getActivity().getName().startsWith(AppInfo.getInstance().getPackageName()))) {

                AppInfo.getInstance().addSuspectedSDK(request.getActivity());
            } else {

                if (Preconditions.isNotBlank(request.getActivity().intentFilter)) {
                    AppInfo.getInstance().addSuspectedSDK(request.getActivity());
                }
            }
        }  else if (request.getService() != null && request.getService().name != null) {
            if (!(request.getService().name.startsWith(".")
                    || request.getService().name.startsWith(AppInfo.getInstance().getPackageName()))) {

                AppInfo.getInstance().addSuspectedSDK(request.getService());
            }
        } else if (request.getReceiver() !=null && request.getReceiver().getName() !=null) {
            if (!(request.getReceiver().getName().startsWith(".")
                    || request.getReceiver().getName().startsWith(AppInfo.getInstance().getPackageName()))) {

                AppInfo.getInstance().addSuspectedSDK(request.getReceiver());
            }
        }
    }
}
