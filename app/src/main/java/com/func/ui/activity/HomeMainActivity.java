package com.func.ui.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.frame.R;
import com.frame.manager.adapter.BaseFragmentPagerAdapter;
import com.frame.manager.base.view.BaseActivity;
import com.frame.manager.utils.NavigationViewHelper;
import com.func.ui.fragment.AccountFragment;
import com.func.ui.fragment.IndexFragment;
import com.func.ui.fragment.InvestFragment;
import com.func.ui.fragment.QuestionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:主页
 * Created by Zijin on 2017/7/27.
 * Email: info@zijinqianbao.com
 */

public class HomeMainActivity extends BaseActivity {
    public static final int PAGE_INDEX = 0;     //首页
    public static final int PAGE_INVEST = 1;    //投资
    public static final int PAGE_QUESTION = 2;  //问题
    public static final int PAGE_ACCOUNT = 3;   //账户

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.navigationView)
    BottomNavigationView mNavigationView;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;

    private MenuItem mMenuItem;//选中的MenuItem

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();

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

        mViewPager.setOnTouchListener((view, motionEvent) -> true);

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

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(IndexFragment.newInstance());
        fragmentList.add(InvestFragment.newInstance());
        fragmentList.add(QuestionFragment.newInstance());
        fragmentList.add(AccountFragment.newInstance());

        mViewPager.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(), fragmentList) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }
        });

        initDrawerLayout();
    }

    /**
     * 初始化侧滑菜单
     */
    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected boolean isFitSystemWindows() {
        return false;
    }
}
