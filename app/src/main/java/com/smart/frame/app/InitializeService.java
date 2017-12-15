package com.smart.frame.app;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.smart.frame.BuildConfig;
import com.smart.frame.R;
import com.smart.frame.utils.imageloader.ImageLoader;

/**
 * Description: 初始化Service
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
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
        ImageLoader.init(context);              //图片加载
                                                //数据库
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
     * 异步初始化：
     * 日志
     * 错误收集
     * 内存泄漏
     * 过渡绘制检测
     */
    private void initApplicationAsync(){
        initLogger();
        initSmartRefresh();
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
                return BuildConfig.DEBUG_ENABLE;
            }
        });
    }

    /**
     * 初始化SmartRefresh
     */
    private void initSmartRefresh(){
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
            return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
        });
    }
}
