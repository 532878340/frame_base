package com.frame.ui.view.frame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.frame.R;

/**
 * Created by Zijin on 2017/7/24.
 * Email:info@zijinqianbao.com
 */

public class ToolBarHelperView extends Toolbar {
    /**
     * 是否使用默认样式显示ToolBar
     */
    private boolean mDisplayDefault;

    /**
     * 标题布局
     */
    private View mTitleLayout;
    /**
     * 标题
     */
    private TextView mTvTitle;
    /**
     * 右侧文字
     */
    private TextView mTvLeft;
    /**
     * 右侧文字
     */
    private TextView mTvRight;

    public ToolBarHelperView(Context context) {
        this(context, null);
    }

    public ToolBarHelperView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToolBarHelperView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.layout_title, this);

        mTitleLayout = findViewById(R.id.titleLayout);
        mTvTitle = findViewById(R.id.tvTitle);
        mTvLeft = findViewById(R.id.tvTitleLeft);
        mTvRight = findViewById(R.id.tvTitleRight);

        setBackgroundResource(R.color.toolbar_background);
    }

    public boolean isDisplayDefault() {
        return mDisplayDefault;
    }

    /**
     * 设置是否显示自定义内容
     * @param mDisplayDefault true显示默认toolbar false显示自定义内容
     */
    public void setDisplayDefault(boolean mDisplayDefault) {
        this.mDisplayDefault = mDisplayDefault;
    }

    @Override
    public void setTitleTextColor(int color) {
        if (mDisplayDefault) {
            super.setTitleTextColor(color);
        } else {
            mTvTitle.setTextColor(color);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (mDisplayDefault) {
            mTitleLayout.setVisibility(View.GONE);
            super.setTitle(title);
        } else {
            mTitleLayout.setVisibility(View.VISIBLE);
            super.setTitle(null);
            mTvTitle.setText(title);
        }
    }

    /**
     * 设置左右两侧文本和监听
     */
    public void setDirectionTextAndListener(@NonNull CharSequence left, @Nullable OnClickListener leftLis, @NonNull CharSequence right, @Nullable OnClickListener rightLis) {
        if (!mDisplayDefault) {
            mTitleLayout.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(left)) {
                mTvLeft.setVisibility(View.VISIBLE);
                mTvLeft.setText(left);
                mTvLeft.setOnClickListener(leftLis);
            }

            if (!TextUtils.isEmpty(right)) {
                mTvRight.setVisibility(View.VISIBLE);
                mTvRight.setText(right);
                mTvRight.setOnClickListener(rightLis);
            }
        }
    }
}
