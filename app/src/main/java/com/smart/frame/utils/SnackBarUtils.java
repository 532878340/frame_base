package com.smart.frame.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Zijin on 2017/7/24.
 * Email:info@zijinqianbao.com
 */

public class SnackBarUtils {
    public static void showSnackBar(View view, CharSequence message) {
        showSnackBar(view, message, null, null);
    }

    /**
     * 显示SnackBar
     */
    public static void showSnackBar(View view, CharSequence message, CharSequence clickLabel, View.OnClickListener clickLis) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .setAction(clickLabel,clickLis)
                .show();
    }
}
