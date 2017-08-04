package com.smart.frame.ui.activity;

import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleActivity;
import com.smart.frame.ui.fragment.IndexFragment;
import com.smart.frame.utils.ActivityUtils;

/**
 * Description:
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public class SecondActivity extends SimpleActivity{
    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initViewOrData() {
        ActivityUtils.addFragment(this, R.id.container, IndexFragment.getInstance());
    }
}
