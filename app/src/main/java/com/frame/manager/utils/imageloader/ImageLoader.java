package com.frame.manager.utils.imageloader;

import android.content.Context;

import com.bumptech.glide.MemoryCategory;
import com.frame.manager.utils.imageloader.config.ImageLoaderConfig;
import com.frame.manager.utils.imageloader.loader.ILoader;

/**
 * Description: 图片加载
 * Created by Zijin on 2017/7/31.
 * Email: info@zijinqianbao.com
 * https://github.com/libin7278/ImageLoader
 */

public class ImageLoader {
    private static Context mContext;

    /**
     * 默认最大缓存
     */
    private static final int CACHE_IMAGE_SIZE = 150;

    public static void init(final Context context) {
        init(context, CACHE_IMAGE_SIZE);
    }

    public static void init(final Context context, int cacheSizeInM) {
        init(context, cacheSizeInM, MemoryCategory.NORMAL);
    }

    public static void init(final Context context, int cacheSizeInM, MemoryCategory memoryCategory) {
        init(context, cacheSizeInM, memoryCategory, true);
    }

    /**
     * @param context        上下文
     * @param cacheSizeInM   Glide默认磁盘缓存最大容量250MB
     * @param memoryCategory 调整内存缓存的大小 LOW(0.5f) ／ NORMAL(1f) ／ HIGH(1.5f);
     * @param isInternalCD   true 磁盘缓存到应用的内部目录 / false 磁盘缓存到外部存
     */
    public static void init(Context context, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD) {
        mContext = context;
        ImageLoaderConfig.init(context,cacheSizeInM,memoryCategory,isInternalCD);
    }

    /**
     * 获取加载器
     */
    public static ILoader getImageLoader() {
        return ImageLoaderConfig.getLoader();
    }
}
