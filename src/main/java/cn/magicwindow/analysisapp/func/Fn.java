package cn.magicwindow.analysisapp.func;

import cn.magicwindow.analysisapp.func.functions.Action1;
import cn.magicwindow.analysisapp.func.functions.Func1;
import cn.magicwindow.analysisapp.utils.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony Shen on 16/9/9.
 */

public class Fn {

    public static <T, R> void forEach(Action1<? super T> func, Iterable<T> iterable) {

        if (Preconditions.isNotBlank(iterable)) {
            for (T t : iterable) {
                func.call(t);
            }
        }
    }

    public static <T, R> List<R> map(Func1<? super T, ? extends R> func, List<T> list) {
        List<R> r = new ArrayList<R>();
        if (Preconditions.isNotBlank(list)) {
            for (T l : list) {
                r.add(func.call(l));
            }
        }

        return r;
    }
}
