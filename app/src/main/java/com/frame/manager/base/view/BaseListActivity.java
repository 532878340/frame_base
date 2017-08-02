package com.frame.manager.base.view;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.frame.R;
import com.frame.data.entity.ListRepo;
import com.frame.manager.base.RequestFlag;
import com.frame.manager.base.contracts.IListContract;
import com.frame.manager.base.presenter.BaseListPresenter;
import com.frame.view.recycleview.BaseRecycleAdapter;
import com.frame.view.recycleview.BaseRecycleHolder;
import com.frame.view.recycleview.refresh.RecycleLoadLayout;
import com.frame.view.recycleview.refresh.Status;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Description: 列表基类
 * Created by Zijin on 2017/8/2.
 * Email: info@zijinqianbao.com
 */

public abstract class BaseListActivity<P extends BaseListPresenter,E> extends BaseActivity<P> implements IListContract.IListView{
    @BindView(R.id.recycleLayout)
    RecycleLoadLayout mRecycleLayout;

    private BaseRecycleAdapter mAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.base_list;
    }

    @Override
    protected void initView() {
        super.initView();

        mRecycleLayout.setLayoutManager(new LinearLayoutManager(this));
        mRecycleLayout.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecycleLayout.setOnLoadMoreListener(() -> mPresenter.loadMore());

        mAdapter = new BaseRecycleAdapter<E>(this,new ArrayList<>(),getItemResource()) {
            @Override
            public void onBindView(BaseRecycleHolder holder, int position, E e) {
                bindView(holder,position,e);
            }
        };
        mRecycleLayout.setAdapter(mAdapter);
    }

    @Override
    protected void initData(RequestFlag flag) {
        mPresenter.requestRefresh(flag);
    }

    @Override
    public void getData(ListRepo listRepo, boolean isRefresh) {
        if(isRefresh){
            mAdapter.clear();
        }
        mAdapter.addAll(listRepo.getList());
        mRecycleLayout.setLoadMoreEnable(listRepo.getRecordCount() > mAdapter.getItemCount());
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }

    @Override
    public void resetLoadView() {
        mRecycleLayout.setStatus(Status.STATUS_NO_LOADING);
    }

    /**
     * item条目资源
     */
    protected abstract int getItemResource();

    /**
     * 绑定视图
     */
    protected abstract void bindView(BaseRecycleHolder holder, int position, E e);
}
