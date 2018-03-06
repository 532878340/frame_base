package com.smart.frame.ui.fetures.user.presenter;

import com.smart.frame.base.presenter.RxPresenter;
import com.smart.frame.base.subscriber.CommonSubscriber;
import com.smart.frame.manager.constants.Configs;
import com.smart.frame.model.DataManager;
import com.smart.frame.ui.fetures.user.bean.req.PhoneCodeReq;
import com.smart.frame.ui.fetures.user.bean.req.SendSmsReq;
import com.smart.frame.ui.fetures.user.contract.ForgetLoginPwdContract;
import com.smart.frame.utils.TransformUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 忘记密码
 *
 * @author Gjm
 * @date 2018/3/6
 */
public class ForgetLoginPwdPresenter extends RxPresenter<ForgetLoginPwdContract.ForgetLoginPwdView> implements ForgetLoginPwdContract.IForgetLoginPwdPresenter{
    @Inject
    public ForgetLoginPwdPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void nextStep(PhoneCodeReq phoneCodeReq) {
        Map<String, String> param = new HashMap<>();
        param.put("phone", phoneCodeReq.getPhone());
        param.put("smsCode", phoneCodeReq.getCode());
        performRequestLoading(mDataManager.findPwdVerify(param), new CommonSubscriber<Object>(getView()) {
            @Override
            public void onSuccess(Object resp) {
                getView().nextStep();
            }
        });
    }

    @Override
    public void sendSms(SendSmsReq sendSmsReq) {
        Map<String, String> param = new HashMap<>();
        param.put("phone", sendSmsReq.getPhone());
        param.put("registerOrNot", String.valueOf(sendSmsReq.isRegisterOrNot()));

        performRequest(mDataManager.sendSms(param), new CommonSubscriber<Object>(getView()) {
            @Override
            public void onSuccess(Object resp) {
                long count = Configs.INTERVAL_TIMEOUT;
                Flowable.interval(0, 1, TimeUnit.MILLISECONDS)
                        .take(count + 1)
                        .map(aLong -> count - aLong)
                        .compose(TransformUtils.defaultScheduler())
                        .compose(getView().bindToLife())
                        .subscribeWith(new ResourceSubscriber<Long>() {
                            @Override
                            protected void onStart() {
                                super.onStart();
                                getView().disableCode();
                            }

                            @Override
                            public void onNext(Long aLong) {
                                getView().countDownTimer(aLong);
                            }

                            @Override
                            public void onError(Throwable t) {
                            }

                            @Override
                            public void onComplete() {
                                getView().enableCode();
                            }
                        });
            }
        });
    }
}
