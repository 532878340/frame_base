package com.smart.frame.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * @author Zijin
 * @date 2017/7/27
 */

public final class DimensUtils {
    /**
     * dp转px
     */
    public static final float dpToPx(float value) {
        return Resources.getSystem().getDisplayMetrics().density * value;
    }

    /**
     * sp转px
     */
    public static final float spToPx(float value) {
        return Resources.getSystem().getDisplayMetrics().scaledDensity * value;
    }

    /**
     * px转dp
     */
    public static final float pxToDp(float value) {
        return value / Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * px转sp
     */
    public static final float pxToSp(float value) {
        return value / Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    /**
     * pt转px 此处不能使用 Resources.getSystem()
     */
    public static final int ptToPx(Context context, float value){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
//        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, value, metrics);
    }
}
