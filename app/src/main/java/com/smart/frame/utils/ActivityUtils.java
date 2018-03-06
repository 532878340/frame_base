package com.smart.frame.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.smart.frame.R;

import java.lang.reflect.Field;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.KITKAT;

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

    /**
     * 解决InputMethodManager造成的内存泄漏问题
     * @param destContext 上下文
     */
    public static void fixInputMethodManagerLeak(Context destContext) {

        if (SDK_INT < KITKAT || SDK_INT > 22) {
            return;
        }
        if (destContext == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f;
        Object obj_get;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm.getClass().getDeclaredField(param);
                if (f == null) {
                    return;
                }
                f.setAccessible(true);
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
