package com.smart.frame.ui.fetures.fragment;

import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleFragment;

/**
 * @author Zijin
 * @date 2017/8/4
 */

public class AccountFragment extends SimpleFragment {
    public static AccountFragment getInstance() {
        return new AccountFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_account;
    }

    @Override
    protected void initViewOrData() {
    }
}
