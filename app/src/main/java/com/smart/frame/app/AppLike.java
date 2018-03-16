package com.smart.frame.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.smart.frame.utils.tinker.TinkerResultService;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.util.UpgradePatchRetry;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

/**
 * Tinker Application delegate
 *
 * @author Gjm
 * @date 2018/3/8
 */

public class AppLike extends DefaultApplicationLike{
    public AppLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);

        UpgradePatchRetry.getInstance(base).setRetryEnable(true);
        TinkerInstaller.install(this,new DefaultLoadReporter(base),new DefaultPatchReporter(base),new DefaultPatchListener(base), TinkerResultService.class,new UpgradePatch());
    }
}
