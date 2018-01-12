package com.smart.frame.model.http.helper;


import com.smart.frame.base.bean.Repo;

import io.reactivex.Observable;

/**
 * retrofit helper
 * @author Gjm
 * @date 2018/01/12
 */

public interface HttpHelper {
    Observable<Repo<String>> platformIndex();
}
