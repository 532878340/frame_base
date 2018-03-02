package com.smart.frame.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.smart.frame.R;

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


    public static void startAct(Context ctx,Class<?> clazz){
        startAct(ctx,clazz,null);
    }

    /**
     * Activity跳转
     */
    public static void startAct(Context ctx, Class<?> clazz, Bundle bundle){
        Intent intent = new Intent(ctx,clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        ctx.startActivity(intent);
        if(ctx instanceof Activity){
            ((Activity)ctx).overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
        }
    }
}
