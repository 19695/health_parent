package com.colm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.colm.dao.OrderSettingDao;
import com.colm.pojo.OrderSetting;
import com.colm.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
        List<Date> dateList = orderSettings.stream().map(OrderSetting::getOrderDate).collect(Collectors.toList());
        orderSettingDao.batchDelete(dateList);
        orderSettingDao.batchSave(orderSettings);
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String firstDay = date + "-1";
        String lastDay = date + "-31";
        Map<String, String> map = new HashMap<>();
        map.put("begin", firstDay);
        map.put("end", lastDay);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> result = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)) {
            for (OrderSetting orderSetting : list) {
                Map<String, Object> item = new HashMap<>();
                item.put("date", orderSetting.getOrderDate().getDate());
                item.put("number", orderSetting.getNumber());
                item.put("reservations", orderSetting.getReservations());
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        Date orderDate = orderSetting.getOrderDate();
        //根据日期查询是否已经进行了预约设置
        long count = orderSettingDao.findCountByOrderDate(orderDate);
        if(count > 0){
            //当前日期已经进行了预约设置，需要执行更新操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            //当前日期没有就那些预约设置，需要执行插入操作
            orderSettingDao.add(orderSetting);
        }
    }

}
