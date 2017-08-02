package com.func.entity;

/**
 * Description:
 * Created by Zijin on 2017/8/2.
 * Email: info@zijinqianbao.com
 */

public class SteadyEntity {
    private long createTime;
    private String productInformationName;
    private String productTypeName;

    public String getProductInformationName() {
        return productInformationName;
    }

    public void setProductInformationName(String productInformationName) {
        this.productInformationName = productInformationName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
}
