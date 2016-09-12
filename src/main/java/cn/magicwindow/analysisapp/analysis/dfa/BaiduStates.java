package cn.magicwindow.analysisapp.analysis.dfa;

import cn.magicwindow.analysisapp.AppInfo;
import cn.magicwindow.analysisapp.SDK;
import cn.magicwindow.analysisapp.analysis.ActivityRequest;

/**
 * Created by tony on 16/8/12.
 */
public enum BaiduStates implements State {

    Init {
        public State next(ActivityRequest request) {

            if (request.getActivity()!=null) {

                return ACTIVITY_STATE;
            } else if (request.getMetadata()!=null) {

                return METADATA_STATE;
            } else if (request.getService()!=null) {

                return SERVICE_STATE;
            }

            return FailingState.Fail;
        }
    },

    ACTIVITY_STATE {
        public State next(ActivityRequest request) {

            if (request.getActivity().getName() != null) {

                if (request.getActivity().getName().equals("com.baidu.wallet.paysdk.ui.WelcomeActivity")) {
                    SDK sdk = new SDK("百度钱包sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getActivity().getName().equals("com.baidu.mobad.feeds.BaiduActivity")) {
                    SDK sdk = new SDK("百度MSSP信息流广告",true);
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                }
            }
            return FailingState.Fail;
        }
    },

    METADATA_STATE {
        public State next(ActivityRequest request) {

            if (request.getMetadata().getName() != null) {

                if (request.getMetadata().getName().equals("com.baidu.lbsapi.API_KEY")) {
                    SDK sdk = new SDK("百度地图sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getMetadata().getName().equals("BaiduMobAd_APP_ID")) {
                    SDK sdk = new SDK("百度广告联盟sdk",true);
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getMetadata().getName().equals("BaiduMobAd_STAT_ID")) {
                    SDK sdk = new SDK("百度移动统计sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                }
            }
            return FailingState.Fail;
        }
    },

    SERVICE_STATE {

        public State next(ActivityRequest request) {

            if (request.getService().name !=null) {
                if (request.getService().name.equals("com.baidu.android.pushservice.PushService")) {
                    SDK sdk = new SDK("百度云推送sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                }
            }

            return FailingState.Fail;
        }
    };

    public abstract State next(ActivityRequest request);
}
