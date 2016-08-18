package cn.magicwindow.analysisapp.xml.model;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by tony on 16/8/8.
 */
@Data
@Root(strict = false)
public class MetaDataEntry {

    @Attribute(name = "name", required = false)
    private String name;

    @Attribute(name = "value", required = false)
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
