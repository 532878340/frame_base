package com.func.ui.fragment;

import com.frame.R;
import com.frame.manager.base.view.FrameRootFragment;

/**
 * Description: 投资页
 * Created by Zijin on 2017/7/28.
 * Email: info@zijinqianbao.com
 */

public class InvestFragment extends FrameRootFragment {
    public static InvestFragment newInstance() {
        return new InvestFragment();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_invest;
    }
}
