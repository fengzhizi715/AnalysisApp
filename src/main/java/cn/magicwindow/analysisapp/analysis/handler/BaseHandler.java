package cn.magicwindow.analysisapp.analysis.handler;

import cn.magicwindow.analysisapp.AppInfo;
import cn.magicwindow.analysisapp.analysis.ActivityRequest;

/**
 * Created by tony on 16/8/8.
 */
public abstract class BaseHandler {

    // 责任链的下一个节点，即处理者
    private BaseHandler nextHandler = null;

    // 捕获具体请求并进行处理，或是将请求传递到责任链的下一级别
    public final void handleRequest(ActivityRequest request) {

        if (request == null) {
            return;
        }

        if (!handle(request)) {
            // 当前处理者不能胜任，则传递至职责链的下一节点
            if (this.nextHandler != null) {
                // 这里使用了递归调用
                this.nextHandler.handleRequest(request);
            } else {

                // 对疑似sdk单独收集
                if (request.getMetadata()!=null) {
                    AppInfo.getInstance().addSuspectedSDK(request.getMetadata());
                } else if (request.getActivity()!=null && request.getActivity().getName()!=null) {
                    if (!(request.getActivity().getName().startsWith(".")
                            || request.getActivity().getName().startsWith(AppInfo.getInstance().getPackageName()))) {

                        AppInfo.getInstance().addSuspectedSDK(request.getActivity());
                    }
                }
            }
        }
    }

    // 设置责任链中的下一个处理者
    public void setNextHandler(BaseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // 定义链中每个处理者具体的处理方式
    protected abstract boolean handle(ActivityRequest request);
}
