package com.smart.frame.ui.fetures.user.contract;

import com.smart.frame.base.contract.IBasePresenter;
import com.smart.frame.base.contract.IBaseView;
import com.smart.frame.ui.fetures.user.bean.req.RegisterReq;
import com.smart.frame.ui.fetures.user.bean.req.SendSmsReq;

/**
 * 注册
 *
 * @author Gjm
 * @date 2018/2/27
 */
public interface RegisterContract {
    interface RegisterView extends IBaseView{
        void enableCode();
        void disableCode();
        void countDownTimer(long delay);
    }

    interface IRegisterPresenter extends IBasePresenter<RegisterView>{
        void register(RegisterReq registerReq);

        void sendSms(SendSmsReq sendSmsReq);
    }
}
