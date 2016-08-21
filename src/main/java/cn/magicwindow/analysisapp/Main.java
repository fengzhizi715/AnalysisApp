package cn.magicwindow.analysisapp;

import cn.magicwindow.analysisapp.analysis.*;
import cn.magicwindow.analysisapp.analysis.handler.BaseHandler;
import cn.magicwindow.analysisapp.utils.Preconditions;
import cn.magicwindow.analysisapp.xml.model.*;
import cn.magicwindow.analysisapp.xml.XmlHandler;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
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

        File file = new File("/Users/tony/jadx/apk-tool/1E06D1426CB2DFAB4EE4A459C5B4C071/AndroidManifest.xml");
        InputStream in = null;
        if (file!=null) {
            try {
                in = new FileInputStream(file);
                XmlHandler xmlHandler = new XmlHandler();
                AndroidManifest androidManifest = (AndroidManifest) xmlHandler.parse(AndroidManifest.class,in);

                if (androidManifest==null || androidManifest.application==null) {
                    return;
                }

                String packageName = androidManifest.packageName;
                appInfo.setPackageName(packageName);

                ActivityRequest request;
                List<ActivityEntry> activities = androidManifest.application.getActivities();
                if (Preconditions.isNotBlank(activities)) {
                    for (ActivityEntry activity:activities) {
                        request = new ActivityRequest(activity);
                        handler.handleRequest(request);
                    }
                }

                List<MetaDataEntry> metadatas = androidManifest.application.getMetaDatas();
                if (Preconditions.isNotBlank(metadatas)) {
                    for (MetaDataEntry metadata:metadatas) {
                        request = new ActivityRequest(metadata);
                        handler.handleRequest(request);
                    }
                }

                List<ReceiverEntry> receivers = androidManifest.application.getReceivers();
                if (Preconditions.isNotBlank(receivers)) {
                    for (ReceiverEntry receiver:receivers) {
                        request = new ActivityRequest(receiver);
                        handler.handleRequest(request);
                    }
                }

                List<ServiceEntry> services = androidManifest.application.getServices();
                if (Preconditions.isNotBlank(services)) {
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

                // debug模式可以打印出疑似sdk
                appInfo.setDebug(true);
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
