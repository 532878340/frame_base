package com.smart.frame.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

import com.smart.frame.di.component.AppComponent;
import com.smart.frame.di.component.DaggerAppComponent;
import com.smart.frame.di.module.AppModule;
import com.smart.frame.di.module.HttpModule;
import com.smart.frame.utils.imageloader.ImageLoader;
import com.smart.frame.utils.tinker.TinkerResultService;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.util.UpgradePatchRetry;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Tinker Application delegate
 *
 * @author Gjm
 * @date 2018/3/8
 */

@DefaultLifeCycle(application = "com.smart.frame.app.BaseApp",flags = ShareConstants.TINKER_ENABLE_ALL)
public class AppLike extends DefaultApplicationLike{
    private static Application sInstance;
    private static AppComponent sAppComponent;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public AppLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = getApplication();

        InitializeService.init(sInstance);
    }

    public static synchronized Application getInstance() {
        return sInstance;
    }

    /**
     * 获取全局AppComponent
     */
    public static AppComponent getAppComponent() {
        if (sAppComponent == null) {
            sAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(sInstance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return sAppComponent;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ImageLoader.trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.clearAllMemoryCaches();
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);

        UpgradePatchRetry.getInstance(base).setRetryEnable(true);
        TinkerInstaller.install(this,new DefaultLoadReporter(base),new DefaultPatchReporter(base),new DefaultPatchListener(base), TinkerResultService.class,new UpgradePatch());
    }
}
