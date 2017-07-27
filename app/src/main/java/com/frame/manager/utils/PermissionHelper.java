package com.frame.manager.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.frame.FrameApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zijin on 2017/7/26.
 * Email:info@zijinqianbao.com
 */

public final class PermissionHelper {
    /**
     * 权限请求码
     */
    private static final int REQUEST_CODE = 0x01;

    private Activity mActivity;

    private PermissionCallback mPermissionCallback;

    public PermissionHelper(Activity activity, @NonNull PermissionCallback callback) {
        this.mActivity = activity;
        this.mPermissionCallback = callback;
    }

    /**
     * 权限检测
     */
    public void requestPermission(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mPermissionCallback.onSuccess();
            return;
        }

        List<String> deniedPermissions = findDeniedPermissions(permissions);

        if (deniedPermissions.isEmpty()) {
            mPermissionCallback.onSuccess();//申请的权限均已获取
        } else {
            ActivityCompat.requestPermissions(mActivity, permissions, REQUEST_CODE);
        }
    }

    /**
     * 返回未获取到的权限
     */
    private List<String> findDeniedPermissions(final String[] permissions) {
        List<String> deniedList = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(FrameApplication.getContext(), permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                deniedList.add(permission);
            }
        }

        return deniedList;
    }

    /**
     * 请求权限响应
     */
    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mPermissionCallback == null || requestCode != REQUEST_CODE) return;

        boolean showRequestPermission = true;

        for (int i : grantResults) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permissions[i])) {
                    mPermissionCallback.onDenied();
                } else {
                    showRequestPermission = false;
                }
                break;
            }
        }

        if (!showRequestPermission) {
            mPermissionCallback.onForbiddenAlert();
            return;
        }

        mPermissionCallback.onSuccess();
    }

    public interface PermissionCallback {
        /**
         * 权限已获取
         */
        void onSuccess();

        /**
         * 权限被拒绝
         */
        void onDenied();

        /**
         * 禁止弹出权限申请
         */
        void onForbiddenAlert();
    }
}
