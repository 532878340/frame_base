package com.smart.frame.utils;

import android.util.Log;

import com.smart.frame.BuildConfig;
import com.smart.frame.manager.constants.Configs;


/**
 * description：日志工具
 * author：JimG on 17/5/4 14:41
 * e-mail：info@zijinqianbao@qq.com
 */

public class Logger {
    public static final boolean isDebug = BuildConfig.DEBUG_ENABLE;

    /**
     * warn
     */
    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        }
    }

    /**
     * info
     */
    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    /**
     * error
     */
    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    /**
     * debug
     */
    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    /**
     * verbose
     */
    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, msg);
        }
    }
}
