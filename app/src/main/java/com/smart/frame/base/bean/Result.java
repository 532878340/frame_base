package com.smart.frame.base.bean;

/**
 * $desc$
 *
 * @author Gjm
 * @date 2018/5/2
 */
public class Result {
    private String msg;
    private String code;
    private boolean success;
    private String data;

    @Override
    public String toString() {
        return "Result{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", success=" + success +
                ", data='" + data + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
