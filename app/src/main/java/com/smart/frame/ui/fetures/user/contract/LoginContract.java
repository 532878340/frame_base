package com.smart.frame.ui.fetures.user.contract;

import com.smart.frame.base.contract.IBasePresenter;
import com.smart.frame.base.contract.IBaseView;
import com.smart.frame.ui.fetures.user.bean.req.GetPatchReq;
import com.smart.frame.ui.fetures.user.bean.req.LoginReq;

/**
 * 登录
 *
 * @author Gjm
 * @date 2018/1/15
 */
public interface LoginContract{
    interface ILoginView extends IBaseView{
        void jumpToAccount();
    }

    interface ILoginPresenter extends IBasePresenter<ILoginView>{
        void login(LoginReq loginReq);

        void getPatchInfo(GetPatchReq getPatchReq);
    }
}
