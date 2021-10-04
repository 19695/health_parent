package com.colm.dao;

import com.colm.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    int batchSave(List<OrderSetting> list);
    long batchDelete(List<Date> list);
    List<OrderSetting> getOrderSettingByMonth(Map<String, String> map);
    long findCountByOrderDate(Date orderDate);
    void editNumberByOrderDate(OrderSetting orderSetting);
    void add(OrderSetting orderSetting);
}
