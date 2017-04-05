package cn.magicwindow.analysisapp;

import cn.magicwindow.analysisapp.analysis.*;
import cn.magicwindow.analysisapp.analysis.handler.BaseHandler;
import cn.magicwindow.analysisapp.xml.model.*;
import cn.magicwindow.analysisapp.xml.XmlHandler;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.safframwork.tony.common.utils.Preconditions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.List;

/**
 * 程序入口
 * Created by tony on 16/8/8.
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Analysis analysis = (Analysis)context.getBean("analysis");
        final BaseHandler handler = analysis.getFirstHandler();

        if (handler==null) {
            return;
        }

        AppInfo appInfo = AppInfo.getInstance();
        int processCount = appInfo.getProcessCount();

        File file = new File("AndroidManifest.xml");
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

                List<ActivityEntry> activities = androidManifest.application.getActivities();
                if (Preconditions.isNotBlank(activities)) {

                    activities.parallelStream().forEach(
                            activityEntry -> handler.handleRequest(new ActivityRequest(activityEntry))
                    );
                }

                List<MetaDataEntry> metadatas = androidManifest.application.getMetaDatas();
                if (Preconditions.isNotBlank(metadatas)) {

                    metadatas.parallelStream().forEach(
                            metaDataEntry -> handler.handleRequest(new ActivityRequest(metaDataEntry))
                    );
                }

                List<ReceiverEntry> receivers = androidManifest.application.getReceivers();
                if (Preconditions.isNotBlank(receivers)) {

                    receivers.parallelStream().forEach(
                            receiverEntry -> handler.handleRequest(new ActivityRequest(receiverEntry))
                    );
                }

                List<ServiceEntry> services = androidManifest.application.getServices();
                Multimap<String,ServiceEntry> multimap = ArrayListMultimap.create();
                if (Preconditions.isNotBlank(services)) {

                    services.parallelStream().forEach(
                            serviceEntry -> {
                                if (serviceEntry.process!=null) {
                                    multimap.put(serviceEntry.process, serviceEntry);
                                }
                                handler.handleRequest(new ActivityRequest(serviceEntry));
                            }
                    );

                    if (multimap.keySet()!=null) {
                        processCount+=multimap.keySet().size();
                        appInfo.setProcessCount(processCount);
                    }
                }

                // debug模式可以打印出疑似sdk
//                appInfo.setDebug(true);
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
