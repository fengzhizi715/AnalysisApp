package cn.magicwindow.analysisapp;

import cn.magicwindow.analysisapp.utils.Preconditions;
import cn.magicwindow.analysisapp.xml.model.*;

/**
 * 疑似sdk
 * Created by tony on 16/8/18.
 */
public class SuspectedSDK{

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

                ActivityEntry activityEntry = (ActivityEntry)obj;
                sb.append("<activity android:name=\"").append(name).append("\" ");
                if (Preconditions.isNotBlank(activityEntry.intentFilter)) {
                   for (IntentFilterEntry intentFilter:activityEntry.intentFilter) {

                       sb.append("\r\n  ").append("<intent-filter");
                       if (Preconditions.isNotBlank(intentFilter.label)) {
                           sb.append("android:label=\"").append(intentFilter.label).append("\">");
                       } else {
                           sb.append(">");
                       }

                       if (Preconditions.isNotBlank(intentFilter.actions)) {
                           for (IntentAction action:intentFilter.actions) {
                               sb.append("\r\n    ").append("<action android:name=").append(action.name).append("\" />");
                           }
                       }

                       if (Preconditions.isNotBlank(intentFilter.categories)) {
                           for (IntentCategory category:intentFilter.categories) {
                               sb.append("\r\n    ").append("<action android:name=\"").append(category.name).append("\" />");
                           }
                       }

                       sb.append("\r\n  ").append("</intent-filter>");
                   }
                } else {
                    sb.append("/>");
                }

                break;
            default:
                break;
        }

        return sb.toString();
    }

    public Type getType() {
        return type;
    }
}
