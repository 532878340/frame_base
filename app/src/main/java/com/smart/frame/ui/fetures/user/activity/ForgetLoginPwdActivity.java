package com.smart.frame.ui.fetures.user.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.smart.frame.R;
import com.smart.frame.base.bean.User;
import com.smart.frame.base.ui.RootActivity;
import com.smart.frame.ui.fetures.user.bean.req.PhoneCodeReq;
import com.smart.frame.ui.fetures.user.bean.req.SendSmsReq;
import com.smart.frame.ui.fetures.user.contract.ForgetLoginPwdContract;
import com.smart.frame.ui.fetures.user.presenter.ForgetLoginPwdPresenter;
import com.smart.frame.ui.view.basic.LabelEditRow;
import com.smart.frame.utils.ActivityUtils;
import com.smart.frame.utils.ValidateUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 忘记登录密码
 *
 * @author Gjm
 * @date 2018/3/6
 */
public class ForgetLoginPwdActivity extends RootActivity<ForgetLoginPwdPresenter> implements ForgetLoginPwdContract.ForgetLoginPwdView {
    @BindView(R.id.rowPhone)
    LabelEditRow mRowPhone;
    @BindView(R.id.rowCode)
    LabelEditRow mRowCode;
    @BindView(R.id.btnGetCode)
    Button mBtnGetCode;
    @BindView(R.id.btnNext)
    Button mBtnNext;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_forget_login_pwd;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewOrData() {
        Log.d("user:---------->", "initViewOrData: " + User.getUser());
    }

    @OnClick({R.id.btnGetCode, R.id.btnNext})
    void click(View v) {
        String phone = mRowPhone.getDescText();
        String code = mRowCode.getDescText();

        switch (v.getId()) {
            case R.id.btnGetCode://发送验证码
                if(TextUtils.isEmpty(phone)){
                    showMessage("手机号码不能为空");
                }else if (!ValidateUtil.isMobileNo(phone)) {
                    showMessage("手机号码格式不正确");
                } else {
                    SendSmsReq sendSmsReq = new SendSmsReq();
                    sendSmsReq.setPhone(phone);
                    sendSmsReq.setRegisterOrNot(false);
                    mPresenter.sendSms(sendSmsReq);
                }
                break;
            case R.id.btnNext://下一步
                if(TextUtils.isEmpty(phone)){
                    showMessage("手机号码不能为空");
                }else if (!ValidateUtil.isMobileNo(phone)) {
                    showMessage("手机号码格式不正确");
                }else if(TextUtils.isEmpty(code)){
                    showMessage("验证码不能为空");
                }else{
                    PhoneCodeReq phoneCodeReq = new PhoneCodeReq();
                    phoneCodeReq.setPhone(phone);
                    phoneCodeReq.setCode(code);
                    mPresenter.nextStep(phoneCodeReq);
                }
                break;
        }
    }

    @Override
    public void enableCode() {
        mBtnGetCode.setEnabled(true);
        mBtnGetCode.setText(R.string.get_code);
    }

    @Override
    public void disableCode() {
        mBtnGetCode.setEnabled(false);
    }

    @Override
    public void countDownTimer(long delay) {
        mBtnGetCode.setText(String.format(getString(R.string.code_count_down),delay / 1000));
    }

    @Override
    public void nextStep() {
        Bundle bundle = new Bundle();
        bundle.putString("phone",mRowPhone.getDescText());
        ActivityUtils.startAct(mCtx,ResetLoginPwdActivity.class,bundle,true);
    }
}
