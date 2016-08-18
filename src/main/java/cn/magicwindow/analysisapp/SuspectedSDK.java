package cn.magicwindow.analysisapp;

import cn.magicwindow.analysisapp.xml.model.ActivityEntry;
import cn.magicwindow.analysisapp.xml.model.IntentFilterEntry;
import cn.magicwindow.analysisapp.xml.model.MetaDataEntry;

/**
 * 疑似sdk
 * Created by tony on 16/8/18.
 */
public class SuspectedSDK {

    private Type type;
    private String name;
    private Object obj;

    public SuspectedSDK(Object obj) {

        if (obj instanceof MetaDataEntry) {
            this.name = ((MetaDataEntry) obj).getName();
            this.obj = obj;
            this.type = Type.METADATA;
        } else if (obj instanceof ActivityEntry) {
            this.name = ((ActivityEntry) obj).getName();
            this.obj = obj;
            this.type = Type.ACTIVITY;
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        switch (type) {
            case METADATA:
                String value = ((MetaDataEntry)obj).getValue();
                sb.append("<meta-data android:name=\"").append(name).append("\" ").append("android:value=\"").append(value).append("\"/>");
                break;

            case ACTIVITY:

//                ActivityEntry activityEntry = (ActivityEntry)obj;
//                sb.append("<activity android:name=\"").append(name).append("\" ");
//                if (activityEntry.intentFilter!=null && activityEntry.intentFilter.size()>0) {
//                   for (IntentFilterEntry intentFilter:activityEntry.intentFilter) {
//
//                       sb.append("<intent-filter");
//                   }
//                }

                break;
            default:
                break;
        }

        return sb.toString();
    }
}
