package com.smart.frame.ui.view.basic.loading;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.github.ybq.android.spinkit.SpinKitView;
import com.smart.frame.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 加载视图
 * @author Zijin
 * @date 2017/7/24
 */

public class LoadingView extends FrameLayout implements ILoading {
    /**
     * 加载错误
     */
    @BindView(R.id.errorLayout)
    View mErrorLayout;

    /**
     * SpinKitView
     */
    @BindView(R.id.spinKit)
    SpinKitView mSpinKit;

    /**
     * 当前状态
     */
    private Status mStatus;

    /**
     * 重试
     */
    private OnRetryListener mOnRetryListener;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        View.inflate(getContext(), R.layout.layout_loading, this);
        ButterKnife.bind(this);
        setVisibility(View.GONE);
    }

    /**
     * 设置重试监听
     * @param retryListener
     */
    public void setOnRetryListener(OnRetryListener retryListener) {
        this.mOnRetryListener = retryListener;
    }

    @Override
    public Status getStatus() {
        return mStatus;
    }

    @Override
    public void setStatus(Status status) {
        if(mStatus != status){
            mStatus = status;
            onStatusChange(status);
        }
    }

    @Override
    public void onStatusChange(Status status) {
        switch (status) {
            case STATUS_INIT:
                init();
                break;
            case STATUS_LOADING:
                loading();
                break;
            case STATUS_SUCCESS:
                success();
                break;
            case STATUS_FAIL:
                fail();
                break;
        }
    }

    @OnClick(R.id.errorLayout)
    public void onClick() {
        if (mOnRetryListener != null) {
            mOnRetryListener.onRetry();
            setStatus(Status.STATUS_LOADING);
        }
    }

    @Override
    public void init() {
        mSpinKit.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
        setBackgroundResource(R.color.foreground);
        setVisibility(View.VISIBLE);
    }

    @Override
    public void loading() {
        mSpinKit.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
        setVisibility(View.VISIBLE);
    }

    @Override
    public void success() {
        setVisibility(View.GONE);
    }

    @Override
    public void fail() {
        mSpinKit.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.VISIBLE);
    }
}
