package cn.magicwindow.analysisapp.analysis;

import cn.magicwindow.analysisapp.xml.model.ActivityEntry;
import cn.magicwindow.analysisapp.xml.model.MetaDataEntry;
import cn.magicwindow.analysisapp.xml.model.ReceiverEntry;
import cn.magicwindow.analysisapp.xml.model.ServiceEntry;

/**
 * Created by tony on 16/8/8.
 */
public class ActivityRequest {

    private ActivityEntry activity;
    private MetaDataEntry metadata;
    private ServiceEntry service;
    private ReceiverEntry receiver;

    public ActivityRequest(ActivityEntry activity) {
        this.activity = activity;
    }

    public ActivityRequest(MetaDataEntry metadata) {
        this.metadata = metadata;
    }

    public ActivityRequest(ServiceEntry service) {
        this.service = service;
    }

    public ActivityRequest(ReceiverEntry receiver) {
        this.receiver = receiver;
    }

    public ActivityEntry getActivity() {
        return activity;
    }

    public MetaDataEntry getMetadata() {
        return metadata;
    }

    public ServiceEntry getService() {
        return service;
    }

    public ReceiverEntry getReceiver() {
        return receiver;
    }
}
