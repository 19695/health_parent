package com.colm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.colm.constant.MessageConstant;
import com.colm.entity.Result;
import com.colm.pojo.OrderSetting;
import com.colm.service.OrderSettingService;
import com.colm.utils.POIReadUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("")MultipartFile file) {
        try {
            List<String[]> values = POIReadUtils.readExcel(file);
            List<OrderSetting> orderSettings = new ArrayList<>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(POIReadUtils.DATE_FORMAT);
            for (String[] value : values) {
                try {
                    Date date = simpleDateFormat.parse(value[0]);
                    int count = Integer.parseInt(value[1]);
                    OrderSetting orderSetting = new OrderSetting(date, count);
                    orderSettings.add(orderSetting);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
                }
            }
            orderSettingService.batchSave(orderSettings);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

    @PostMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date) { // date 格式为 yyyy-MM
        try {
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    @PostMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        try{
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }
}
