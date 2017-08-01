package com.frame.manager.utils.imageloader.config;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.bumptech.glide.MemoryCategory;
import com.frame.manager.constants.Configs;
import com.frame.manager.utils.imageloader.loader.glide.GlideLoader;
import com.frame.manager.utils.imageloader.loader.ILoader;

/**
 * Description: ImageLoader配置
 * Created by Zijin on 2017/7/31.
 * Email: info@zijinqianbao.com
 */

public class GlobalConfig {
    /**
     * 图片基础目录
     */
    public static final String BASE_URL = Configs.BASE_URL;

    private static Context mContext;

    /**
     * lrucache 最大值
     */
    public static int mCacheMaxSize;

    public static void init(Context context, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD) {
        mContext = context;
        mCacheMaxSize = cacheSizeInM;

        getLoader().init(context, cacheSizeInM, memoryCategory, isInternalCD);
    }

    private static ILoader mLoader;

    /**
     * 实际Loader加载器
     */
    public static ILoader getLoader() {
        if (mLoader == null) {
            mLoader = new GlideLoader();
        }
        return mLoader;
    }

    private static Handler mHandler;

    /**
     * 返回主线程Handler
     */
    public static Handler getMainHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }

        return mHandler;
    }

    public static Context getContext() {
        return mContext;
    }
}