package com.smart.frame.model.http.helper;


import com.smart.frame.base.bean.Repo;
import com.smart.frame.ui.fetures.user.bean.resp.LoginResp;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * retrofit helper
 * @author Gjm
 * @date 2018/01/12
 */

public interface HttpHelper {
    Observable<Repo<String>> platformIndex();

    /**
     * 登录
     */
    Flowable<Repo<LoginResp>> login(String loginName,String password);
}
