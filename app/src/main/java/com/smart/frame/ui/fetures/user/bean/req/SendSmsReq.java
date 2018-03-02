package com.smart.frame.ui.fetures.user.bean.req;

/**
 * 发送短信请求
 *
 * @author Gjm
 * @date 2018/2/28
 */
public class SendSmsReq {
    /**
     * 手机号
     */
    private String phone;

    /**
     * 是否为注册
     */
    private boolean registerOrNot;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isRegisterOrNot() {
        return registerOrNot;
    }

    public void setRegisterOrNot(boolean registerOrNot) {
        this.registerOrNot = registerOrNot;
    }
}
