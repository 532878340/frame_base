package com.demo.tmp;

import com.frame.data.entity.Repo;
import com.frame.di.annotation.ActivityScope;
import com.frame.di.annotation.FragmentScope;
import com.frame.manager.ApiService;
import com.frame.manager.base.RequestFlag;
import com.frame.manager.base.callback.DefaultCallBack;
import com.frame.manager.base.presenter.FrameRootPresenter;

import javax.inject.Inject;

/**
 * Created by Zijin on 2017/7/21.
 * Email:info@zijinqianbao.com
 */
@FragmentScope
public class TmpPresenter extends FrameRootPresenter<ITmpContract.ITmpView> implements ITmpContract.ITmpPresenter {
    private static final String TAG = "TmpPresenter";

    @Inject
    public TmpPresenter(ITmpContract.ITmpView iTmpView, ApiService apiService) {
        super(iTmpView, apiService);
    }

    @Override
    public void getIndexInfo(RequestFlag flag) {
        performRequest(getApiService().platformIndex(), flag, new DefaultCallBack<Simple>() {
            @Override
            public void onSuccess(Repo<Simple> repo) {
                getView().showMessage(repo.getDescription());
            }
        });
    }
}
