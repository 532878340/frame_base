package com.smart.frame.ui.fragment.contact;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.smart.frame.R;
import com.smart.frame.ui.view.wheel.WheelView;
import com.smart.frame.ui.view.wheel.adapter.ListWheelAdapter;
import com.smart.frame.utils.ScreenUtils;

import java.util.List;

/**
 * description：预约时间选择对话框
 * author：JimG on 17/4/18 15:45
 * e-mail：info@zijinqianbao@qq.com
 */


public class AppointTimePickerDialog extends Dialog implements View.OnClickListener {
    WheelView storeWheel;

    private Context mCtx;

    List<String> storeArr;

    public AppointTimePickerDialog(Context context, List<String> storeArr) {
        this(context, R.style.netLoadingDialog, storeArr);
    }

    public AppointTimePickerDialog(Context context, int themeResId, List<String> storeArr) {
        super(context, themeResId);
        setContentView(R.layout.dialog_single_picker);

        mCtx = context;
        this.storeArr = storeArr;

        initView();

        setCanceledOnTouchOutside(false);
        setCancelable(false);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ScreenUtils.getScreenWidth(context);
        window.setGravity(Gravity.BOTTOM);
    }

    void initView() {
        findViewById(R.id.tvCancel).setOnClickListener(this);//取消
        findViewById(R.id.tvCfm).setOnClickListener(this);//确定
        storeWheel = (WheelView) findViewById(R.id.provinceWheel);
        storeWheel.setViewAdapter(new TimePickerAdapter(mCtx, storeArr));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCfm:
                if (_lis != null) {
                    _lis.onPickerSelected(storeWheel.getCurrentItem());
                }
            case R.id.tvCancel:
                dismiss();
                break;
        }
    }

    private class TimePickerAdapter extends ListWheelAdapter<String> {
        private final int paddingDimens;

        /**
         * Constructor
         *
         * @param context the current context
         */
        public TimePickerAdapter(Context context, List<String> list) {
            super(context, list);
            paddingDimens = context.getResources().getDimensionPixelOffset(R.dimen.dp_10);

            setTextSize(context.getResources().getDimensionPixelOffset(R.dimen.sp_14));
            setTextColor(context.getResources().getColor(R.color.gray_minor));
        }

        @Override
        protected void configureTextView(TextView view) {
            view.setGravity(Gravity.CENTER);
            view.setLines(1);
            view.setPadding(0, paddingDimens, 0, paddingDimens);
        }
    }

    private TimePickerListener _lis;

    /**
     * 地区选择
     */
    public interface TimePickerListener {
        void onPickerSelected(int pos);
    }

    public void setPickerListener(TimePickerListener lis){
        _lis = lis;
    }
}
