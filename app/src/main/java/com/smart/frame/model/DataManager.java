package com.smart.frame.model;

import com.smart.frame.base.bean.Repo;
import com.smart.frame.model.db.DBHelper;
import com.smart.frame.model.http.helper.HttpHelper;

import io.reactivex.Observable;

/**
 * Description: model manager
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

public class DataManager implements HttpHelper,DBHelper{
    HttpHelper mHttpHelper;
    DBHelper mDBHelper;

    public DataManager(HttpHelper httpHelper, DBHelper dbHelper) {
        this.mHttpHelper = httpHelper;
        this.mDBHelper = dbHelper;
    }

    @Override
    public Observable<Repo> platformIndex() {
        return mHttpHelper.platformIndex();
    }
}
