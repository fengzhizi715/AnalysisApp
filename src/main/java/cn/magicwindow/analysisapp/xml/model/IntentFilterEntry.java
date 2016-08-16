package cn.magicwindow.analysisapp.xml.model;

import lombok.Data;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by tony on 16/8/3.
 */
@Data
@Root(strict = false)
public class IntentFilterEntry {

    @ElementList(entry = "action", inline = true, required = false)
    private List<IntentAction> actions;

    @ElementList(entry = "category", inline = true, required = false)
    private List<IntentCategory> categories;
}
