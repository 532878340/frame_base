package com.smart.frame.ui.activity;

import android.content.Intent;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.smart.frame.R;
import com.smart.frame.base.ui.RootActivity;
import com.smart.frame.contract.InfoContract;
import com.smart.frame.presenter.FirstPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

public class MainActvitiy extends RootActivity<FirstPresenter> implements InfoContract.ActView {

    @BindView(R.id.button)
    Button button;

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

        RxView.clicks(button)
                .subscribe(o -> startActivity(new Intent(this,SecondActivity.class)));
    }

    @OnClick(R.id.button)
    void click() {
        getInfo();
    }

    @Override
    public void getInfo() {
        mPresenter.getInfoMessage();
    }
}
