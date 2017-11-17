package com.smart.frame.ui.fragment;

import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleFragment;

/**
 * Description:
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public class InvestFragment extends SimpleFragment {
    public static InvestFragment getInstance() {
        return new InvestFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initViewOrData() {

    }
}
