package com.smart.frame.ui.fragment.contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleFragment;
import com.smart.frame.ui.view.recycleview.BaseRecycleAdapter;
import com.smart.frame.ui.view.recycleview.BaseRecycleHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description: 模板选择
 * Created by Zijin on 2017/9/14.
 * Email: info@zijinqianbao.com
 */

public class SearchFragment extends SimpleFragment {
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;

    private BaseRecycleAdapter mAdapter;

    private HeadEntity headEntity;

    public static SearchFragment getInstance(HeadEntity entity){
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("entity",entity);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initViewOrData() {
        headEntity = (HeadEntity) getArguments().getSerializable("entity");

        mAdapter = new BaseRecycleAdapter<String>(mContext,new ArrayList<>(),R.layout.item_home) {
            @Override
            public void onBindView(BaseRecycleHolder holder, int position, String str) {
                TextView id_num = holder.getView(R.id.id_num);
                id_num.setText(headEntity.title + "列表" + str);
            }
        };

        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        recycleView.setAdapter(mAdapter);
        refresh.setOnRefreshListener(refreshlayout -> refresh.postDelayed(() -> {
            refresh.finishRefresh(2000);
            mAdapter.clear();
            fetchData();
        },2000));
        refresh.setOnLoadmoreListener(refreshlayout -> refresh.postDelayed(() -> {
            refresh.finishLoadmore(2000);
            fetchData();
        },2000));

        fetchData();
    }

    void fetchData(){
        List<String> list = new ArrayList<>();
        for (int i = 0 ; i < 10; i ++){
            list.add("i" + i);
        }
        mAdapter.addAll(list);
    }
}
