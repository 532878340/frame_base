package com.demo.tmp;

import com.frame.FrameApplication;
import com.frame.R;
import com.frame.manager.base.RequestFlag;
import com.frame.manager.base.view.FrameRootFragment;

/**
 * Created by Zijin on 2017/7/26.
 * Email:info@zijinqianbao.com
 */

public class TmpFragment extends FrameRootFragment<TmpPresenter> implements ITmpContract.ITmpView {
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_index;
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
        super.initView();
        initToolBar(false, R.string.app_name);
    }

    @Override
    protected void initData(RequestFlag flag) {
        mPresenter.getIndexInfo(flag);
    }
}
