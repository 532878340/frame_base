package com.smart.frame.ui.fetures.user.presenter;

import android.text.TextUtils;

import com.smart.frame.base.presenter.RxPresenter;
import com.smart.frame.model.DataManager;
import com.smart.frame.ui.fetures.user.bean.req.LoginReq;
import com.smart.frame.ui.fetures.user.contract.LoginContract;

/**
 * 登录Presenter
 *
 * @author Gjm
 * @date 2018/1/15
 */
public class LoginPresenter extends RxPresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter{
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void login(LoginReq loginReq) {
        if(TextUtils.isEmpty(loginReq.getLoginName())){
            mView.showMessage("登录名不能为空");
        }else if(TextUtils.isEmpty(loginReq.getPwd())){
            mView.showMessage("密码不能为空");
        }else{
            wrapFlowable(mDataManager.login(loginReq.getLoginName(),loginReq.getPwd())).subscribe();
        }
    }
}
