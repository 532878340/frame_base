package com.smart.frame.ui.fetures;

import com.smart.frame.base.contract.IBasePresenter;
import com.smart.frame.base.contract.IBaseView;

/**
 * Description:
 * Created by Zijin on 2017/12/19.
 * Email: info@zijinqianbao.com
 */

public interface ITmpContact {
    interface ITmpView extends IBaseView{
        void showPwd();
    }

    interface ITmpPresenter extends IBasePresenter<ITmpView>{
        void getData();
    }
}
