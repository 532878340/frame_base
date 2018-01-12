package com.smart.frame.manager.constants;

import com.smart.frame.BuildConfig;

/**
 * 配置类
 * @author Gjm
 * @date 2018/01/12
 */

public class Configs {
    /**
     * 是否为调试模式
     */
    public static final boolean DEBUG_ENABLE = BuildConfig.DEBUG;

    /**
     * 根目录
     */
    public static final String BASE_URL = BuildConfig.API_URL;

    /**
     * 请求超时时间
     */
    public static final long REQUEST_TIMEOUT = 20 * 1000;

    /**
     * 防抖设置
     */
    public static final long THROTTLE_DELAY = 2000;

    /**
     * Bugly App ID
     */
    public static final String APP_ID_BUGLY = "cced09bba0";
}
