package com.smart.frame.app;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.github.moduth.blockcanary.BlockCanary;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.smart.frame.R;
import com.smart.frame.manager.constants.Configs;
import com.smart.frame.utils.AppBlockCanaryContext;
import com.smart.frame.utils.DensityHelper;
import com.smart.frame.utils.SystemUtil;
import com.smart.frame.utils.imageloader.ImageLoader;
import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.ExcludedRefs;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import zlc.season.rxdownload3.core.DownloadConfig;

/**
 * 初始化Service
 * @author Gjm
 * @date 2018/01/12
 */

public class InitializeService extends IntentService{
    private static final String ACTION_INIT = "initApplication";

    /**
     * 全局初始化
     */
    public static void init(Context context){
        syncInit(context);
        asyncInit(context);
    }

    /**
     * 常用初始化
     */
    private static void syncInit(Context context){
        //图片加载
        ImageLoader.init(context);
        //数据库
        DensityHelper.getInstance().active(context);
    }

    /**
     * 子线程初始化
     */
    private static void asyncInit(Context context){
        Intent intent = new Intent(context,InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    public InitializeService() {
        super("InitializeService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null && ACTION_INIT.equals(intent.getAction())){
            initApplicationAsync();
        }
    }

    /**
     * 初始化SmartRefresh
     */
    private void initSmartRefresh(){
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            ////指定为经典Header，默认是 贝塞尔雷达Header
            return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
        });
    }

    /**
     * 异步初始化：
     * 日志
     * 错误收集
     * 内存泄漏
     * 过渡绘制检测
     * rx下载
     */
    private void initApplicationAsync(){
        initLogger();
        initBugly();
        initSmartRefresh();
        initLeakCanary();
        initBlockCanary();
        initRxDownload();
    }

    /**
     * 初始化日志
     */
    private void initLogger(){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(7)
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return Configs.DEBUG_ENABLE;
            }
        });
    }

    /**
     * 初始化Bugly
     */
    private void initBugly(){
        Context ctx = getApplicationContext();
        String packageName = ctx.getPackageName();
        String processName = SystemUtil.getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(ctx);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(App.getInstance(), Configs.APP_ID_BUGLY, Configs.DEBUG_ENABLE, strategy);
    }

    /**
     * 初始化内存泄漏检测
     */
    private void initLeakCanary(){
        ExcludedRefs excludedRefs = AndroidExcludedRefs.createAppDefaults()
                .instanceField("android.view.inputmethod.InputMethodManager", "sInstance")
                .instanceField("android.view.inputmethod.InputMethodManager", "mLastSrvView")
                .instanceField("com.android.internal.policy.PhoneWindow$DecorView", "mContext")
                .instanceField("android.support.v7.widget.SearchView$SearchAutoComplete", "mContext")
                .build();
        LeakCanary.refWatcher(App.getInstance())
                .listenerServiceClass(DisplayLeakService.class)
                .excludedRefs(excludedRefs)
                .buildAndInstall();
    }

    /**
     * 初始化过渡绘制检测
     */
    private void initBlockCanary(){
        BlockCanary.install(App.getInstance(),new AppBlockCanaryContext());
    }

    /**
     * 初始化RxDownload
     */
    private void initRxDownload(){
        DownloadConfig.Builder builder = DownloadConfig.Builder
                .Companion
                .create(App.getInstance())
//                .enableDb(true)
                .enableAutoStart(true)
                .enableService(true)
                .enableNotification(true);
        DownloadConfig.INSTANCE.init(builder);
    }
}
