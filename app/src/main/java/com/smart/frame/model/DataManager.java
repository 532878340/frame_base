package com.smart.frame.model;

import com.smart.frame.base.bean.Repo;
import com.smart.frame.model.db.DbHelper;
import com.smart.frame.model.http.helper.HttpHelper;
import com.smart.frame.ui.fetures.user.bean.resp.LoginResp;
import com.smart.frame.ui.fetures.user.bean.resp.PatchInfo;
import com.smart.frame.ui.fetures.user.bean.resp.RegisterResp;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * model manager
 * @author Gjm
 * @date 2018/01/12
 */

public class DataManager implements HttpHelper,DbHelper {
    private HttpHelper mHttpHelper;
    private DbHelper mDbHelper;

    public DataManager(HttpHelper httpHelper, DbHelper dbHelper) {
        this.mHttpHelper = httpHelper;
        this.mDbHelper = dbHelper;
    }

    @Override
    public Observable<Repo<String>> platformIndex() {
        return mHttpHelper.platformIndex();
    }

    @Override
    public Flowable<Repo<LoginResp>> login(Map<String,String> param) {
        return mHttpHelper.login(param);
    }

    @Override
    public Flowable<Repo<RegisterResp>> register(Map<String, String> param) {
        return mHttpHelper.register(param);
    }

    @Override
    public Flowable<Repo<Object>> sendSms(Map<String, String> param) {
        return mHttpHelper.sendSms(param);
    }

    @Override
    public Flowable<Repo<Object>> findPwdVerify(Map<String, String> param) {
        return mHttpHelper.findPwdVerify(param);
    }

    @Override
    public Flowable<Repo<Object>> resetLoginPwd(Map<String, String> param) {
        return mHttpHelper.resetLoginPwd(param);
    }

    @Override
    public Flowable<Repo<ArrayList<PatchInfo>>> getPatchInfo(Map<String, String> param) {
        return mHttpHelper.getPatchInfo(param);
    }
}
