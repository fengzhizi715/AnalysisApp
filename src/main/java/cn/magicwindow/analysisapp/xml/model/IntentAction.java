package cn.magicwindow.analysisapp.xml.model;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by tony on 16/8/3.
 */
@Data
@Root(strict = false)
public class IntentAction {

    @Attribute(name = "name")
    private String name;

}
