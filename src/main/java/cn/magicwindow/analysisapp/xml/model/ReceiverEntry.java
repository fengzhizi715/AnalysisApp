package cn.magicwindow.analysisapp.xml.model;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by tony on 16/8/10.
 */
@Data
@Root(strict = false)
public class ReceiverEntry {

    @Attribute(name = "name", required = true)
    private String name;

    @Attribute(name = "exported", required = false)
    private String exported;

    @Attribute(name = "enabled", required = false)
    private String enabled;

    public String getName() {
        return name;
    }
}
