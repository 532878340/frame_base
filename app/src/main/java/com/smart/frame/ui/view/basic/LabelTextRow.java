package com.smart.frame.ui.view.basic;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.smart.frame.R;

/**
 * 通用Label和文本view
 *
 * @author Gjm
 * @date 2018/3/1
 */
public class LabelTextRow extends LinearLayout{
    public LabelTextRow(Context context) {
        this(context,null);
    }

    public LabelTextRow(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LabelTextRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.base_label_row_text,this);

        setMinimumHeight(100);
    }
}
