package com.frame.manager.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: FragmentPagerAdapter基类
 * Created by Zijin on 2017/7/28.
 * Email: info@zijinqianbao.com
 */

public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    protected final List<Fragment> mFragmentList = new ArrayList<>();

    public BaseFragmentPagerAdapter(FragmentManager fm, @NonNull List<Fragment> fragments) {
        super(fm);
        mFragmentList.addAll(fragments);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
