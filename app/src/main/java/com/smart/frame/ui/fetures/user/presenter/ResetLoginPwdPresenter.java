package com.smart.frame.ui.fetures.user.presenter;

import com.smart.frame.base.presenter.RxPresenter;
import com.smart.frame.base.subscriber.CommonSubscriber;
import com.smart.frame.model.DataManager;
import com.smart.frame.ui.fetures.user.bean.req.PhonePwdReq;
import com.smart.frame.ui.fetures.user.contract.ResetLoginPwdContract;
import com.smart.frame.utils.CyptoUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * 重置密码
 *
 * @author Gjm
 * @date 2018/3/6
 */
public class ResetLoginPwdPresenter extends RxPresenter<ResetLoginPwdContract.ResetLoginPwdView> implements ResetLoginPwdContract.IResetLoginPwdPresenter{
    @Inject
    public ResetLoginPwdPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void resetLoginPwd(PhonePwdReq phonePwdReq) {
        Map<String,String> param = new HashMap<>();
        param.put("phone",phonePwdReq.getPhone());
        param.put("password", CyptoUtils.getInstance().encodeMD5(phonePwdReq.getPassword()));

        performRequestLoading(mDataManager.resetLoginPwd(param), new CommonSubscriber<Object>(getView()) {
            @Override
            public void onSuccess(Object resp) {
                getView().jumpToLogin();
            }
        });
    }
}
