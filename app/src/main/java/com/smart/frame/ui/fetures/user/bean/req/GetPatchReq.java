package com.smart.frame.ui.fetures.user.bean.req;

/**
 * 获取补丁请求
 *
 * @author Gjm
 * @date 2018/3/15
 */
public class GetPatchReq {
    private String versionName;
    private int status;
    private int platform;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }
}
