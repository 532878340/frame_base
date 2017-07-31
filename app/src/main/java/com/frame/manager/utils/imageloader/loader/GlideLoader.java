package com.frame.manager.utils.imageloader.loader;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.frame.manager.utils.glide.GlideApp;
import com.frame.manager.utils.imageloader.config.ImageLoaderConfig;

/**
 * Description: GlideLoader
 * Created by Zijin on 2017/7/31.
 * Email: info@zijinqianbao.com
 */

public class GlideLoader implements ILoader{
    @Override
    public void init(Context context, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD) {
//        Glide.with(ImageLoaderConfig.getContext());
        GlideBuilder builder = new GlideBuilder();
        if(isInternalCD){
            builder.setDiskCache(new InternalCacheDiskCacheFactory(context,cacheSizeInM * 1024 * 1024));
        }else{
            builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,cacheSizeInM * 1024 * 1024));
        }
    }

    @Override
    public void load() {

    }

    @Override
    public void pause() {
        GlideApp.with(ImageLoaderConfig.getContext()).pauseRequestsRecursive();
    }

    @Override
    public void resume() {
        GlideApp.with(ImageLoaderConfig.getContext()).resumeRequestsRecursive();
    }

    @Override
    public void clearDiskCache() {
//        GlideApp.with(ImageLoaderConfig.getContext()).c
    }

    @Override
    public void clearMemory() {
    }

    @Override
    public boolean isCached(String url) {
        return false;
    }

    @Override
    public void trimMemory(int level) {
        GlideApp.with(ImageLoaderConfig.getContext()).onTrimMemory(level);
    }

    @Override
    public void clearAllMemoryCaches() {
//        GlideApp.with(ImageLoaderConfig.getContext()).
    }
}
