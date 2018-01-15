package com.smart.frame.ui.fetures.user.bean.req;

/**
 * 登录
 *
 * @author Gjm
 * @date 2018/1/15
 */
public class LoginReq {
    private String loginName;
    private String pwd;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
