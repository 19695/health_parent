package com.colm.utils.excel;

import org.apache.poi.ss.usermodel.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExcelParser {

    private static Map<Class, List<ExcelFieldInfo>> cachedType = new ConcurrentHashMap<>();

    /**
     * excel 解析为对应的实体集合
     * @param file
     * @param type
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> excel2List(File file, Class<T> type, Integer sheetIndex) throws Exception {
        Workbook workbook = WorkbookFactory.create(file);
        List<ExcelFieldInfo> fieldInfoList = parseType(type);
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        if(sheet == null) {
            return null;
        }
        Iterator<Row> iterator = sheet.iterator();
        // 首行是 title 直接跳过
        if(iterator.hasNext()) {
            iterator.next();
        }
        while (iterator.hasNext()) {
            Row row = iterator.next();
            T t = type.getDeclaredConstructor().newInstance();
            for (ExcelFieldInfo fieldInfo : fieldInfoList) {
                Cell cell = row.getCell(fieldInfo.getIndex());
                if(cell != null) {
                    Object value = getCellValue(cell);
                    fieldInfo.getWriteMethod().invoke(t, value);
                }
            }

        }
        return null;
    }

    private static Object getCellValue(Cell cell) {
        return null;
    }

    /**
     * 解析 excel 对应的实体类
     * @param type
     * @param <T>
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    private static <T> List<ExcelFieldInfo> parseType(Class<T> type) throws IntrospectionException, NoSuchFieldException {
        if(cachedType.containsKey(type)) {
            return cachedType.get(type);
        }
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        List<ExcelFieldInfo> fieldInfoList = new ArrayList<>();
        if(propertyDescriptors.length <= 0) {
            return fieldInfoList;
        }
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if("class".equals(propertyDescriptor.getName())) {
                continue;
            }
            Field field = type.getField(propertyDescriptor.getName());
            ExcelCell excelCell = field.getAnnotation(ExcelCell.class);
            if(excelCell == null) {
                continue;
            }
            ExcelFieldInfo excelFieldInfo = new ExcelFieldInfo();
            excelFieldInfo.setPropertyType(propertyDescriptor.getPropertyType());
            excelFieldInfo.setReadMethod(propertyDescriptor.getReadMethod());
            excelFieldInfo.setWriteMethod(propertyDescriptor.getWriteMethod());
            excelFieldInfo.setIndex(excelCell.index());
            excelFieldInfo.setTitle(excelCell.title());
            excelFieldInfo.setRoundingMode(excelCell.roundingMode());
            excelFieldInfo.setDateFormat(excelCell.dateFormat());
            fieldInfoList.add(excelFieldInfo);
        }
        cachedType.put(type, fieldInfoList);
        return fieldInfoList;
    }

}