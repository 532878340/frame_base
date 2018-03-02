package com.smart.frame.base.bean;

/**
 * 响应基类
 * @author Gjm
 * @date 2018/01/12
 */
public class Repo<T> {
    private String description = "this is description";
    private String code;
    private T data;

    public Repo() {
    }

    /**
     * 判断请求操作是否成功
     */
    public boolean isOk() {
        return "000000".equals(code) || "2000078".equals(code);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
