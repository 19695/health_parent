package com.colm.constant;

public enum DateFormatEnum {

    DATE("yyyyMMdd"),
    DATE_HYPHEN("yyyy-MM-dd"),
    DATE_SLASH("yyyy/MM/dd"),
    TIME("HHmmss"),
    TIME_COLON("HH:mm:ss"),
    TIME_MILLS("HHmmssSSS"),
    DATE_TIME("yyyyMMddHHmmss"),
    DATE_TIME_HYPHEN("yyyy-MM-dd HH:mm:ss"),
    DATE_TIME_SLASH("yyyy/MM/dd HH:mm:ss"),
    DATE_TIME_MILLS("yyyyMMddHHmmssSSS");

    private String format;

    DateFormatEnum(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
