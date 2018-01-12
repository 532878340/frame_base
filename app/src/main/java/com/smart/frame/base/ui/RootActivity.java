package com.smart.frame.base.ui;

import com.smart.frame.R;
import com.smart.frame.base.Status;
import com.smart.frame.base.presenter.BasePresenter;
import com.smart.frame.ui.view.basic.loading.LoadingView;

import butterknife.BindView;

/**
 * activity基类，处理不同状态的view
 * @author Gjm
 * @date 2018/01/12
 */
public abstract class RootActivity<P extends BasePresenter> extends BaseActivity<P>{
    @BindView(R.id.loading)
    protected LoadingView mLoadingView;

    /**
     * 记录当前状态
     */
    protected Status mStatus = Status.SUCCESS;

    @Override
    public void onStatusMain() {
        if(mStatus != Status.SUCCESS){
            mStatus = Status.SUCCESS;
            mLoadingView.success();
        }
    }

    @Override
    public void onStatusLoading() {
        if(mStatus != Status.LOADING){
            mStatus = Status.LOADING;
            mLoadingView.loading();
        }
    }

    @Override
    public void onStatusError() {
        if(mStatus != Status.FAIL){
            mStatus = Status.FAIL;
            mLoadingView.fail();
        }
    }
}
