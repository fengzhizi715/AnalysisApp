package cn.magicwindow.analysisapp


import cn.magicwindow.analysisapp.xml.model.ActivityEntry
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
                def activty = """<activity android:name="$name" """
                if (Preconditions.isNotBlank(activityEntry.intentFilter)) {
                    activty += ">"

                    activityEntry.intentFilter.each {
                        activty += "\r\n  <intent-filter"
                        if (Preconditions.isNotBlank(it.label)) {
                            def label = it.label
                            activty += """ android:label="$label" >"""
                        } else {
                            activty += ">"
                        }

                        if (Preconditions.isNotBlank(it.actions)) {
                            it.actions.each {
                                 intentAction ->
                                    def intentActionName = intentAction.name
                                    activty +="""\r\n    <action android:name="$intentActionName" />"""
                            }
                        }

                        if (Preconditions.isNotBlank(it.categories)) {
                            it.categories.each {
                                 intentCategory ->
                                    def intentCategoryName = intentCategory.name
                                    activty +="""\r\n    <category android:name="$intentCategoryName" />"""
                            }
                        }

                        activty +="\r\n  </intent-filter>"
                    }
                    activty += "\r\n</activity>"
                } else {
                    activty += "/>"
                }
                sb.append(activty);
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
