package com.smart.frame.ui.fetures.activity;

import com.smart.frame.base.ui.SimpleActivity;
import com.smart.frame.ui.fetures.MainActvitiy;
import com.smart.frame.utils.ActivityUtils;

/**
 * Launcher
 *
 * @author Gjm
 * @date 2018/4/11
 */
public class LauncherActivity extends SimpleActivity{
    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initViewOrData() {
        delayEntry();
    }

    /**
     * 延迟进入app
     */
    void delayEntry() {
        getWindow().getDecorView().postDelayed(() -> {
            ActivityUtils.startAct(mCtx, MainActvitiy.class);
            finish();
        },2000);
    }
}
