package com.smart.frame.ui.fetures.user.presenter;

import com.orhanobut.logger.Logger;
import com.smart.frame.base.presenter.RxPresenter;
import com.smart.frame.base.subscriber.CommonSubscriber;
import com.smart.frame.manager.constants.Configs;
import com.smart.frame.model.DataManager;
import com.smart.frame.ui.fetures.user.bean.req.GetPatchReq;
import com.smart.frame.ui.fetures.user.bean.req.LoginReq;
import com.smart.frame.ui.fetures.user.bean.resp.LoginResp;
import com.smart.frame.ui.fetures.user.contract.LoginContract;
import com.smart.frame.utils.CyptoUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import zlc.season.rxdownload3.RxDownload;

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
//        performRequestLoading(mDataManager.login(param).delay(3000, TimeUnit.MILLISECONDS), new CommonSubscriber<LoginResp>(getView()) {
//            @Override
//            public void onSuccess(LoginResp resp) {
//                getView().jumpToAccount();
//            }
//        });



//        performRequestLoading(mDataManager.performReq(RequestBody.create(MediaType.parse("application/json"),buildParam())), new RespSubscriber<String>(getView()) {
//            @Override
//            public void onSuccess(String resp) {
//                getView().jumpToAccount();
//            }
//        });
    }

    String buildParam(){
        try {
            Map<String,String> param = new HashMap<>();
            param.put("cmd","A00002");
            param.put("appKey","fdacf80828bf4e68");
            param.put("userName","18627540357");
            param.put("passWord","123456");
            param.put("sign","123456");

            JSONObject jsonObject = new JSONObject();
            for (String key : param.keySet()){
                jsonObject.put(key,param.get(key));
            }

            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
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
