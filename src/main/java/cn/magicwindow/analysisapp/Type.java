package cn.magicwindow.analysisapp;

/**
 * Created by tony on 16/8/19.
 */
public enum Type {

    METADATA(1),
    ACTIVITY(2),
    SERVICE(3),
    RECEIVER(4);

    private int index;

    Type(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
