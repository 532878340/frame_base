package com.smart.frame.presenter;

import com.smart.frame.base.bean.Repo;
import com.smart.frame.base.presenter.RxPresenter;
import com.smart.frame.base.subscriber.ResultObserver;
import com.smart.frame.contract.InfoContract;
import com.smart.frame.di.annotation.scope.ActivityScope;
import com.smart.frame.model.DataManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * Description:
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */
@ActivityScope
public class FirstPresenter extends RxPresenter<InfoContract.ActView> {

    @Inject
    public FirstPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void getInfoMessage(){
        wrapObservable(mDataManager.platformIndex()
                .delay(3000, TimeUnit.MILLISECONDS))
                .subscribe(new ResultObserver(getView()){
                    @Override
                    public void onSuccess(Repo repo) {
                        getView().showMessage(repo.getDescription());
                    }
                });
    }
}
