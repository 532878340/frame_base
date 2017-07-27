package com.frame.manager.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Description: Toast弹窗
 * Created by Zijin on 2017/7/27.
 * Email: info@zijinqianbao.com
 */

public class ToastManager {
    private Toast mToast;

    private ToastManager() {
    }

    private static final class Holder {
        private static final ToastManager INSTANCE = new ToastManager();
    }

    public static ToastManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 显示 Toast
     */
    public void showMessage(Context context, CharSequence message) {
        View v = View.inflate(context, com.android.internal.R.layout.transient_notification, null);
        TextView tv = v.findViewById(android.R.id.message);
        tv.setText(message);

        if (mToast == null) {
            mToast = new Toast(context);
        }
        mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(v);
        mToast.show();
    }
}
