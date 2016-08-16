package cn.magicwindow.analysisapp.analysis.dfa;

import cn.magicwindow.analysisapp.analysis.ActivityRequest;

/**
 * Created by tony on 16/8/11.
 */
public enum FailingState implements FinalState {

    Fail {
        public State next(ActivityRequest request) {
            return Fail;
        }
    };
}
