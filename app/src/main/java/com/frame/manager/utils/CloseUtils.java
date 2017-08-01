package com.frame.manager.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Description: 关闭工具
 * Created by Zijin on 2017/8/1.
 * Email: info@zijinqianbao.com
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
