package com.smart.frame.ui.fetures.activity;

import com.orhanobut.logger.Logger;
import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleActivity;
import com.smart.frame.ui.fetures.fragment.IndexFragment;
import com.smart.frame.utils.ActivityUtils;

/**
 *
 * @author huangqin
 */
public class SecondActivity extends SimpleActivity{
    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initViewOrData() {
        mToolBar.setDisplayDefault(true);
        initToolBar(false,"第二页");

        ActivityUtils.addFragment(this, R.id.container, IndexFragment.getInstance());

        Logger.e("this is message");


        Logger.d("initViewOrData: ");
    }
}
