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
public class ActivityEntry {

    @Attribute(name = "name", required = false)
    private String name;

    @Attribute(name = "excludeFromRecents", required = false)
    private String excludedFromRecents;

    @ElementList(entry = "intent-filter", inline = true, required = false)
    private List<IntentFilterEntry> intentFilter;

    public String getName() {
        return name;
    }
}
