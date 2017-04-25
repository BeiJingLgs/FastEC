package com.diabin.latte.util.log;

import com.orhanobut.logger.Logger;

/**
 * Created by 傅令杰 on 2017/4/22
 */

public class LatteLogger {

    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static final int NOTHING = 6;

    //控制log等级
    private static int LEVEL = VERBOSE;

    public static void v(String tag, String meg) {
        if (LEVEL <= VERBOSE) {
            Logger.t(tag).v(meg);
        }
    }

    public static void d(String tag, Object meg) {
        if (LEVEL <= DEBUG) {
            Logger.t(tag).d(meg);
        }
    }

    public static void i(String tag, String meg) {
        if (LEVEL <= INFO) {
            Logger.t(tag).i(meg);
        }
    }

    public static void w(String tag, String meg) {
        if (LEVEL <= WARN) {
            Logger.t(tag).w(meg);
        }
    }

    public static void json(String tag, String meg) {
        if (LEVEL <= WARN) {
            Logger.t(tag).json(meg);
        }
    }

    public static void e(String tag, String meg) {
        if (LEVEL <= ERROR) {
            Logger.t(tag).e(meg);
        }
    }
}
