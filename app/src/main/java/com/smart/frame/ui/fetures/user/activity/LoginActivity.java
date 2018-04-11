package com.smart.frame.ui.fetures.user.activity;

import android.graphics.Color;
import android.text.Selection;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.orhanobut.logger.Logger;
import com.smart.frame.R;
import com.smart.frame.base.ui.RootActivity;
import com.smart.frame.bus.RxBus;
import com.smart.frame.bus.impl.TmpBus;
import com.smart.frame.ui.fetures.user.bean.req.GetPatchReq;
import com.smart.frame.ui.fetures.user.bean.req.LoginReq;
import com.smart.frame.ui.fetures.user.contract.LoginContract;
import com.smart.frame.ui.fetures.user.presenter.LoginPresenter;
import com.smart.frame.ui.view.basic.UltimateBar;
import com.smart.frame.utils.ActivityUtils;
import com.smart.frame.utils.TransformUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 *
 * @author Gjm
 * @date 2018/1/15
 */
public class LoginActivity extends RootActivity<LoginPresenter> implements LoginContract.ILoginView {
    @BindView(R.id.edtName)
    EditText mEdtName;
    @BindView(R.id.edtPwd)
    EditText mEdtPwd;
    @BindView(R.id.cbEye)
    CheckBox mCbEye;
    @BindView(R.id.btnLogin)
    Button mBtnLogin;

    @Inject
    RxPermissions mRxPermissions;

    /**
     * 登录名
     */
    private String mLoginName;
    /**
     * 登录密码
     */
    private String mLoginPwd;

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
        UltimateBar.newTransparentBuilder()
                .statusColor(Color.TRANSPARENT)        // 状态栏颜色
                .statusAlpha(100)               // 状态栏透明度
                .build(this)
                .apply();

        initToolBar(true, "标题栏");

        RxTextView.textChanges(mEdtName)
                .compose(bindToLife())
                .subscribe(charSequence -> {
                    mLoginName = charSequence.toString();
                    RxView.enabled(mBtnLogin)
                            .accept(!TextUtils.isEmpty(mLoginName) && !TextUtils.isEmpty(mLoginPwd));
                });

        RxTextView.textChanges(mEdtPwd)
                .compose(bindToLife())
                .subscribe(charSequence -> {
                    mLoginPwd = charSequence.toString();
                    RxView.enabled(mBtnLogin).accept(!TextUtils.isEmpty(mLoginName) && !TextUtils.isEmpty(mLoginPwd));
                });

        RxCompoundButton.checkedChanges(mCbEye)
                .compose(bindToLife())
                .subscribe(bool -> {
                    mEdtPwd.setTransformationMethod(bool ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                    Selection.setSelection(mEdtPwd.getText(), mEdtPwd.length());
                });

        RxBus.getInstance()
                .toObservableSticky(TmpBus.class)
                .compose(bindToLife())
                .compose(TransformUtils.observableIOToMain())
                .subscribe(tmpBus -> Logger.d("收到通知了:" + tmpBus.getName()));
    }

    @OnClick({R.id.btnLogin, R.id.tvForgetPwd, R.id.btnRegister})
    void click(View v) {
        switch (v.getId()) {
            case R.id.btnLogin://登录
                LoginReq loginReq = new LoginReq();
                loginReq.setLoginName(mLoginName);
                loginReq.setPwd(mLoginPwd);
                mPresenter.login(loginReq);
                break;
            case R.id.tvForgetPwd://忘记密码
                ActivityUtils.startAct(mCtx, ForgetLoginPwdActivity.class);
                break;
            case R.id.btnRegister://注册
//                ActivityUtils.startAct(mCtx,RegisterActivity.class);

                GetPatchReq getPatchReq = new GetPatchReq();
                getPatchReq.setPlatform(1);
                getPatchReq.setStatus(2);
                getPatchReq.setVersionName("1.0.0");
                mPresenter.getPatchInfo(getPatchReq);
                break;
        }
    }

    @Override
    protected boolean initLoadingUI() {
        return false;
    }

    @Override
    protected void initRequestLoading() {
        mPresenter.initRequestLoading();
    }

    @Override
    public void jumpToAccount() {

    }
}
