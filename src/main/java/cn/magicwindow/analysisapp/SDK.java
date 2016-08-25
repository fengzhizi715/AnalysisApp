package cn.magicwindow.analysisapp;

import lombok.ToString;

/**
 * Created by tony on 16/8/9.
 */
public class SDK {

    private String sdkName;
    private boolean isAd;

    public SDK(String sdkName) {
        this(sdkName,false);
    }

    public SDK(String sdkName,boolean isAd) {
        this.sdkName = sdkName;
        this.isAd = isAd;
    }

    public String getSdkName() {
        return sdkName;
    }

    public void setSdkName(String sdkName) {
        this.sdkName = sdkName;
    }

    public boolean isAd() {
        return isAd;
    }

    public void setAd(boolean ad) {
        isAd = ad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SDK sdk = (SDK) o;

        if (isAd != sdk.isAd) return false;
        return sdkName.equals(sdk.sdkName);

    }

    @Override
    public int hashCode() {
        int result = sdkName.hashCode();
        result = 31 * result + (isAd ? 1 : 0);
        return result;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        if (isAd()) {
            sb.append(getSdkName()).append("(广告sdk)");
        } else {
            sb.append(getSdkName());
        }

        return sb.toString();
    }
}
