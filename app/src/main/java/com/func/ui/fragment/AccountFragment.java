package com.func.ui.fragment;

import com.frame.R;
import com.frame.manager.base.view.FrameRootFragment;

/**
 * Description: 账户中心
 * Created by Zijin on 2017/7/28.
 * Email: info@zijinqianbao.com
 */

public class AccountFragment extends FrameRootFragment {
    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_account;
    }
}
