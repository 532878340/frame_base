package com.frame.manager.utils.imageloader.loader.glide;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * Description: 全局Glide module
 * Created by Zijin on 2017/7/31.
 * Email: info@zijinqianbao.com
 */

@GlideModule
public class BasicAppGlideModule extends AppGlideModule {
    /**
     * 通过GlideBuilder设置默认的结构(Engine,BitmapPool ,ArrayPool,MemoryCache等等).
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setMemoryCache(new LruResourceCache(10 * 1024 * 1024));//设置内存限制
    }

    @Override
    public void registerComponents(Context context, Registry registry) {
        registry.append(String.class, InputStream.class, new BasicGlideUrlLoader.Factory());
    }

    /**
     * 清单解析的开启
     * <p>
     * 这里不开启，避免添加相同的modules两次
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
