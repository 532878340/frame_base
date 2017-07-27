package com.frame.manager.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Zijin on 2017/7/26.
 * Email:info@zijinqianbao.com
 */

public class AppUtils {
    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取系统版本信息
     * 版本名：info.versionName
     * 版本号：info.versionCode
     */
    public static PackageInfo getVersionCode(Context context) {
        String packageName = context.getApplicationContext().getPackageName();
        try {
            return context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        DateFormatUtils.formatTime(1000,DateFormatUtils.FORMAT_STARNDARD);
        return null;
    }
}
