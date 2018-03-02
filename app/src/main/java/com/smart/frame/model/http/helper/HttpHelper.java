package com.smart.frame.model.http.helper;


import com.smart.frame.base.bean.Repo;
import com.smart.frame.ui.fetures.user.bean.req.LoginReq;
import com.smart.frame.ui.fetures.user.bean.req.SendSmsReq;
import com.smart.frame.ui.fetures.user.bean.resp.LoginResp;
import com.smart.frame.ui.fetures.user.bean.resp.RegisterResp;

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
    Flowable<Repo<LoginResp>> login(Map<String,String> param);

    /**
     * 注册
     */
    Flowable<Repo<RegisterResp>> register(Map<String,String> param);

    /**
     * 发送短信验证码
     */
    Flowable<Repo<Object>> sendSms(Map<String,String> param);
}
