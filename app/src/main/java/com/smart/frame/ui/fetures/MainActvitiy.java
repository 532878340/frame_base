package com.smart.frame.ui.fetures;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.smart.frame.R;
import com.smart.frame.base.adapter.BaseFragmentPagerAdapter;
import com.smart.frame.base.ui.SimpleActivity;
import com.smart.frame.ui.fetures.fragment.AccountFragment;
import com.smart.frame.ui.fetures.fragment.IndexFragment;
import com.smart.frame.ui.fetures.fragment.InvestFragment;
import com.smart.frame.ui.fetures.fragment.QuestionFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 主页面
 * @author Gjm
 * @date 2018/01/12
 */
public class MainActvitiy extends SimpleActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    CommonTabLayout mTabLayout;

    private String[] TITLES = {"首页","账户"};
    private int[] ICONS_UNSELECTED = { R.drawable.ic_dashboard_black_24dp, R.drawable.ic_dashboard_black_24dp };
    private int[] ICONS_SELECTED = { R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp };

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewOrData() {
        initFragmentUI();
        initTabLayout();
    }

    /**
     * 初始化fragment ui
     */
    private void initFragmentUI() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(IndexFragment.getInstance());
//        fragments.add(InvestFragment.getInstance());
//        fragments.add(QuestionFragment.getInstance());
        fragments.add(AccountFragment.getInstance());

        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(),fragments){
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return TITLES[position];
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 初始化TabLayout
     */
    private void initTabLayout(){
        ArrayList<CustomTabEntity> list = new ArrayList<>();
        for (int i = 0 ; i < TITLES.length ; i ++){
            list.add(new TabEntity(TITLES[i],ICONS_SELECTED[i],ICONS_UNSELECTED[i]));
        }

        mTabLayout.setTabData(list);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mViewPager.setCurrentItem(0);
    }

    class TabEntity implements CustomTabEntity {
        private String mTitle;
        private int mSelectedIcon;
        private int mUnSelectedIcon;

        private TabEntity(String title, int selectedIcon, int unSelectedIcon) {
            this.mTitle = title;
            this.mSelectedIcon = selectedIcon;
            this.mUnSelectedIcon = unSelectedIcon;
        }

        @Override
        public String getTabTitle() {
            return mTitle;
        }

        @Override
        public int getTabSelectedIcon() {
            return mSelectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return mUnSelectedIcon;
        }
    }
}
