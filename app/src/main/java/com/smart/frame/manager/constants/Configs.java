package com.smart.frame.manager.constants;

import com.smart.frame.BuildConfig;

/**
 * 配置类
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

public class Configs {
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
}
