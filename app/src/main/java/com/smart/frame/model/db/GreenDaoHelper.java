package com.smart.frame.model.db;

import com.smart.frame.di.annotation.scope.ApplicationScope;

import javax.inject.Inject;

/**
 * Description:greendao helper
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

@ApplicationScope
public class GreenDaoHelper implements DBHelper{
    @Inject
    public GreenDaoHelper() {
    }
}
