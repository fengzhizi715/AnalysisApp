package cn.magicwindow.analysisapp;

import cn.magicwindow.analysisapp.analysis.*;
import cn.magicwindow.analysisapp.analysis.handler.BaseHandler;
import cn.magicwindow.analysisapp.func.Fn;
import cn.magicwindow.analysisapp.func.functions.Action1;
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
        final BaseHandler handler = analysis.getFirstHandler();

        if (handler==null) {
            return;
        }

        AppInfo appInfo = AppInfo.getInstance();
        int processCount = appInfo.getProcessCount();

        File file = new File("/Users/tony/jadx/apk-tool/com.oppo.community_5.4.3_50403/AndroidManifest.xml");
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

                Fn.forEach(new Action1<ActivityEntry>(){

                    public void call(ActivityEntry activity) {
                        handler.handleRequest(new ActivityRequest(activity));
                    }
                },androidManifest.application.getActivities());
                
                Fn.forEach(new Action1<MetaDataEntry>() {

                    public void call(MetaDataEntry metadata) {
                        handler.handleRequest(new ActivityRequest(metadata));
                    }
                },androidManifest.application.getMetaDatas());

                Fn.forEach(new Action1<ReceiverEntry>() {

                    public void call(ReceiverEntry receiver) {
                        handler.handleRequest(new ActivityRequest(receiver));
                    }
                },androidManifest.application.getReceivers());

                final Multimap<String,ServiceEntry> multimap = ArrayListMultimap.create();
                Fn.forEach(new Action1<ServiceEntry>() {

                    public void call(ServiceEntry service) {
                        if (service.getProcess()!=null) {
                            multimap.put(service.getProcess(),service);
                        }

                        handler.handleRequest(new ActivityRequest(service));
                    }
                },androidManifest.application.getServices());

                if (multimap.keySet()!=null) {
                    processCount+=multimap.keySet().size();
                    appInfo.setProcessCount(processCount);
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
