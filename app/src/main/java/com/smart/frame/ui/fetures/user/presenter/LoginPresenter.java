package com.smart.frame.ui.fetures.user.presenter;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.smart.frame.base.bean.Repo;
import com.smart.frame.base.presenter.RxPresenter;
import com.smart.frame.base.subscriber.CommonSubscriber;
import com.smart.frame.manager.constants.Configs;
import com.smart.frame.model.DataManager;
import com.smart.frame.ui.fetures.user.bean.req.GetPatchReq;
import com.smart.frame.ui.fetures.user.bean.req.LoginReq;
import com.smart.frame.ui.fetures.user.bean.resp.LoginResp;
import com.smart.frame.ui.fetures.user.bean.resp.PatchInfo;
import com.smart.frame.ui.fetures.user.contract.LoginContract;
import com.smart.frame.utils.CyptoUtils;
import com.smart.frame.utils.TransformUtils;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import zlc.season.rxdownload3.RxDownload;
import zlc.season.rxdownload3.core.Mission;
import zlc.season.rxdownload3.core.Status;
import zlc.season.rxdownload3.core.Succeed;

/**
 * 登录Presenter
 *
 * @author Gjm
 * @date 2018/1/15
 */
public class LoginPresenter extends RxPresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter {
    @Inject
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void login(LoginReq loginReq) {
        Map<String, String> param = new HashMap<>();
        param.put("loginName", loginReq.getLoginName());
        param.put("password", CyptoUtils.getInstance().encodeMD5(loginReq.getPwd()));
        performRequestLoading(mDataManager.login(param).delay(3000, TimeUnit.MILLISECONDS), new CommonSubscriber<LoginResp>(getView()) {
            @Override
            public void onSuccess(LoginResp resp) {
                getView().jumpToAccount();
            }
        });
    }

    @Override
    public void getPatchInfo(GetPatchReq getPatchReq) {
        Map<String, String> param = new HashMap<>();
        param.put("versionName", getPatchReq.getVersionName());
        param.put("status", String.valueOf(getPatchReq.getStatus()));
        param.put("platform", String.valueOf(getPatchReq.getPlatform()));
//        performRequest(mDataManager.getPatchInfo(param), new CommonSubscriber<ArrayList<PatchInfo>>(getView()) {
//            @Override
//            public void onSuccess(ArrayList<PatchInfo> resp) {
//                Logger.d(resp);
//            }
//        });

        final String URL = Configs.URL_BASE + "//fileUpload//wangzg/2018/1/10000059551752.apk";

//        mDataManager.getPatchInfo(param)
//                .compose(getView().bindToLife())
//                .compose(TransformUtils.flowableAllIO())
//                .flatMap(arrayListRepo -> Flowable.fromIterable(arrayListRepo.getData()))
//                .filter(patchInfo -> TextUtils.isEmpty(patchInfo.getFilename()))
//                .flatMap(patchInfo -> RxDownload.INSTANCE.create(URL))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Status>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        Logger.d("开始下载...");
//                    }
//
//                    @Override
//                    public void onNext(Status status) {
//                        Logger.d("下载过程中..." + status);
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        t.printStackTrace();
//                        Logger.d("下载异常...");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Logger.d("下载完成...");
//                    }
//                });

        RxDownload.INSTANCE.create(URL)
                .subscribe(status -> Logger.d("当前状态;" + status));
    }

    @Override
    public void initRequestLoading(String... params) {
        performRequestInit(mDataManager.login(new HashMap<>()), new CommonSubscriber<LoginResp>(getView(), true) {
            @Override
            public void onSuccess(LoginResp resp) {

            }
        });
    }
}
