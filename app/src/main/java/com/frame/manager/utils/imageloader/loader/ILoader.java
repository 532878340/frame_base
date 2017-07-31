package com.frame.manager.utils.imageloader.loader;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.MemoryCategory;

/**
 * Description:
 * Created by Zijin on 2017/7/31.
 * Email: info@zijinqianbao.com
 */

public interface ILoader {
    /**
     * 初始化
     */
    void init(Context context, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD);

    /**
     * 开始加载
     */
    void load();

    /**
     * 暂停请求
     */
    void pause();

    /**
     * 恢复请求
     */
    void resume();

    /**
     * 清除磁盘缓存
     */
    void clearDiskCache();

    /**
     * 清除内存缓存
     */
    void clearMemory();

    /**
     * 是否已缓存
     */
    boolean isCached(String url);

    /**
     * 程序清理内存调用
     * @param level
     */
    void trimMemory(int level);

    /**
     * 清除缓存
     */
    void clearAllMemoryCaches();
}
