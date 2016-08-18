package cn.magicwindow.analysisapp;

import cn.magicwindow.analysisapp.xml.model.MetaDataEntry;

/**
 * 疑似sdk
 * Created by tony on 16/8/18.
 */
public class SuspectedSDK {

    private Type type;
    private String name;
    private Object obj;

    enum Type {

        METADATA(1),
        ACTIVITY(2),
        SERVICE(3),
        RECEIVER(4);

        private int index;

        Type(int index) {
            this.index = index;
        }
    }

    public SuspectedSDK(Object obj) {

        if (obj instanceof MetaDataEntry) {
            this.name = ((MetaDataEntry) obj).getName();
            this.obj = obj;
            this.type = Type.METADATA;
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

                break;
            default:
                break;
        }

        return sb.toString();
    }
}
