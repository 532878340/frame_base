package com.smart.frame.ui.fetures.user.contract;

import com.smart.frame.base.contract.IBasePresenter;
import com.smart.frame.base.contract.IBaseView;
import com.smart.frame.ui.fetures.user.bean.req.PhonePwdReq;

/**
 * 重置登录密码
 *
 * @author Gjm
 * @date 2018/3/6
 */
public interface ResetLoginPwdContract {
    interface ResetLoginPwdView extends IBaseView{
        void jumpToLogin();
    }

    interface IResetLoginPwdPresenter extends IBasePresenter<ResetLoginPwdView>{
        void resetLoginPwd(PhonePwdReq phonePwdReq);
    }
}
