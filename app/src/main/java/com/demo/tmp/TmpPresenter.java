package com.demo.tmp;

import com.frame.di.annotation.ActivityScope;
import com.frame.manager.ApiService;
import com.frame.manager.base.presenter.FrameRootPresenter;

import javax.inject.Inject;

/**
 * Created by Zijin on 2017/7/21.
 * Email:info@zijinqianbao.com
 */
@ActivityScope
public class TmpPresenter extends FrameRootPresenter<ITmpContract.ITmpView> implements ITmpContract.ITmpPresenter{

    @Inject
    public TmpPresenter(ITmpContract.ITmpView iTmpView, ApiService apiService) {
        super(iTmpView, apiService);
    }

    @Override
    public void getIndexInfo() {
        request(getApiService().platformIndex());
    }
}
