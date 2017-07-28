package com.func.ui.fragment;

import android.content.Intent;

import com.frame.R;
import com.frame.manager.base.view.FrameRootFragment;
import com.func.ui.activity.LoginActivity;

import butterknife.OnClick;

/**
 * Description: 首页
 * Created by Zijin on 2017/7/28.
 * Email: info@zijinqianbao.com
 */

public class IndexFragment extends FrameRootFragment {
    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @OnClick(R.id.cancel)
    void click() {
        startActivity(new Intent(mCtx, LoginActivity.class));
    }
}
