package com.smart.frame.ui.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleActivity;
import com.smart.frame.ui.fragment.AccountFragment;
import com.smart.frame.ui.fragment.IndexFragment;
import com.smart.frame.ui.fragment.InvestFragment;
import com.smart.frame.ui.fragment.QuestionFragment;
import com.smart.frame.utils.ActivityUtils;
import com.smart.frame.utils.NavigationViewHelper;

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

    private static final String TAG_INDEX = "首页";
    private static final String TAG_INVEST = "投资";
    private static final String TAG_QUESTION = "问题";
    private static final String TAG_ACCOUNT = "账户";

    Fragment mIndexFragment, mInvestFragment, mQuestionFragment, mAccountFragment;
    private Fragment mCurrentFragment;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewOrData() {
        enableTranslucentStatus(true);

        initNavigationView();
        initDrawerLayout();
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
                    switchContent(mIndexFragment, TAG_INDEX);
                    break;
                case R.id.tab_invest:
                    switchContent(mInvestFragment, TAG_INVEST);
                    break;
                case R.id.tab_qa:
                    switchContent(mQuestionFragment, TAG_QUESTION);
                    break;
                case R.id.tab_account:
                    switchContent(mAccountFragment, TAG_ACCOUNT);
                    break;
            }


            return true;
        });
    }

    /**
     * 切换界面
     */
    void switchContent(Fragment target, String tag) {
        if (mCurrentFragment != target) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            if (!target.isAdded()) { // 先判断是否被add过
                transaction.hide(mCurrentFragment)
                        .add(R.id.fragmentContainer, target, tag)
                        .commit();
            } else {
                transaction.hide(mCurrentFragment)
                        .show(target)
                        .commit();
            }
            mCurrentFragment = target;
        }
    }

    /**
     * 初始化DrawerLayout
     */
    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * 初始化fragment ui
     */
    private void initFragmentUI() {
        mIndexFragment = IndexFragment.getInstance();
        mInvestFragment = InvestFragment.getInstance();
        mQuestionFragment = QuestionFragment.getInstance();
        mAccountFragment = AccountFragment.getInstance();

        ActivityUtils.addFragment(this, R.id.fragmentContainer, mCurrentFragment = mIndexFragment);
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
