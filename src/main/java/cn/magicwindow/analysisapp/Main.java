package cn.magicwindow.analysisapp;

import cn.magicwindow.analysisapp.analysis.*;
import cn.magicwindow.analysisapp.analysis.handler.BaseHandler;
import cn.magicwindow.analysisapp.xml.model.*;
import cn.magicwindow.analysisapp.xml.XmlHandler;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.List;

/**
 * Created by tony on 16/8/8.
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Analysis analysis = (Analysis)context.getBean("analysis");
        BaseHandler handler = analysis.getFirstHandler();

        if (handler==null) {
            return;
        }

        AppInfo appInfo = AppInfo.getInstance();
        int processCount = appInfo.getProcessCount();

        File file = new File("/Users/tony/jadx/apk-tool/com.yibasan.lizhifm_3.8.8_93695/AndroidManifest.xml");
        InputStream in = null;
        if (file!=null) {
            try {
                in = new FileInputStream(file);
                XmlHandler xmlHandler = new XmlHandler();
                AndroidManifest androidManifest = (AndroidManifest) xmlHandler.parse(AndroidManifest.class,in);

                ActivityRequest request;
                List<ActivityEntry> activities = androidManifest.getApplication().getActivities();
                if (activities!=null && activities.size()>0) {
                    for (ActivityEntry activity:activities) {
                        request = new ActivityRequest(activity);
                        handler.handleRequest(request);
                    }
                }

                List<MetaDataEntry> metadatas = androidManifest.getApplication().getMetaDatas();
                if (metadatas!=null && metadatas.size()>0) {
                    for (MetaDataEntry metadata:metadatas) {
                        request = new ActivityRequest(metadata);
                        handler.handleRequest(request);
                    }
                }

                List<ReceiverEntry> receivers = androidManifest.getApplication().getReceivers();
                if (receivers!=null && receivers.size()>0) {
                    for (ReceiverEntry receiver:receivers) {
                        request = new ActivityRequest(receiver);
                        handler.handleRequest(request);
                    }
                }

                List<ServiceEntry> services = androidManifest.getApplication().getServices();
                if (services!=null && services.size()>0) {
                    Multimap<String,ServiceEntry> multimap = ArrayListMultimap.create();
                    for (ServiceEntry service:services) {

                        if (service.getProcess()!=null) {
                            multimap.put(service.getProcess(),service);
                        }
                        request = new ActivityRequest(service);
                        handler.handleRequest(request);
                    }

                    if (multimap.keySet()!=null) {
                        processCount+=multimap.keySet().size();
                        appInfo.setProcessCount(processCount);
                    }
                }

                System.out.println(appInfo);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (in !=null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
