package com.colm.utils.excel;

import lombok.Data;

import java.lang.reflect.Method;
import java.math.RoundingMode;

@Data
public class ExcelFieldInfo {
    Class<?> propertyType;
    Method readMethod;
    Method writeMethod;
    int index;
    String title;
    RoundingMode roundingMode;
    String dateFormat;
}
