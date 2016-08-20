package cn.magicwindow.analysisapp.analysis.dfa;

import cn.magicwindow.analysisapp.AppInfo;
import cn.magicwindow.analysisapp.SDK;
import cn.magicwindow.analysisapp.analysis.ActivityRequest;

/**
 * Created by tony on 16/8/11.
 */
public enum TencentStates implements State {

    Init {
        public State next(ActivityRequest request) {

            if (request.getActivity()!=null) {

                return ACTIVITY_STATE;
            } else if (request.getMetadata()!=null) {

                return METADATA_STATE;
            } else if (request.getService()!=null) {

                return SERVICE_STATE;
            } else if (request.getReceiver()!=null) {

                return RECEIVER_STATE;
            }

            return FailingState.Fail;
        }
    },

    ACTIVITY_STATE {
        public State next(ActivityRequest request) {

            if (request.getActivity().getName() != null) {

                if (request.getActivity().getName().equals("com.tencent.tauth.AuthActivity")) {
                    SDK sdk = new SDK("QQ互联sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getActivity().getName().contains("WXEntryActivity")) {
                    SDK sdk = new SDK("微信分享sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getActivity().getName().contains("WXPayEntryActivity")) {
                    SDK sdk = new SDK("微信支付sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getActivity().getName().equals("com.tencent.weibo.webview.OAuthV2AuthorizeWebView")
                        || request.getActivity().getName().equals("com.tencent.weibo.sdk.android.component.Authorize")) {
                    SDK sdk = new SDK("腾讯微博sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getActivity().getName().equals("com.qq.e.ads.ADActivity")) {
                    SDK sdk = new SDK("广点通sdk",true);
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getActivity().getName().equals("com.tencent.midas.qq.APReidasQQWalletActivity")) {
                    SDK sdk = new SDK("qq钱包sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getActivity().getName().equals("com.tencent.assistant.oem.superapp.activity.HomeActivity")) {
                    SDK sdk = new SDK("应用宝积分墙sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getActivity().getName().equals("com.tencent.smtt.sdk.VideoActivity")) {
                    SDK sdk = new SDK("腾讯浏览服务TBS sdk");
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

                if (request.getMetadata().getName().equals("APPKEY_DENGTA")) {
                    SDK sdk = new SDK("腾讯灯塔sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getMetadata().getName().equals("TencentMapSDK")) {
                    SDK sdk = new SDK("腾讯地图sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getMetadata().getName().equals("TA_APPKEY")) {
                    SDK sdk = new SDK("腾讯MTA sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getMetadata().getName().equals("WX_APP_ID")) {
                    SDK sdk = new SDK("微信sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getMetadata().getName().equals("BUGLY_APPID")) {
                    SDK sdk = new SDK("Bugly sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                }
            }
            return FailingState.Fail;
        }
    },

    SERVICE_STATE {

        public State next(ActivityRequest request) {

            if (request.getService().getName() != null) {
                if (request.getService().getName().equals("com.tencent.qalsdk.service.QalService")) {
                    SDK sdk = new SDK("腾讯云通信sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getService().getName().equals("com.tencent.tmassistantsdk.downloadservice.TMAssistantDownloadSDKService")) {
                    SDK sdk = new SDK("应用宝省流量更新sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                } else if (request.getService().getName().equals("com.tencent.android.tpush.service.XGPushService")
                        || request.getService().getName().equals("com.tencent.android.tpush.rpc.XGRemoteService")) {
                    SDK sdk = new SDK("信鸽Push sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                }
            }

            return FailingState.Fail;
        }
    },

    RECEIVER_STATE {

        public State next(ActivityRequest request) {

            if (request.getReceiver().getName() != null) {
                if (request.getReceiver().getName().equals("com.tencent.msdk.dns.HttpDnsCache$ConnectivityChangeReceiver")) {
                    SDK sdk = new SDK("腾讯云智营防劫持sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    return AcceptingState.Accept;
                }
            }

            return FailingState.Fail;
        }
    };

    public abstract State next(ActivityRequest request);
}
