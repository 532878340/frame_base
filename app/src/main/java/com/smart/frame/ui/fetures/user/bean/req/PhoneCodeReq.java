package com.smart.frame.ui.fetures.user.bean.req;

/**
 * 手机验证码请求
 *
 * @author Gjm
 * @date 2018/3/6
 */
public class PhoneCodeReq {
    /**
     * 手机号
     */
    private String phone;
    /**
     * 验证码
     */
    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
