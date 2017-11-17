package com.smart.frame.ui.fragment;

import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleFragment;

import butterknife.BindView;

/**
 * Description:
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public class IndexFragment extends SimpleFragment{
    @BindView(R.id.cancel)
    Button cancel;

    public static IndexFragment getInstance() {
        return new IndexFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initViewOrData() {
        Logger.d("initViewOrData 创建" + toString());
    }
}
