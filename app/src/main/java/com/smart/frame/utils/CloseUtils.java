package com.smart.frame.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Description: 关闭工具
 * @author Zijin
 * @date 2017/8/1
 */

public class CloseUtils {
    public static void close(Closeable target){
        try {
            if(target != null){
                target.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
