package com.smart.frame.ui.fetures.fragment;

import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleFragment;

/**
 * Description:
 *
 * @author Zijin
 * @date 2017/8/4
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
