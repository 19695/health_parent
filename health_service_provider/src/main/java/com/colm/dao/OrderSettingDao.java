package com.colm.dao;

import com.colm.pojo.OrderSetting;

import java.util.List;

public interface OrderSettingDao {
    int batchSave(List<OrderSetting> list);
}
