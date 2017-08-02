package com.func.presenter;

import com.frame.data.entity.Repo;
import com.frame.di.annotation.ActivityScope;
import com.frame.manager.ApiService;
import com.frame.manager.base.contracts.IListContract;
import com.frame.manager.base.presenter.BaseListPresenter;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Zijin on 2017/8/2.
 * Email: info@zijinqianbao.com
 */

@ActivityScope
public class RecycleListPresenter extends BaseListPresenter{

    @Inject
    public RecycleListPresenter(IListContract.IListView iListView, ApiService apiService) {
        super(iListView, apiService);
    }

    @Override
    public Observable<Repo> getObservable() {
        return getApiService().getSteadyInfo(getRequestMap());
    }
}
