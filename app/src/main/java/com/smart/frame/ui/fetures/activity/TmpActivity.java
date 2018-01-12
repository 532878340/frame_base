package com.smart.frame.ui.fetures.activity;

import com.smart.frame.base.ui.RootActivity;
import com.smart.frame.ui.fetures.ITmpContact;
import com.smart.frame.ui.fetures.TmpPresenter;

/**
 * @author Zijin
 * @date 2017/12/19
 * Email: info@zijinqianbao.com
 */

public class TmpActivity extends RootActivity<TmpPresenter> implements ITmpContact.ITmpView{
    @Override
    public void showPwd() {
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initViewOrData() {

    }
}
