package com.smart.frame.ui.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.smart.frame.R;
import com.smart.frame.base.adapter.BaseFragmentPagerAdapter;
import com.smart.frame.base.ui.SimpleActivity;
import com.smart.frame.ui.fragment.IndexFragment;
import com.smart.frame.utils.NavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerlayout;

    public static final int PAGE_INDEX = 0;     //首页
    public static final int PAGE_INVEST = 1;    //投资
    public static final int PAGE_QUESTION = 2;  //问题
    public static final int PAGE_ACCOUNT = 3;   //账户

    private MenuItem mMenuItem;//选中的MenuItem

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewOrData() {
        initNavigationView();
        initViewPager();
        initDrawerLayout();

        enableTranslucentStatus(true);
    }

    /**
     * 初始化navigationview
     */
    private void initNavigationView() {
        NavigationViewHelper.disableShiftMode(mNavigationView);
        mNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_index:
                    mViewPager.setCurrentItem(PAGE_INDEX);
                    break;
                case R.id.tab_invest:
                    mViewPager.setCurrentItem(PAGE_INVEST);
                    break;
                case R.id.tab_qa:
                    mViewPager.setCurrentItem(PAGE_QUESTION);
                    break;
                case R.id.tab_account:
                    mViewPager.setCurrentItem(PAGE_ACCOUNT);
                    break;
            }
            return false;
        });
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        List<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(IndexFragment.getInstance());
        mFragmentList.add(IndexFragment.getInstance());
        mFragmentList.add(IndexFragment.getInstance());
        mFragmentList.add(IndexFragment.getInstance());
        mViewPager.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (mMenuItem != null) {
                    mMenuItem.setChecked(false);
                } else {
                    mNavigationView.getMenu().getItem(0).setChecked(false);
                }

                mMenuItem = mNavigationView.getMenu().getItem(position);
                mMenuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 初始化DrawerLayout
     */
    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
