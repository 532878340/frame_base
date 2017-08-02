package com.frame.manager.base.contracts;

import com.frame.data.entity.ListRepo;
import com.frame.manager.base.RequestFlag;

/**
 * Description:列表contracts
 * Created by Zijin on 2017/8/2.
 * Email: info@zijinqianbao.com
 */

public interface IListContract {
    interface IListView extends IContracts.IView{
        /**
         * 获取数据
         */
        void getData(ListRepo listRepo, boolean isRefresh);

        /**
         * 重置上拉加载
         */
        void resetLoadView();
    }

    interface IListPresenter extends IContracts.IPresenter{

        /**
         * 刷新加载
         */
        void requestRefresh(RequestFlag flag);

        /**
         * 加载更多
         */
        void loadMore();
    }
}
