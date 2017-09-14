package com.smart.frame.ui.fragment;

import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleFragment;
import com.smart.frame.ui.fragment.contact.HeadEntity;
import com.smart.frame.ui.fragment.contact.ScrollTitleFragment;

import java.util.ArrayList;

import butterknife.OnClick;

/**
 * Description:
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public class InvestFragment extends SimpleFragment {
    private ArrayList<HeadEntity> mHeadList;

    ScrollTitleFragment scrollFragment;

    public static InvestFragment getInstance() {
        return new InvestFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initViewOrData() {
        mHeadList = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i ++){
            HeadEntity entity = new HeadEntity();
            entity.title = "标题" + i;
            mHeadList.add(entity);
        }
    }

    @OnClick(R.id.btnCfm) void click(){
        if(scrollFragment == null){
            scrollFragment = ScrollTitleFragment.getInstance(mHeadList);
        }
        scrollFragment.show(getChildFragmentManager(),"");
    }
}
