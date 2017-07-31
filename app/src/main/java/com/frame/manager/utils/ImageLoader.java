package com.frame.manager.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.frame.FrameApplication;
import com.frame.R;
import com.frame.manager.utils.glide.GlideApp;
import com.frame.manager.utils.glide.GlideRequests;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Description:图片加载管理类
 * Created by Zijin on 2017/7/31.
 * Email: info@zijinqianbao.com
 */

@SuppressWarnings("unused")
public final class ImageLoader {
    private final LinkedList<Keeper> mKeepers;

    private ImageLoader() {
        mKeepers = new LinkedList<>();
    }

    private static final class Holder {
        private static final ImageLoader INSTANCE = new ImageLoader();
    }

    public static ImageLoader getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 添加 GlideRequests
     */
    public void addGlideRequest(Activity activity) {
        for (Keeper keeper : mKeepers) {
            if (keeper.mKey == activity.hashCode()) {
                return;
            }
        }

        Keeper keeper = new Keeper(activity);
        mKeepers.add(keeper);
    }

    /**
     * 添加 GlideRequests
     */
    public void addGlideRequest(Fragment fragment) {
        for (Keeper keeper : mKeepers) {
            if (keeper.mKey == fragment.hashCode()) {
                return;
            }
        }

        Keeper keeper = new Keeper(fragment);
        mKeepers.add(keeper);
    }

    /**
     * 移除 GlideRequests
     */
    public void removeGlideRequests(@NonNull Object obj) {
        for (Keeper keeper : mKeepers) {
            if (keeper.mKey == obj.hashCode()) {
                mKeepers.remove(keeper);
            }
        }
    }

    /**
     * 获取GlideRequests
     */
    private GlideRequests getGlideRequests(@NonNull Object obj) {
        for (Keeper keeper : mKeepers) {
            if (keeper.mValues.contains(obj.hashCode())) {
                return keeper.mGlideRequests;
            }
        }
        return GlideApp.with(FrameApplication.getContext());
    }

    /**
     * 绑定生命周期
     */
    public void displayImage(Object obj, String url, ImageView imageView) {
        displayImage(getGlideRequests(obj), url, imageView);
    }

    /**
     * 通用调用，不绑定生命周期
     */
    public void displayImage(String url, ImageView imageView) {
        displayImage(null, url, imageView);
    }

    /**
     * 显示图片
     */
    private void displayImage(GlideRequests glideRequests, String url, ImageView imageView) {
        if (glideRequests == null) {
            glideRequests = GlideApp.with(imageView.getContext());
        }

        glideRequests.asBitmap()
                .load(url)
                .error(R.drawable.icon_default)
                .placeholder(R.drawable.icon_default)
                .fallback(R.drawable.icon_default)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)//设置缓存策略
                .into(imageView);
    }

    /**
     * 加载图片，自定义响应(不绑定生命周期)
     */
    public void loadImage(Context context, String url, final ImageLoaderListener loaderListener) {
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        loaderListener.onSuccess(resource);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        loaderListener.onError(errorDrawable);
                    }
                });
    }

    /**
     * 加载图片，自定义响应(绑定周期)
     *
     * @param obj activity fragment对象
     */
    public void loadImage(@NonNull Object obj, String url, final ImageLoaderListener loaderListener) {
        getGlideRequests(obj)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        loaderListener.onSuccess(resource);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        loaderListener.onError(errorDrawable);
                    }
                });
    }

    /**
     * 用于以及加载图片获取 Bitmap
     */
    public interface ImageLoaderListener {
        void onSuccess(Bitmap bitmap);

        void onError(Drawable drawable);
    }

    /**
     * 注意！！！该方法必须在子线程中执行
     * 清除硬盘缓存
     */
    private void cleanDiskCache(final Context context) {
        Glide.get(context).clearDiskCache();
    }

    /**
     * 清除内存缓存
     */
    private void cleanMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * 内存和硬盘双清
     */
    public void cleanDoubleCache(Context context) {
        cleanDiskCache(context);
        cleanMemoryCache(context);
    }

    /**
     * 恢复请求，一般在停止滚动的时候调用
     */
    public void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }

    /**
     * 暂停请求，一般在滚动的时候调用
     */
    public void pauseRequests(Context context) {
        Glide.with(context).pauseRequests();
    }

    /**
     * 根据图片的网络地址，拿到使用 Glide 进行缓存后的图片缓存地址
     * 注意！！！ 该方法要在子线程中调用，否则会出错
     *
     * @param imageUrl 图片的网络地址
     * @return 图片的缓存地址
     */
    public static String getImagePathFromCache(String imageUrl, Context context) {
        //noinspection deprecation
        FutureTarget<File> futureTarget = Glide.with(context)
                .load(imageUrl)
                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        File cacheFile;
        try {
            cacheFile = futureTarget.get();
            return cacheFile.getAbsolutePath();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class Keeper {
        /**
         * {@link Activity}或{@link Fragment}的HashCode
         */
        int mKey;

        GlideRequests mGlideRequests;
        /**
         * 需要使用{@link Keeper#mGlideRequests}加载图片的对象的HashCode
         * 一般是Activity、Fragment、Adapter、PopupWindow这些需要在内部加载图像的对象
         * 对于这些对象唯一的要求是，生命周期与key所对应Activity或Fragment的生命周期“直接绑定”或“间接绑定”
         */
        Set<Integer> mValues;

        private Keeper(@NonNull Activity activity) {
            mKey = activity.hashCode();
            mValues = new HashSet<>();
            mValues.add(activity.hashCode());
            mGlideRequests = GlideApp.with(activity);
        }

        private Keeper(@NonNull Fragment fragment) {
            mKey = fragment.hashCode();
            mValues = new HashSet<>();
            mValues.add(fragment.hashCode());
            mGlideRequests = GlideApp.with(fragment);
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Keeper && mKey == ((Keeper) obj).mKey;
        }
    }
}
