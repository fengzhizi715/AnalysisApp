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

        File file = new File("/Users/tony/jadx/apk-tool/com.gotokeep.keep_3.9.0_3865/AndroidManifest.xml");
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
                AppInfo.getInstance().setPackageName(packageName);

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

                System.out.println(appInfo);

                if (Preconditions.isNotBlank(appInfo.getSuspectedSDKs())) {
                    Collections.sort(appInfo.getSuspectedSDKs(), new Comparator() {
                        public int compare(Object o1, Object o2) {
                            SuspectedSDK suspectedSDK1 = (SuspectedSDK) o1;
                            SuspectedSDK suspectedSDK2 = (SuspectedSDK) o2;

                            if (suspectedSDK1.getType()!=null && suspectedSDK2.getType()!=null) {
                                return suspectedSDK1.getType().getIndex()-suspectedSDK2.getType().getIndex();
                            }

                            return 0;
                        }
                    });

                    System.out.println("\r\n疑似sdk:");
                    for(SuspectedSDK item:appInfo.getSuspectedSDKs()) {
                        System.out.println(item);
                    }
                }
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
