package com.smart.frame.model.http.helper;


import com.smart.frame.base.bean.Repo;

import io.reactivex.Observable;

/**
 * Description: retrofit helper
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

public interface HttpHelper {
    Observable<Repo<String>> platformIndex();
}
