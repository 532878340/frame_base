package com.frame.manager.utils.imageloader.config;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;

import com.bumptech.glide.MemoryCategory;
import com.frame.manager.utils.imageloader.loader.ILoader;

/**
 * Description: ImageLoader配置
 * Created by Zijin on 2017/7/31.
 * Email: info@zijinqianbao.com
 */

public class ImageLoaderConfig {
    private static Context mContext;

    /**
     * 屏幕高度
     */
    private static int mWinHeight;

    /**
     * 屏幕宽度
     */
    private static int mWinWidth;

    /**
     * lrucache 最大值
     */
    public static int mCacheMaxSize;

    public static void init(Context context, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD) {
        mContext = context;
        mCacheMaxSize = cacheSizeInM;

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWinWidth = wm.getDefaultDisplay().getWidth();
        mWinHeight = wm.getDefaultDisplay().getHeight();

        getLoader().init(context, cacheSizeInM, memoryCategory, isInternalCD);
    }

    private static ILoader mLoader;

    /**
     * 实际Loader加载器
     */
    public static ILoader getLoader() {
        if (mLoader == null) {
            mLoader = null;
        }
        return mLoader;
    }

    private static Handler mHandler;

    /**
     * 返回主线程Handler
     */
    public static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }

        return mHandler;
    }

    public static Context getContext() {
        return mContext;
    }
}
