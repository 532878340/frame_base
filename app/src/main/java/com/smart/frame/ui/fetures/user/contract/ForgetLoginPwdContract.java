package com.smart.frame.ui.fetures.user.contract;

import com.smart.frame.base.contract.IBasePresenter;
import com.smart.frame.base.contract.IBaseView;
import com.smart.frame.ui.fetures.user.bean.req.PhoneCodeReq;
import com.smart.frame.ui.fetures.user.bean.req.SendSmsReq;

/**
 * 忘记密码
 *
 * @author Gjm
 * @date 2018/3/6
 */
public interface ForgetLoginPwdContract {
    interface ForgetLoginPwdView extends IBaseView{
        void enableCode();
        void disableCode();
        void countDownTimer(long delay);
        void nextStep();
    }

    interface IForgetLoginPwdPresenter extends IBasePresenter<ForgetLoginPwdView>{
        void nextStep(PhoneCodeReq phoneCodeReq);

        void sendSms(SendSmsReq sendSmsReq);
    }
}
