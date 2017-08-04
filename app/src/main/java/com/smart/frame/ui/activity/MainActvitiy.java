package com.smart.frame.ui.activity;

import com.smart.frame.R;
import com.smart.frame.base.ui.RootActivity;
import com.smart.frame.contract.InfoContract;
import com.smart.frame.presenter.TmpPresenter;

import butterknife.OnClick;

/**
 * Description:
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

public class MainActvitiy extends RootActivity<TmpPresenter> implements InfoContract.ActView {

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViewOrData() {
        initToolBar(false, "首页");

        onStatusLoading();
        getInfo();
    }

    @OnClick(R.id.button) void click(){
        getInfo();
    }

    @Override
    public void getInfo() {
        mPresenter.getInfoMessage();
    }
}
