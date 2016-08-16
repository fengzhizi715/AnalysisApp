package cn.magicwindow.analysisapp.analysis.handler

import cn.magicwindow.analysisapp.analysis.ActivityRequest
import cn.magicwindow.analysisapp.analysis.dfa.AcceptingState
import cn.magicwindow.analysisapp.analysis.dfa.FinalState
import cn.magicwindow.analysisapp.analysis.dfa.State
import cn.magicwindow.analysisapp.analysis.dfa.TencentStates
import org.springframework.stereotype.Service

/**
 * Created by tony on 16/8/12.
 */
@Service
class TencentHandler extends BaseHandler {

    protected boolean handle(ActivityRequest request) {

        State s;

        for (s = TencentStates.Init; !(s instanceof FinalState); s=s.next(request)) {
        }

        return s == AcceptingState.Accept;
    }
}
