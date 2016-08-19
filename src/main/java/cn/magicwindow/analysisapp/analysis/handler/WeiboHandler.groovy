package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.AppInfo
import cn.magicwindow.analysisapp.SDK
import cn.magicwindow.analysisapp.analysis.ActivityRequest
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/13.
 */
@Service
class WeiboHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        boolean result = false;

        request?.activity?.any {
            activity ->
                if (activity.name == "com.sina.weibo.sdk.component.WeiboSdkBrowser") {
                    def sdk = new SDK("新浪微博sdk");
                    AppInfo.getInstance().addSDK(sdk);
                    result = true;
                    return true;
                } else {
                    /*  如下的格式:
                       <activity
                           android:name=".DemoActivity"
                           android:configChanges="keyboardHidden|orientation"
                           android:launchMode="singleTask"
                           android:screenOrientation="portrait"
                           android:theme="@android:style/Theme.Black.NoTitleBar" >
                           <intent-filter>
                           <action android:name="com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY"/>
                           <category android:name="android.intent.category.DEFAULT"/>
                           </intent-filter>
                      </activity>*/
                    activity?.intentFilter?.actions?.any {
                        def action ->
                            action?.name?.any {
                                if (it == "com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY") {
                                    def sdk = new SDK("新浪微博sdk");
                                    AppInfo.getInstance().addSDK(sdk);
                                    result = true;
                                    return true;
                                }
                            }
                    }
                }
        }

        return result;
    }
}
