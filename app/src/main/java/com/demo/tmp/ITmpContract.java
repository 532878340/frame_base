package com.demo.tmp;

import com.frame.manager.base.RequestFlag;
import com.frame.manager.base.contracts.IContracts;

/**
 * Created by Zijin on 2017/7/21.
 * Email:info@zijinqianbao.com
 */

public interface ITmpContract {
    interface ITmpView extends IContracts.IView {

    }

    interface ITmpPresenter extends IContracts.IPresenter {
        void getIndexInfo(RequestFlag flag);
    }
}
