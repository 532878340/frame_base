package com.smart.frame.ui.fragment.contact;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.smart.frame.R;
import com.smart.frame.base.adapter.BaseFragmentPagerAdapter;
import com.smart.frame.ui.view.bar.NavitationFollowScrollLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 滑动标题栏对话框
 * Created by Zijin on 2017/9/14.
 * Email: info@zijinqianbao.com
 */

public class ScrollTitleFragment extends DialogFragment{
    private NavitationFollowScrollLayout scrollLayout;//滑动标题
    private ViewPager viewPager;

    private Context mContext;

    //头部标题实体列表
    private List<HeadEntity> mHeadList = new ArrayList<>();

    public static ScrollTitleFragment getInstance(ArrayList<HeadEntity> headList){
        ScrollTitleFragment fragment = new ScrollTitleFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("headList",headList);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null && getArguments().containsKey("headList")){
            mHeadList = (List<HeadEntity>) getArguments().getSerializable("headList");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_scroll_title,container,false);

        mContext = getContext();
        viewPager = view.findViewById(R.id.viewPager);
        scrollLayout = view.findViewById(R.id.scrollLayout);

        initHeader();
        initViewPager();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Window window = getDialog().getWindow();
        window.setWindowAnimations(R.style.AnimTop); //设置窗口弹出动画
        window.setGravity(Gravity.TOP);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    /**
     * 初始化Header
     */
    private void initHeader() {
        List<String> titleList = new ArrayList<>();

        for (int i = 0 ; i < mHeadList.size() ; i ++){
            titleList.add(mHeadList.get(i).title);
        }

        scrollLayout.setViewPager(mContext, titleList.toArray(new String[titleList.size()]), viewPager, R.color.gray_light, R.color.colorPrimary, 14, 14, 12, true, R.color.gray_light, 1f, 12f, 12f, 100);
        scrollLayout.setBgLine(mContext, 1, R.color.colorAccent);
        scrollLayout.setNavLine((Activity) mContext, 3, R.color.colorPrimary);
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        List<Fragment> list = new ArrayList<>();
        for (HeadEntity entity : mHeadList) {
            list.add(SearchFragment.getInstance(entity));
        }

        viewPager.setAdapter(new BaseFragmentPagerAdapter(getChildFragmentManager(), list));
    }
}
