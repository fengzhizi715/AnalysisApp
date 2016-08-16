package cn.magicwindow.analysisapp.xml.model;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by tony on 16/8/3.
 */
@Data
@Root(strict = false)
public class ApplicationEntry {

    @Attribute(name = "name", required = false)
    private String name;

    @ElementList(entry = "activity", inline = true)
    private List<ActivityEntry> activities;

    @ElementList(entry = "meta-data", inline = true, required = false)
    private List<MetaDataEntry> metaDatas;

    @ElementList(entry = "service", inline = true, required = false)
    private List<ServiceEntry> services;

    @ElementList(entry = "receiver", inline = true, required = false)
    private List<ReceiverEntry> receivers;

    public String getName() {
        return name;
    }

    public List<ActivityEntry> getActivities() {
        return activities;
    }

    public List<MetaDataEntry> getMetaDatas() {
        return metaDatas;
    }

    public List<ServiceEntry> getServices() {
        return services;
    }

    public List<ReceiverEntry> getReceivers() {
        return receivers;
    }
}
