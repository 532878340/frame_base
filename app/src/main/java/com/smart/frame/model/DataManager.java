package com.smart.frame.model;

import com.smart.frame.base.bean.Repo;
import com.smart.frame.model.db.DbHelper;
import com.smart.frame.model.http.helper.HttpHelper;
import com.smart.frame.ui.fetures.user.bean.resp.LoginResp;

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
    public Flowable<Repo<LoginResp>> login(Map<String, String> param) {
        return mHttpHelper.login(param);
    }
}
