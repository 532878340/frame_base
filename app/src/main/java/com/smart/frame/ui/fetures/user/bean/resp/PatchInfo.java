package com.smart.frame.ui.fetures.user.bean.resp;

import java.util.Date;

/**
 * 补丁信息
 *
 * @author Gjm
 * @date 2018/3/15
 */
public class PatchInfo {
    private Long patchid;       //补丁Id
    private String patchname;   //补丁名
    private Integer platform;   //平台名
    private String versionname; //版本号
    private String fileid;      //fileId
    private String filename;    //文件名
    private String description; //描述
    private Integer status;     //状态

    public Long getPatchid() {
        return patchid;
    }

    public void setPatchid(Long patchid) {
        this.patchid = patchid;
    }

    public String getPatchname() {
        return patchname;
    }

    public void setPatchname(String patchname) {
        this.patchname = patchname;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PatchInfo{" +
                "patchid=" + patchid +
                ", patchname='" + patchname + '\'' +
                ", platform=" + platform +
                ", versionname='" + versionname + '\'' +
                ", fileid='" + fileid + '\'' +
                ", filename='" + filename + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
