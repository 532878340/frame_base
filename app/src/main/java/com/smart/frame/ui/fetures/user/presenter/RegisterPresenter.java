package com.smart.frame.ui.fetures.user.presenter;

import com.smart.frame.base.presenter.RxPresenter;
import com.smart.frame.base.subscriber.CommonSubscriber;
import com.smart.frame.manager.constants.Configs;
import com.smart.frame.model.DataManager;
import com.smart.frame.ui.fetures.user.bean.req.RegisterReq;
import com.smart.frame.ui.fetures.user.bean.req.SendSmsReq;
import com.smart.frame.ui.fetures.user.bean.resp.LoginResp;
import com.smart.frame.ui.fetures.user.contract.RegisterContract;
import com.smart.frame.utils.CyptoUtils;
import com.smart.frame.utils.TransformUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 注册Presenter
 *
 * @author Gjm
 * @date 2018/2/27
 */
public class RegisterPresenter extends RxPresenter<RegisterContract.RegisterView> implements RegisterContract.IRegisterPresenter {
    @Inject
    public RegisterPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void register(RegisterReq registerReq) {
        Map<String, String> param = new HashMap<>();
        param.put("phone",registerReq.getPhone());
        param.put("password", CyptoUtils.getInstance().encodeMD5(registerReq.getPassword()));
        param.put("smsCode",registerReq.getSmsCode());
        param.put("referrer",registerReq.getReferrer());

        getView().onStatusLoading();
        mDataManager.register(param)
                .compose(TransformUtils.flowableIOToMain())
                .compose(getView().bindToLife())
                .doOnNext(registerResp -> {
                    if(!registerResp.isOk()){
                        getView().onStatusMain();
                        getView().showMessage(registerResp.getDescription());
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(registerResp -> {
                    Map<String, String> param1 = new HashMap<>();
                    param1.put("loginName", registerReq.getPhone());
                    param1.put("password", registerReq.getPassword());
                    return registerResp.isOk() ? mDataManager.login(param1) : Flowable.empty();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonSubscriber<LoginResp>(getView()) {
                    @Override
                    public void onSuccess(LoginResp resp) {
                        getView().jumpToAccount();
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
                        .compose(TransformUtils.flowableIOToMain())
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
