package cn.magicwindow.analysisapp.xml.model;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by tony on 16/8/8.
 */
@Data
@Root(strict = false)
public class ServiceEntry {

    @Attribute(name = "name", required = true)
    private String name;

    @Attribute(name = "permission", required = false)
    private String permission;

    @Attribute(name = "process", required = false)
    private String process;

    @Attribute(name = "exported", required = false)
    private String exported;

    @Attribute(name = "enabled", required = false)
    private String enabled;

    public String getName() {
        return name;
    }

    public String getProcess() {
        return process;
    }
}
