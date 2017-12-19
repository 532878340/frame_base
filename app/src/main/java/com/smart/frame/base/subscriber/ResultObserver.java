package com.smart.frame.base.subscriber;

import com.smart.frame.base.bean.Repo;
import com.smart.frame.base.contract.IBaseView;

/**
 * Description: 通用请求处理
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public abstract class ResultObserver<T> extends CommonObserver<Repo<T>> {
    public ResultObserver(IBaseView view) {
        super(view);
    }

    public ResultObserver(IBaseView view, boolean showErrorState) {
        super(view, showErrorState);
    }

    @Override
    public void onNext(Repo<T> repo) {
        if(repo.isOk()){
            mView.onStatusMain();
            onSuccess(repo);
        }else if(showErrorState){
            mView.onStatusError();
        }else{
            mView.showMessage(repo.getDescription());
            onIllegal(repo);
        }
    }

    public abstract void onSuccess(Repo<T> repo);

    /**
     * 子类有需要可单独处理
     */
    protected void onIllegal(Repo<T> repo){
    }
}
