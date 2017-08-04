package com.smart.frame.ui.fragment;

import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.smart.frame.R;
import com.smart.frame.base.ui.RootFragment;
import com.smart.frame.contract.InfoContract;
import com.smart.frame.presenter.SecondPresenter;

import butterknife.BindView;

/**
 * Description:
 * Created by Zijin on 2017/8/4.
 * Email: info@zijinqianbao.com
 */

public class IndexFragment extends RootFragment<SecondPresenter> implements InfoContract.ActView{
    @BindView(R.id.cancel)
    Button cancel;

    public static IndexFragment getInstance() {
        return new IndexFragment();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initViewOrData() {
        onStatusLoading();
        mPresenter.getInfoMessage();

        RxView.clicks(cancel)
                .subscribe(o -> mPresenter.getInfoMessage());
    }

    @Override
    public void getInfo() {

    }
}
