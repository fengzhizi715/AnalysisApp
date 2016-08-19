package cn.magicwindow.analysisapp

import cn.magicwindow.analysisapp.utils.Preconditions
import cn.magicwindow.analysisapp.xml.model.ActivityEntry
import cn.magicwindow.analysisapp.xml.model.IntentAction
import cn.magicwindow.analysisapp.xml.model.IntentCategory
import cn.magicwindow.analysisapp.xml.model.IntentFilterEntry
import cn.magicwindow.analysisapp.xml.model.MetaDataEntry
import cn.magicwindow.analysisapp.xml.model.ReceiverEntry
import cn.magicwindow.analysisapp.xml.model.ServiceEntry

/**
 * 疑似sdk
 * Created by tony on 16/8/19.
 */
class SuspectedSDK {

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
        } else if (obj instanceof ServiceEntry) {
            this.name = ((ServiceEntry) obj).getName();
            this.obj = obj;
            this.type = Type.SERVICE;
        } else if (obj instanceof ReceiverEntry) {
            this.name = ((ReceiverEntry) obj).getName();
            this.obj = obj;
            this.type = Type.RECEIVER;
        }
    }

    @Override
    String toString() {

        StringBuilder sb = new StringBuilder();

        switch (type) {
            case Type.METADATA:
                String value = ((MetaDataEntry)obj).getValue();
                def metadata = """<meta-data android:name="$name" android:value="$value"/>"""
                sb.append(metadata);
                break;

            case Type.ACTIVITY:

                ActivityEntry activityEntry = (ActivityEntry)obj;
                sb.append("<activity android:name=\"").append(name).append("\" ");
                if (Preconditions.isNotBlank(activityEntry.intentFilter)) {
                    sb.append(">")
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
                        sb.append("\r\n").append("</activity>");
                    }
                } else {
                    sb.append("/>");
                }

                break;

            case Type.SERVICE:

                ServiceEntry serviceEntry = (ServiceEntry)obj;
                def service = """<service android:name="$name" """;
                if (Preconditions.isNotBlank(serviceEntry.getProcess())) {
                    def procressName = serviceEntry.process
                    service += """android:process="$procressName" />"""
                } else {
                    service += "/>";
                }
                sb.append(service);
                break;

            case Type.RECEIVER:

                def receiver = """<receiver android:name="$name" />"""
                sb.append(receiver);
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
