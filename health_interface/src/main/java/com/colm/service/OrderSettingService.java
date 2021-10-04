package com.colm.service;

import com.colm.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    void batchSave(List<OrderSetting> orderSettings);
    List<Map> getOrderSettingByMonth(String date);
    void editNumberByDate(OrderSetting orderSetting);
}
