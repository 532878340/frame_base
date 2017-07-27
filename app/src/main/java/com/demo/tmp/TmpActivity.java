package com.demo.tmp;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.frame.R;
import com.frame.manager.base.RequestFlag;
import com.frame.manager.base.view.FrameRootActivity;
import com.frame.manager.utils.ActivityUtils;
import com.frame.manager.utils.PermissionHelper;

import butterknife.BindView;

/**
 * Created by Zijin on 2017/7/21.
 * Email:info@zijinqianbao.com
 */

public class TmpActivity extends FrameRootActivity<TmpPresenter> implements ITmpContract.ITmpView {
    private static final String TAG = "FrameRootPresenter";
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    PermissionHelper mPermissionHelper;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
        super.initView();
        mSwipeRefresh.setEnabled(false);

//        ActivityUtils.addFragment(this, R.id.fragmentContainer, new TmpFragment());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new TmpFragment();
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }

    @Override
    protected void initData(RequestFlag flag) {
        mPermissionHelper = new PermissionHelper(this, new PermissionHelper.PermissionCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onDenied() {

            }

            @Override
            public void onForbiddenAlert() {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
