package com.colm.service;

import com.colm.pojo.OrderSetting;

import java.util.List;

public interface OrderSettingService {
    void batchSave(List<OrderSetting> orderSettings);
}
