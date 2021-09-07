package com.colm.utils;

import com.colm.constant.DateFormatEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatUtil {

    /**
     * 标准格式的时间格式化
     * @param time
     * @param formatEnum
     * @return
     */
    public static String formatTime(LocalDateTime time, DateFormatEnum formatEnum) {
        String format = formatEnum.getFormat();
        return formatTime(time, format);
    }

    /**
     * 扩充的自定义的时间格式化（仅在标准格式化不满足的时候使用）
     * @param time
     * @param format
     * @return
     */
    public static String formatTime(LocalDateTime time, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(time);
    }
}
