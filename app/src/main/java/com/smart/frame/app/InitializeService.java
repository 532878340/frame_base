package com.smart.frame.app;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

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

    }
}
