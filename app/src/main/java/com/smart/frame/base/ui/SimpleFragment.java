package com.smart.frame.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.orhanobut.logger.Logger;
import com.smart.frame.R;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 顶层无MVP fragment
 * @author Gjm
 * @date 2018/01/12
 */
public abstract class SimpleFragment extends RxFragment {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected Context mContext;

    /**
     * 根容器
     */
    private View mRootView;

    /**
     * content容器
     */
    protected FrameLayout mContainer;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logger.d("fragment:-------->" + getClass().getSimpleName());

        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.base_container_fragment, container, false);

            mContainer = mRootView.findViewById(R.id.container);
            if(getLayoutRes() != 0){
                View.inflate(mContext,getLayoutRes(),mContainer);
            }
        }
        mUnbinder = ButterKnife.bind(this,mRootView);

        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewOrData();
    }

    /**
     * 资源布局
     * @return
     */
    @LayoutRes
    protected abstract int getLayoutRes();

    /**
     * 操作视图
     */
    protected abstract void initViewOrData();
}
