package com.smart.frame.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

/**
 *
 * @author Zijin
 * @date 2017/7/25
 */

public final class ActivityUtils {
    /**
     * 添加fragment
     */
    public static void addFragment(FragmentActivity act, int containerViewId, Fragment fragment) {
        addFragment(act, containerViewId, fragment, null);
    }

    /**
     * 添加fragment tag
     */
    public static void addFragment(FragmentActivity act, int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction transaction = act.getSupportFragmentManager().beginTransaction();
        if (TextUtils.isEmpty(tag)) {
            transaction.add(containerViewId, fragment);
        } else {
            transaction.add(containerViewId, fragment, tag);
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    /**
     * 替换fragment
     */
    public static void replaceFragment(FragmentActivity act, int containerViewId, Fragment fragment, String tag) {
        if (act.getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction transaction = act.getSupportFragmentManager().beginTransaction();

            transaction.replace(containerViewId, fragment, tag);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(tag);

            transaction.commit();
        } else {
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            act.getSupportFragmentManager().popBackStack(tag, 0);
        }
    }
}
