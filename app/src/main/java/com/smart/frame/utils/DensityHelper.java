package com.smart.frame.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.smart.frame.utils.sharedpreference.SharedPreferenceManager;

import java.lang.reflect.Field;

/**
 * 屏幕适配工具类
 *
 * @author Gjm
 * @date 2018/3/1
 */
public final class DensityHelper {
    private static final String TAG = "DensityHelper";

    /**
     * 设计图宽度
     */
    private static final float DESIGN_WIDTH = 750;
    /**
     * xdpi
     */
    private static final String KEY_DENSITY_XDPI = "key_density_xdpi";

    private DensityHelper(){
    }

    private static final class Holder{
        private static final DensityHelper INSTANCE = new DensityHelper();
    }

    public static DensityHelper getInstance(){
        return Holder.INSTANCE;
    }

    /**
     * 激活适配方式
     */
    public void active(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        float xdpi = size.x / DESIGN_WIDTH * 72f;

        hookDisplayXdpi(context,xdpi);
    }

    /**
     * 取消适配方式
     */
    public void inActive(Context context){
        float originXdpi = SharedPreferenceManager.getInstance().getFloat(KEY_DENSITY_XDPI);
        if(originXdpi != 0){
            hookDisplayXdpi(context,originXdpi);
        }
    }

    /**
     * 反射实现修改系统DENSITY_DEVICE，使得每次setToDefault()不会重置xdpi
     */
    private void hookDisplayXdpi(Context context,float targetXdpi) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        com.orhanobut.logger.Logger.d("修改前 xdpi为:" + metrics.xdpi);
        //保留原始xdpi尺寸
        if(SharedPreferenceManager.getInstance().getFloat(KEY_DENSITY_XDPI) == 0){
            SharedPreferenceManager.getInstance().setFloat(KEY_DENSITY_XDPI,metrics.xdpi);
        }

        try {
            Field metricField = metrics.getClass().getDeclaredField("DENSITY_DEVICE");
            metricField.setAccessible(true);
            metricField.setInt(metrics, (int) targetXdpi);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //解决MIUI更改框架导致的MIUI7 + Android5.1.1上出现的失效问题(以及极少数基于这部分miui去掉art然后置入xposed的手机)
        if ("MiuiResources".equals(resources.getClass().getSimpleName()) || "XResources".equals(resources.getClass().getSimpleName())) {
            try {
                Field field = Resources.class.getDeclaredField("mTmpMetrics");
                field.setAccessible(true);
                metrics = (DisplayMetrics) field.get(resources);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        metrics.xdpi = targetXdpi;
        com.orhanobut.logger.Logger.d("修改后 xdpi为:" + metrics.xdpi);
    }
}
