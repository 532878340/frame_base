package com.smart.frame.ui.fetures;

import com.smart.frame.base.bean.Repo;
import com.smart.frame.base.presenter.RxPresenter;
import com.smart.frame.base.subscriber.ResultObserver;
import com.smart.frame.model.DataManager;

/**
 * Description:
 * Created by Zijin on 2017/12/19.
 * Email: info@zijinqianbao.com
 */

public class TmpPresenter extends RxPresenter<ITmpContact.ITmpView> implements ITmpContact.ITmpPresenter {
    public TmpPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getData() {
        wrapObservable(mDataManager.platformIndex())
                .subscribe(new ResultObserver<String>(getView()) {
                    @Override
                    public void onSuccess(Repo<String> repo) {
                        repo.getData();
                    }
                });
    }
}
