package com.smart.frame.ui.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleFragment;
import com.smart.frame.ui.view.recycleview.BaseRecycleAdapter;
import com.smart.frame.ui.view.recycleview.BaseRecycleHolder;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Description:
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public class QuestionFragment extends SimpleFragment {
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    List<String> list = new ArrayList<>();


    public static QuestionFragment getInstance() {
        return new QuestionFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_question;
    }

    @Override
    protected void initViewOrData() {
        Logger.d("initViewOrData 创建" + toString());

        for (int i = 0 ;i < 10 ; i ++){
            list.add("a" + i);
        }

        for (int i = 0 ;i < 10 ; i ++){
            list.add("c" + i);
        }

        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        AnimalHeaderAdapter adapter = new AnimalHeaderAdapter(mContext,list);
        StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recycleView.addItemDecoration(headersDecor);
        recycleView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        recycleView.setAdapter(adapter);
    }

    class AnimalHeaderAdapter extends BaseRecycleAdapter<String> implements StickyRecyclerHeadersAdapter<BaseRecycleHolder>{

        public AnimalHeaderAdapter(Context context, @NonNull List<String> list) {
            super(context, list,R.layout.item_home);
            setHasStableIds(true);
        }

        @Override
        public long getHeaderId(int position) {
            return list.get(position).charAt(0);
        }

        @Override
        public void onBindView(BaseRecycleHolder holder, int position, String str) {
            TextView textView = holder.getView(R.id.id_num);
            textView.setText(str);
        }

        @Override
        public BaseRecycleHolder onCreateHeaderViewHolder(ViewGroup parent) {
            View view = mInflater.inflate(R.layout.item_header,parent,false);
            return new BaseRecycleHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(BaseRecycleHolder holder, int position) {
            TextView textView = holder.getView(R.id.title);
            textView.setText("123");
        }
    }
}
