package com.frame.manager.utils;

import android.content.res.Resources;

/**
 * Created by Zijin on 2017/7/27.
 * Email:info@zijinqianbao.com
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
}
