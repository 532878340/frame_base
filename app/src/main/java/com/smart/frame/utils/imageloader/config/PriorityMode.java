package com.smart.frame.utils.imageloader.config;

/**
 * Description: 图片加载优先级
 * Created by Zijin on 2017/8/1.
 * Email: info@zijinqianbao.com
 */

public interface PriorityMode {
    /**
     * 低
     */
    int PRIORITY_LOW = 1;
    /**
     * 普通
     */
    int PRIORITY_NORMAL = 2;
    /**
     * 高
     */
    int PRIORITY_HIGH = 3;
    /**
     * 立即执行
     */
    int PRIORITY_IMMEDIATE = 4;
}
