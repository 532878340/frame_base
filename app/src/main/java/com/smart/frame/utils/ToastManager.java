package com.smart.frame.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.frame.R;

/**
 * Description: Toast弹窗
 * @author Zijin
 * @date 2017/7/27
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
        View v = View.inflate(context, R.layout.layout_toast, null);
        TextView tv = v.findViewById(R.id.toastTv);
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
