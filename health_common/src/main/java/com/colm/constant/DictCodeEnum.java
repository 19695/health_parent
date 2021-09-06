package com.colm.constant;

public enum DictCodeEnum {

    DEFAULT_PAGE("0001", "默认主页"),
    OSS_DOMAIN("0010", "对象存储域名");

    private String code;
    private String desc;

    private DictCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "DictCodeEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
