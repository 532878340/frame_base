package com.smart.frame.contract;

import com.smart.frame.base.contract.IMVPContract;

/**
 * Description:
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public interface InfoContract {
    interface ActView extends IMVPContract.IBaseView{
        void getInfo();
    }
}
