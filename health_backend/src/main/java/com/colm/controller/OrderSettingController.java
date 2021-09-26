package com.colm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.colm.constant.MessageConstant;
import com.colm.entity.Result;
import com.colm.pojo.OrderSetting;
import com.colm.service.OrderSettingService;
import com.colm.utils.PoiReadUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("")MultipartFile file) {
        try {
            List<String[]> values = PoiReadUtils.readExcel(file);
            List<OrderSetting> orderSettings = new ArrayList<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PoiReadUtils.DATE_FORMAT);
            for (String[] value : values) {
                try {
                    Date date = simpleDateFormat.parse(value[0]);
                    int count = Integer.parseInt(value[1]);
                    OrderSetting orderSetting = new OrderSetting(date, count);
                    orderSettings.add(orderSetting);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            orderSettingService.batchSave(orderSettings);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

}
