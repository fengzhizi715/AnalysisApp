package cn.magicwindow.analysisapp.xml;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.InputStream;

/**
 * Created by tony on 16/8/3.
 */
public class XmlHandler<T> {

    public T parse(Class<T> resultClass, String xmlString){
        Serializer serializer = new Persister();

        try {
            return serializer.read(resultClass, xmlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public T parse(Class<T> resultClass, InputStream in){
        Serializer serializer = new Persister();

        try {
            return serializer.read(resultClass, in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
