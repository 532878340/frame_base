package com.smart.frame.utils;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Description: 系统工具类
 * @author Zijin
 * @date 2017/12/19
 */

public class SystemUtil {
    /**
     * 获取进程号对应的进程名
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            CloseUtils.close(reader);
        }
        return null;
    }
}
