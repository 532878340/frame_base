package com.smart.frame.ui.fetures.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.smart.frame.R;
import com.smart.frame.base.adapter.BaseFragmentPagerAdapter;
import com.smart.frame.base.ui.SimpleActivity;
import com.smart.frame.ui.fetures.fragment.AccountFragment;
import com.smart.frame.ui.fetures.fragment.IndexFragment;
import com.smart.frame.ui.fetures.fragment.InvestFragment;
import com.smart.frame.ui.fetures.fragment.QuestionFragment;
import com.smart.frame.utils.NavigationViewHelper;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Description: 首页
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

public class MainActvitiy extends SimpleActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.navigationView)
    BottomNavigationView mNavigationView;

    private static final int TAB_INDEX = 0;     //首页
    private static final int TAB_INVEST = 1;    //投资
    private static final int TAB_QUESTION = 2;  //问题
    private static final int TAB_ACCOUNT = 3;   //账户

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewOrData() {
        enableTranslucentStatus(false);

        initNavigationView();
        initFragmentUI();
    }

    /**
     * 初始化navigationview
     */
    private void initNavigationView() {
        NavigationViewHelper.disableShiftMode(mNavigationView);
        mNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_index:
                    mViewPager.setCurrentItem(TAB_INDEX);
                    break;
                case R.id.tab_invest:
                    mViewPager.setCurrentItem(TAB_INVEST);
                    break;
                case R.id.tab_qa:
                    mViewPager.setCurrentItem(TAB_QUESTION);
                    break;
                case R.id.tab_account:
                    mViewPager.setCurrentItem(TAB_ACCOUNT);
                    break;
            }
            return true;
        });
    }

    /**
     * 初始化fragment ui
     */
    private void initFragmentUI() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(IndexFragment.getInstance());
        fragments.add(InvestFragment.getInstance());
        fragments.add(QuestionFragment.getInstance());
        fragments.add(AccountFragment.getInstance());

        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(),fragments));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case TAB_INDEX:
                        mNavigationView.setSelectedItemId(R.id.tab_index);
                        break;
                    case TAB_INVEST:
                        mNavigationView.setSelectedItemId(R.id.tab_invest);
                        break;
                    case TAB_QUESTION:
                        mNavigationView.setSelectedItemId(R.id.tab_qa);
                        break;
                    case TAB_ACCOUNT:
                        mNavigationView.setSelectedItemId(R.id.tab_account);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
