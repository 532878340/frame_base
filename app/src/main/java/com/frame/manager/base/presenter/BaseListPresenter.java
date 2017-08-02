package com.frame.manager.base.presenter;

import com.frame.data.entity.ListRepo;
import com.frame.data.entity.Repo;
import com.frame.manager.ApiService;
import com.frame.manager.base.RequestFlag;
import com.frame.manager.base.callback.CallBack;
import com.frame.manager.base.contracts.IListContract;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 列表基类Presenter
 * Created by Zijin on 2017/8/2.
 * Email: info@zijinqianbao.com
 */

public class BaseListPresenter<V extends IListContract.IListView> extends FrameRootPresenter<V> implements IListContract.IListPresenter {
    protected int mCurrpage = 1;

    public BaseListPresenter(V v, ApiService apiService) {
        super(v, apiService);
    }

    @Override
    public void requestRefresh(RequestFlag flag){
        mCurrpage = 1;
        performRequest(flag, mDefaultCallBack);
    }

    @Override
    public void loadMore() {
        mCurrpage ++;
        performRequest(RequestFlag.FLAG_APPEND, mDefaultCallBack);
    }

    @Override
    public Map<String, String> getRequestMap() {
        Map<String, String> param = new HashMap<>();
        param.put("reqPageNum", String.valueOf(mCurrpage));
        return param;
    }

    private CallBack<ListRepo> mDefaultCallBack = new CallBack<ListRepo>() {
        @Override
        public void onSuccess(Repo<ListRepo> repo) {
            getView().getData(repo.getData(),mCurrpage == 1);
            getView().resetLoadView();
        }

        @Override
        public void onIllegal(Repo<ListRepo> repo) {
            getView().resetLoadView();
        }

        @Override
        public void onFailed() {
            getView().resetLoadView();
        }
    };
}
