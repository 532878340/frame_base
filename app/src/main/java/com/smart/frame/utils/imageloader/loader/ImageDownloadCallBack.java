package com.smart.frame.utils.imageloader.loader;

import android.graphics.Bitmap;

/**
 * Description:图片下载回调
 * Created by Zijin on 2017/8/1.
 * Email: info@zijinqianbao.com
 */

public interface ImageDownloadCallBack {
    /**
     * 下载成功
     */
    void onDownLoadSuccess(Bitmap bitmap);

    /**
     * 下载失败
     */
    void onDownLoadFailed();
}
