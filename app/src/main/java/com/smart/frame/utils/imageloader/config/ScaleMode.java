package com.smart.frame.utils.imageloader.config;

/**
 * Description:缩放模式
 * Created by Zijin on 2017/8/2.
 * Email: info@zijinqianbao.com
 */

public interface ScaleMode {
    /**
     * 等比例裁剪，直到图片的宽高都大于等于ImageView的宽度，然后截取中间的显示
     */
    int CENTER_CROP = 1;
    /**
     * 等比例缩放图片，宽或者是高等于ImageView的宽或者是高
     */
    int FIT_CENTER = 2;
    /**
     * 等比例缩放图片
     */
    int CENTER_INSIDE = 3;
}
