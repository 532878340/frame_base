package com.demo.tmp;

import android.util.Log;

import com.frame.FrameApplication;
import com.frame.R;
import com.frame.manager.base.view.FrameRootActivity;

/**
 * Created by Zijin on 2017/7/21.
 * Email:info@zijinqianbao.com
 */

public class TmpActivity extends FrameRootActivity<TmpPresenter> implements ITmpContract.ITmpView {
    private static final String TAG = "FrameRootPresenter";

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {
        DaggerTmpComponent.builder()
                .appComponent(FrameApplication.getAppComponent())
                .tmpModule(new TmpModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        mPresenter.getIndexInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
