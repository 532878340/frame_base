package com.smart.frame.ui.fetures.user.presenter;

import com.smart.frame.base.presenter.RxPresenter;
import com.smart.frame.base.subscriber.CommonSubscriber;
import com.smart.frame.model.DataManager;
import com.smart.frame.ui.fetures.user.bean.req.LoginReq;
import com.smart.frame.ui.fetures.user.bean.resp.LoginResp;
import com.smart.frame.ui.fetures.user.contract.LoginContract;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * 登录Presenter
 *
 * @author Gjm
 * @date 2018/1/15
 */
public class LoginPresenter extends RxPresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter{
    @Inject
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void login(LoginReq loginReq) {
        Map<String,String> param = new HashMap<>();
        param.put("loginName",loginReq.getLoginName());
        param.put("password",loginReq.getPwd());
        performRequestLoading(mDataManager.login(param).delay(3000, TimeUnit.MILLISECONDS), new CommonSubscriber<LoginResp>(getView()) {
            @Override
            public void onSuccess(LoginResp resp) {

            }
        });
    }

    @Override
    public void initRequestLoading(String ...params) {
        performRequestInit(mDataManager.login(new HashMap<>()), new CommonSubscriber<LoginResp>(getView(),true) {
            @Override
            public void onSuccess(LoginResp resp) {

            }
        });
    }
}
