package com.colm.constant;

public enum DictTypeEnum {

    DEFAULT_PAGE("01", "默认主页"),
    OSS_DOMAIN("02", "对象存储域名");

    private String type;
    private String desc;

    private DictTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "DictTypeEnum{" +
                "type='" + type + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
