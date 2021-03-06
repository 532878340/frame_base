package com.smart.frame.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity容器
 * @author Gjm
 * @date 2018/01/12
 */


public final class ActivityContainer {
    private List<Activity> mActivityList;

    private ActivityContainer(){
        mActivityList = new ArrayList<>();
    }

    private static final class ActivityHolder{
        private static final ActivityContainer INSTANCE = new ActivityContainer();
    }

    public static ActivityContainer getInstance(){
        return ActivityHolder.INSTANCE;
    }

    public void add(Activity activity){
        mActivityList.add(activity);
    }

    public void remove(Activity activity){
        mActivityList.remove(activity);
    }

    /**
     * 保留唯一的activity,singleTask调用
     */
    public void removeWithClearTop(Activity activity){
        mActivityList.clear();
        mActivityList.add(activity);
    }

    /**
     * 清除所有activity
     */
    public void finishAll(){
        for (Activity activity : mActivityList) {
            if(activity != null){
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
