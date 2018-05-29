package com.smart.frame.ui.view.basic;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.smart.frame.utils.DensityHelper;

/**
 * 适配Webview
 *
 * @author Gjm
 * @date 2018/5/16
 */
public class DensityWebView extends WebView{
    public DensityWebView(Context context) {
        super(context);
    }

    public DensityWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DensityWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOverScrollMode(int mode) {
        super.setOverScrollMode(mode);
        DensityHelper.getInstance().active(getContext());
    }
}
