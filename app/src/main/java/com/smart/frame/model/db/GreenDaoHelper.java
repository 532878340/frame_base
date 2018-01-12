package com.smart.frame.model.db;

import com.smart.frame.di.annotation.scope.ApplicationScope;

import javax.inject.Inject;

/**
 * greendao helper
 * @author Gjm
 * @date 2018/01/12
 */

@ApplicationScope
public class GreenDaoHelper implements DbHelper {
    @Inject
    public GreenDaoHelper() {
    }
}
