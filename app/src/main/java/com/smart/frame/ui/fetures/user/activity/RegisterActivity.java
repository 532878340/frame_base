package com.smart.frame.ui.fetures.user.activity;

import android.text.Selection;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.smart.frame.R;
import com.smart.frame.base.ui.RootActivity;
import com.smart.frame.ui.fetures.user.bean.req.RegisterReq;
import com.smart.frame.ui.fetures.user.bean.req.SendSmsReq;
import com.smart.frame.ui.fetures.user.contract.RegisterContract;
import com.smart.frame.ui.fetures.user.presenter.RegisterPresenter;
import com.smart.frame.utils.ActivityUtils;
import com.smart.frame.utils.ValidateUtil;
import com.smart.frame.utils.ViewUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册
 *
 * @author Gjm
 * @date 2018/2/27
 */
public class RegisterActivity extends RootActivity<RegisterPresenter> implements RegisterContract.RegisterView {
    @BindView(R.id.edtName)
    EditText mEdtName;
    @BindView(R.id.edtPwd)
    EditText mEdtPwd;
    @BindView(R.id.cbEye)
    CheckBox mCbEye;
    @BindView(R.id.edtCode)
    EditText mEdtCode;
    @BindView(R.id.tvGetCode)
    TextView mTvGetCode;
    @BindView(R.id.chbInvite)
    CheckBox mChbInvite;
    @BindView(R.id.edtInvite)
    EditText mEdtInvite;
    @BindView(R.id.btnRegister)
    Button mBtnRegister;
    @BindView(R.id.chbProtocol)
    CheckBox mChbProtocol;

    private String mLoginName;
    private String mLoginPwd;
    private String mCode;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewOrData() {
        RxTextView.textChanges(mEdtName)
                .compose(bindToLife())
                .subscribe(charSequence -> {
                    mLoginName = charSequence.toString();
                    RxView.enabled(mBtnRegister).accept(!TextUtils.isEmpty(mLoginName) && !TextUtils.isEmpty(mLoginPwd) && !TextUtils.isEmpty(mCode) && mChbProtocol.isChecked());
                });

        RxTextView.textChanges(mEdtPwd)
                .compose(bindToLife())
                .subscribe(charSequence -> {
                    mLoginPwd = charSequence.toString();
                    RxView.enabled(mBtnRegister).accept(!TextUtils.isEmpty(mLoginName) && !TextUtils.isEmpty(mLoginPwd) && !TextUtils.isEmpty(mCode) && mChbProtocol.isChecked());
                });

        RxTextView.textChanges(mEdtCode)
                .compose(bindToLife())
                .subscribe(charSequence -> {
                    mCode = charSequence.toString();
                    RxView.enabled(mBtnRegister).accept(!TextUtils.isEmpty(mLoginName) && !TextUtils.isEmpty(mLoginPwd) && !TextUtils.isEmpty(mCode) && mChbProtocol.isChecked());
                });

        RxCompoundButton.checkedChanges(mChbProtocol)
                .compose(bindToLife())
                .subscribe(bool -> RxView.enabled(mBtnRegister).accept(!TextUtils.isEmpty(mLoginName) && !TextUtils.isEmpty(mLoginPwd) && !TextUtils.isEmpty(mCode) && mChbProtocol.isChecked()));

        RxCompoundButton.checkedChanges(mCbEye)
                .compose(bindToLife())
                .subscribe(bool -> {
                    mEdtPwd.setTransformationMethod(bool ? HideReturnsTransformationMethod.getInstance() : PasswordTransformationMethod.getInstance());
                    Selection.setSelection(mEdtPwd.getText(), mEdtPwd.length());
                });

        RxCompoundButton.checkedChanges(mChbInvite)
                .compose(bindToLife())
                .subscribe(bool -> RxView.visibility(mEdtInvite).accept(bool));
    }

    @OnClick({R.id.btnRegister, R.id.tvGetCode, R.id.tvProtocol, R.id.tvLogin})
    void click(View v) {
        switch (v.getId()) {
            case R.id.btnRegister://注册
                if (!ValidateUtil.isMobileNo(mLoginName)) {
                    showMessage("手机号码格式不正确");
                } else if (!ValidateUtil.isValidPwd(mLoginPwd)) {
                    showMessage("请输入6-20位密码");
                } else if (!ViewUtil.isEmpty(mEdtInvite) && !ValidateUtil.isMobileNo(ViewUtil.getText(mEdtInvite))) {
                    showMessage("邀请人手机号格式不正确");
                } else {
                    RegisterReq registerReq = new RegisterReq();
                    registerReq.setPhone(mLoginName);
                    registerReq.setPassword(mLoginPwd);
                    registerReq.setSmsCode(mCode);
                    registerReq.setReferrer(ViewUtil.getText(mEdtInvite));
                    mPresenter.register(registerReq);
                }
                break;
            case R.id.tvProtocol://协议
                break;
            case R.id.tvLogin://登录
                ActivityUtils.startAct(mCtx, LoginActivity.class);
                break;
            case R.id.tvGetCode://获取验证码
                if (!ValidateUtil.isMobileNo(mLoginName)) {
                    showMessage("手机号码格式不正确");
                } else {
                    SendSmsReq sendSmsReq = new SendSmsReq();
                    sendSmsReq.setPhone(mLoginName);
                    sendSmsReq.setRegisterOrNot(true);
                    mPresenter.sendSms(sendSmsReq);
                }
                break;
        }
    }

    @Override
    public void enableCode() {
        mTvGetCode.setEnabled(true);
        mTvGetCode.setText(R.string.get_code);
        mTvGetCode.setTextColor(getResources().getColor(R.color.orange_normal));
    }

    @Override
    public void disableCode() {
        mTvGetCode.setEnabled(false);
        mTvGetCode.setTextColor(getResources().getColor(R.color.gray_light));
    }

    @Override
    public void countDownTimer(long delay) {
        mTvGetCode.setText(String.format(getString(R.string.code_count_down),delay / 1000));
    }

    @Override
    public void jumpToAccount() {

    }
}
