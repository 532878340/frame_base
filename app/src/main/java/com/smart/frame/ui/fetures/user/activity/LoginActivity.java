package com.smart.frame.ui.fetures.user.activity;

import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.smart.frame.R;
import com.smart.frame.base.ui.RootActivity;
import com.smart.frame.ui.fetures.user.bean.req.LoginReq;
import com.smart.frame.ui.fetures.user.presenter.LoginPresenter;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * 登录
 *
 * @author Gjm
 * @date 2018/1/15
 */
public class LoginActivity extends RootActivity<LoginPresenter> {
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtPwd)
    EditText edtPwd;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViewOrData() {

        RxView.clicks(btnLogin).subscribe(o -> {
            LoginReq loginReq = new LoginReq();
            loginReq.setLoginName(edtName.getText().toString());
            loginReq.setPwd(edtPwd.getText().toString());

            mPresenter.login(loginReq);
        });
    }
}
