package com.func.ui.fragment;

import android.content.Intent;
import android.widget.ImageView;

import com.frame.R;
import com.frame.manager.base.view.FrameRootFragment;
import com.frame.manager.utils.imageloader.config.ImageConfig;
import com.func.ui.activity.ListActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description: 首页
 * Created by Zijin on 2017/7/28.
 * Email: info@zijinqianbao.com
 */

public class IndexFragment extends FrameRootFragment {
    @BindView(R.id.img)
    ImageView img;

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

        new ImageConfig.ConfigBuilder(mCtx)
                .url("http://106.14.112.233/activity/wap/app/images/app.jpg")
                .placeHolder(R.drawable.icon_default)
                .error(R.drawable.icon_default)
                .into(img);
    }

    @OnClick(R.id.cancel)
    void click() {
        startActivity(new Intent(mCtx, ListActivity.class));
    }
}
