package com.smart.frame.ui.fetures.user.activity;

import android.text.TextUtils;

import com.smart.frame.R;
import com.smart.frame.base.ui.RootActivity;
import com.smart.frame.ui.fetures.user.bean.req.PhonePwdReq;
import com.smart.frame.ui.fetures.user.contract.ResetLoginPwdContract.ResetLoginPwdView;
import com.smart.frame.ui.fetures.user.presenter.ResetLoginPwdPresenter;
import com.smart.frame.ui.view.basic.LabelEditRow;
import com.smart.frame.utils.ValidateUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 重置登录密码
 *
 * @author Gjm
 * @date 2018/3/6
 */
public class ResetLoginPwdActivity extends RootActivity<ResetLoginPwdPresenter> implements ResetLoginPwdView {
    @BindView(R.id.rowNewPwd)
    LabelEditRow mRowNewPwd;
    @BindView(R.id.rowCfmPwd)
    LabelEditRow mRowCfmPwd;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_reset_login_pwd;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViewOrData() {
    }

    @OnClick(R.id.btnComplete) void complete(){
        String newPwd = mRowNewPwd.getDescText();
        String cfmPwd = mRowCfmPwd.getDescText();

        if(TextUtils.isEmpty(newPwd)){
            showMessage("新密码不能为空");
        }else if(!ValidateUtil.isValidPwd(newPwd)){
            showMessage("请输入6-20位密码");
        }else if(TextUtils.isEmpty(cfmPwd)){
            showMessage("确认密码不能为空");
        }else if(!newPwd.equals(cfmPwd)){
            showMessage("两次输入的密码不一致");
        }else{
            PhonePwdReq phonePwdReq = new PhonePwdReq();
            phonePwdReq.setPhone(getBundleValue("phone"));
            phonePwdReq.setPassword(newPwd);
            mPresenter.resetLoginPwd(phonePwdReq);
        }
    }

    @Override
    public void jumpToLogin() {
        finish();
    }
}
