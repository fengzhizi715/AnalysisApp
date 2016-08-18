package cn.magicwindow.analysisapp.xml.model;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by tony on 16/8/3.
 */
@Data
@Root(name = "manifest", strict = false)
public class AndroidManifest {

    @Attribute(name = "package", required = true)
    public String packageName;

    @Element(name = "application")
    public ApplicationEntry application;
}
