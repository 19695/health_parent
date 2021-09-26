package com.colm.utils.excel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.RoundingMode;

import static java.lang.annotation.ElementType.FIELD;

@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelCell {
    // 索引
    int index();
    // 标题
    String title();
    // 舍入模式
    RoundingMode roundingMode() default RoundingMode.HALF_EVEN;
    // 小数位数
    int scaleLen() default 0;
    // 时间日期格式
    String dateFormat() default "";
}
