package com.smart.frame.ui.fetures.fragment;

import android.content.Intent;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.orhanobut.logger.Logger;
import com.smart.frame.R;
import com.smart.frame.base.ui.SimpleFragment;
import com.smart.frame.ui.fetures.user.activity.LoginActivity;

import butterknife.BindView;

/**
 * Description:
 *
 * @author Zijin
 * @date 2017/8/4
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

        RxView.clicks(cancel).subscribe(integer -> startActivity(new Intent(mContext, LoginActivity.class)));
    }
}
