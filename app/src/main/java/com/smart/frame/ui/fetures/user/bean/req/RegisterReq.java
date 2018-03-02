package com.smart.frame.ui.fetures.user.bean.req;

/**
 * 注册
 *
 * @author Gjm
 * @date 2018/2/27
 */
public class RegisterReq {
    private String phone;
    private String password;
    private String smsCode;
    private String referrer;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }
}
