package com.colm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.colm.dao.OrderSettingDao;
import com.colm.pojo.OrderSetting;
import com.colm.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void batchSave(List<OrderSetting> orderSettings) {
        if(CollectionUtils.isEmpty(orderSettings)) {
            return;
        }
        orderSettingDao.batchSave(orderSettings);
    }
}
